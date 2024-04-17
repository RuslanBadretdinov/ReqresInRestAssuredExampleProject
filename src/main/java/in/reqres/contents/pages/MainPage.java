package in.reqres.contents.pages;

import in.reqres.annotations.Page;
import in.reqres.contents.pages.abstracts.AnyPageAbs;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

@Page
public class MainPage extends AnyPageAbs<MainPage> {

    @FindBy(xpath = "//h1[@class = 'logo']")
    private WebElement isLoadedElement;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("загружена страница 'Главная страница'")
    public MainPage isLoaded() {
        assertThat(waitForElementVisible(isLoadedElement)).
                as(String.format("Страница '%s' не загружена, вебэлемент маркера не виден на странице",
                        this.getClass().getSimpleName()))
                .isTrue();
        return this;
    }

    @Override
    @Step("открытие страницы 'Главная страница'")
    public MainPage open() {
        this.driver.get(System.getProperty("base.web.url"));
        return this;
    }
}
