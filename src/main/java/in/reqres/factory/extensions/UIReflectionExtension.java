package in.reqres.factory.extensions;

import in.reqres.factory.WebDriverFactory;
import in.reqres.factory.annotations.Driver;
import in.reqres.listeners.DriverListener;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Arrays;

public class UIReflectionExtension implements BeforeEachCallback, AfterEachCallback, AfterTestExecutionCallback {

    private WebDriver driver = null;

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        EventFiringWebDriver driver = new WebDriverFactory().create();
        driver.register(new DriverListener());
        assignDriverVars(extensionContext, driver);
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {

    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        if (extensionContext.getExecutionException().isPresent()) {
            Allure.getLifecycle().addAttachment("Screenshot", "image/png", "png",
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)
            );
        }
    }

    private void assignDriverVars(ExtensionContext extensionContext, EventFiringWebDriver driver) {
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
}
