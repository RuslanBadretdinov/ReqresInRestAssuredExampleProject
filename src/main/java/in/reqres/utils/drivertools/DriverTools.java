package in.reqres.utils.drivertools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverTools {

    private static DriverTools driverTools;

    private WebDriverWait waiter;
    private Actions actions;

    private DriverTools(WebDriver driver) {
        waiter = new WebDriverWait(driver, 10000L);
        actions = new Actions(driver);
    }

    public static DriverTools getInstance(WebDriver driver) {
        if (driverTools == null) {
            driverTools = new DriverTools(driver);
        }
        return driverTools;
    }

    public WebDriverWait getWaiter() {
        return waiter;
    }

    public Actions getActions() {
        return actions;
    }
}
