package twitterPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static elements.Element.getElement;

public class TwitterUsersPage {

    private static final By LATEST_TWEET_ITEM_LOCATOR = By.xpath("//*[@id='stream-items-id']/li");
    private static final By LATEST_TWEET_ITEM_TEXT_LOCATOR = By.xpath("//p[contains(@class,'text')]");
    private static final By DROP_DOWN_BUTTON_LOCATOR = By.xpath("//button[contains(@class,'actionButton') and contains(@class,'dropdown')]");
    private static final By DROP_DOWN_DELETE_BUTTON_LOCATOR = By.xpath("//li[contains(@class,'actionDelete')]//button");
    private static final By DELETE_ALERT_MODAL_BUTTON_LOCATOR = By.xpath("//button[contains(@class,'delete')]");

    TwitterUsersPage() {
    }

    private WebElement findTheLatestTweet() {

        return getElement(LATEST_TWEET_ITEM_LOCATOR).getWrappedElement();

    }

    private TwitterUsersPage openLatestTweetDropdown() {
        findTheLatestTweet()
                .findElement(DROP_DOWN_BUTTON_LOCATOR).click();

        return this;

    }

    private TwitterUsersPage selectDeleteFromDropdown() {

        getElement(DROP_DOWN_DELETE_BUTTON_LOCATOR).click();
        return this;

    }

    private TwitterUsersPage confirmDelete() {

        getElement(DELETE_ALERT_MODAL_BUTTON_LOCATOR).click();
        return this;

    }

    public String getLatestTweetMessage() {

        return findTheLatestTweet()
                .findElement(LATEST_TWEET_ITEM_TEXT_LOCATOR).getText();
    }


    public TwitterUsersPage removeTheLatestTweet() {

        openLatestTweetDropdown();
        selectDeleteFromDropdown();
        confirmDelete();
        return this;
    }
}
