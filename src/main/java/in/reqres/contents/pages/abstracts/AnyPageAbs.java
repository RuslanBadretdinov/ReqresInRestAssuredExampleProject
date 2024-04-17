package in.reqres.contents.pages.abstracts;

import in.reqres.contents.BasicDriverOperations;
import org.openqa.selenium.WebDriver;

public abstract class AnyPageAbs<T> extends BasicDriverOperations<T> {

    protected AnyPageAbs(WebDriver driver) {
        super(driver);
    }

    protected abstract T isLoaded();

    protected abstract T open();
}
