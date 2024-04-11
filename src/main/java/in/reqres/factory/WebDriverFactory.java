package in.reqres.factory;

import in.reqres.factory.exceptions.WebDriverNotSupportedException;
import in.reqres.factory.impl.ChromeSettings;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Locale;

public class WebDriverFactory {
    private final String browserName = System.getProperty("browser.name");

    public EventFiringWebDriver create() {
        switch (browserName.toLowerCase(Locale.ROOT)) {
            case "chrome" : {
                return new EventFiringWebDriver(new ChromeDriver(new ChromeSettings().getSettings()));
            }
            case "firefox" : {
                throw new WebDriverNotSupportedException(browserName);
            }
        }

        throw new WebDriverNotSupportedException(browserName);
    }
}
