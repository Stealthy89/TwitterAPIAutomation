package elements;

import core.WebdriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Element {

    private By locator;

    private Element(By locator) {
        this.locator = locator;
    }

    private WebElement waitForElementIsVisible() {

        waitForDocStateReady();

        WebElement webElement = null;
        int sleepingTime = 2000;

        try {
            sleep(sleepingTime);//additional wait if doc state is ready but some scripts are not finished
            webElement = findElement();

            if (webElement == null) {
                sleep(sleepingTime);
            }

            if (webElement != null && webElement.isDisplayed()) {
                return webElement;

            } else {
                sleep(sleepingTime);
            }
        } catch (WebDriverException e) {
            e.getMessage();
        }

        return webElement;
    }

    private WebElement findElement() {

        return WebdriverManager.getInstanse().findElement(locator);
    }

    private void sleep(long sleepingTime) {

        try {
            Thread.sleep(sleepingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Element getElement(By locator) {

        return new Element(locator);
    }

    public void click() {

        waitForElementIsVisible().click();
    }

    public void sendKeys(String value) {

        waitForElementIsVisible().sendKeys(value);
    }

    public String getText() {

        return waitForElementIsVisible().getText();
    }

    public boolean isVisible() {

        try {
            WebElement element = WebdriverManager.getInstanse().findElement(locator);
            if (element != null) {
                return element.isDisplayed();
            }

        } catch (NoSuchElementException e) {
            return false;

        }

        return false;
    }

    public WebElement getWrappedElement() {

        return waitForElementIsVisible();
    }

    private static void waitForDocStateReady() {

        new WebDriverWait(WebdriverManager.getInstanse(), 50).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

    }
}
