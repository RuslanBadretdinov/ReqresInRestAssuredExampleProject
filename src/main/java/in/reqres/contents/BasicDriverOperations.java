package in.reqres.contents;

import in.reqres.utils.drivertools.DriverTools;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasicDriverOperations<T> {
    protected WebDriver driver;
    protected WebDriverWait waiter;
    protected Actions actions;
    protected JavascriptExecutor jsExecutor;

    public BasicDriverOperations(WebDriver driver) {
        this.driver = driver;
        this.waiter = DriverTools.getInstance(driver).getWaiter();
        this.actions = DriverTools.getInstance(driver).getActions();
        this.jsExecutor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public boolean waitForCondition(ExpectedCondition expectedCondition) {
        try {
            this.waiter.until(expectedCondition);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean waitForElementVisible(WebElement element) {
        return waitForCondition(ExpectedConditions.visibilityOf(element));
    }
}
