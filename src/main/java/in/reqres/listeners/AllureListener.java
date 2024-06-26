package in.reqres.listeners;

import io.qameta.allure.junit5.AllureJunit5;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class AllureListener extends AllureJunit5 implements WebDriverEventListener {

    @Override
    public void beforeAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateTo(String s, WebDriver webDriver) {

    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        paintBorderRed(webElement, webDriver);
    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        paintBorderRed(webElement, webDriver);
    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        paintHiddenBorder(webElement, webDriver);
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        paintBorderRed(webElement, webDriver);
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        paintHiddenBorder(webElement, webDriver);
    }

    @Override
    public void beforeScript(String s, WebDriver webDriver) {

    }

    @Override
    public void afterScript(String s, WebDriver webDriver) {

    }

    @Override
    public void beforeSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {

    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

    }

    @Override
    public void beforeGetText(WebElement webElement, WebDriver webDriver) {
        paintBorderRed(webElement, webDriver);
    }

    @Override
    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {
        paintHiddenBorder(webElement, webDriver);
    }

    private void paintBorderRed(WebElement webElement, WebDriver webDriver) {
        ((JavascriptExecutor) webDriver)
                .executeScript("argument[0].style.border = '5px ridge rgba(100, 220, 50, .6)';", webElement);
    }

    private void paintHiddenBorder(WebElement webElement, WebDriver webDriver) {
        ((JavascriptExecutor) webDriver)
                .executeScript("argument[0].style.border = 'hidden';", webElement);
    }
}
