package in.reqres.factory;

import in.reqres.exceptions.WebDriverNotSupportedException;
import in.reqres.factory.impl.IDriver;
import in.reqres.factory.impl.InitChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Locale;

public class DriverFactory {
    private final String browserName = System.getProperty("browser.name");

    public EventFiringWebDriver create() {
        switch (browserName.toLowerCase(Locale.ROOT)) {
            case "chrome": {
                WebDriverManager.chromiumdriver().browserVersion("125.0").setup();
                IDriver<ChromeOptions> chromeOptions = new InitChromeOptions();
                return new EventFiringWebDriver(new ChromeDriver(chromeOptions.getInitOptions()));
            }
            case "firefox": {
                throw new WebDriverNotSupportedException(browserName);
            }
        }

        throw new WebDriverNotSupportedException(browserName);
    }
}
