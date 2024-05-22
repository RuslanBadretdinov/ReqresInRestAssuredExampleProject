package in.reqres.extensions;

import in.reqres.annotations.Driver;
import in.reqres.annotations.Page;
import in.reqres.factory.DriverFactory;
import in.reqres.listeners.AllureListener;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class UIExtension implements BeforeEachCallback, AfterEachCallback, AfterTestExecutionCallback {

    private WebDriver driver;

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        EventFiringWebDriver eventFiringWebDriver = new DriverFactory().create();
        eventFiringWebDriver.register(new AllureListener());
        this.driver = eventFiringWebDriver.getWrappedDriver();
        assignDriverVars(extensionContext, this.driver);
        assignPageVars(extensionContext, this.driver);
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        quit();
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        if (extensionContext.getExecutionException().isPresent()) {
            Allure.getLifecycle().addAttachment("Screenshot", "image/png", "png", getScreenshot());
        }
        quit();
    }

    private void quit() {
        if (driver != null) driver.quit();
    }

    private byte[] getScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    private void assignDriverVars(ExtensionContext extensionContext, WebDriver driver) {
        Arrays.stream(extensionContext.getTestInstance().getClass()
                        .getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Driver.class) && field.getType().equals(WebDriver.class))
                .forEach(field -> {
                    field.setAccessible(true);
                    try {
                        field.set(extensionContext.getTestInstance(), driver);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }

    private void assignPageVars(ExtensionContext extensionContext, WebDriver driver) {
        extensionContext.getTestInstance().ifPresent(instance -> Arrays.stream(instance.getClass().getDeclaredFields())
                .filter(field -> field.getType().isAnnotationPresent(Page.class))
                .forEach(f -> {
                    try {
                        Object page = f.getType().getDeclaredConstructor(WebDriver.class)
                                .newInstance(this.driver);
                        f.setAccessible(true);
                        f.set(extensionContext.getTestInstance().get(), page);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                            | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }));
    }
}
