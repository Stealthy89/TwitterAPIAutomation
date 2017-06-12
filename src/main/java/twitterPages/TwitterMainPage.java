package twitterPages;

import org.openqa.selenium.By;

import static elements.Element.getElement;

public class TwitterMainPage {

    private static final By NEW_TWEET_BUTTON_LOCATOR = By.id("global-new-tweet-button");
    private static final By USER_PROFILE_DROPDOWN_BUTTON_LOCATOR = By.id("user-dropdown");
    private static final By USER_SIGN_OUT_BUTTON_LOCATOR = By.id("signout-button");
    private static final By NEW_TWEET_MODAL_TEXT_AREA_LOCATOR = By.id("tweet-box-global");
    private static final By NEW_TWEET_SUBMIT_BUTTON_LOCATOR = By.xpath("//button[contains(@class,'js-tweet-btn') and not(contains(@class,'disabled'))]");
    private static final By CURRENT_USER_BUTTON_LOCATOR = By.className("current-user");
    private static final By ALERT_MESSAGE_TEXT_LOCATOR = By.xpath("//*[@id='message-drawer']//span[@class='message-text']");
    private static final By CLOSE_DIALOG_BUTTON_LOCATOR = By.xpath("//*[contains(@id,'dialog')]/button");

    public TwitterMainPage() {
    }

    public TwitterMainPage clickNewTweetButton() {
        getElement(NEW_TWEET_BUTTON_LOCATOR).click();

        return this;
    }


    public TwitterMainPage inputNewTweetMessage(String message) {

        getElement(NEW_TWEET_MODAL_TEXT_AREA_LOCATOR).sendKeys(message);
        return this;
    }

    public TwitterMainPage submitNewTweet() {

        getElement(NEW_TWEET_SUBMIT_BUTTON_LOCATOR).click();

        return this;
    }


    public TwitterUsersPage goToTwitterUsersPage() {

        getElement(USER_PROFILE_DROPDOWN_BUTTON_LOCATOR).click();
        getElement(CURRENT_USER_BUTTON_LOCATOR).click();
        return new TwitterUsersPage();
    }

    public TwitterMainPage openUserNavigationDropdownPanel() {
        getElement(USER_PROFILE_DROPDOWN_BUTTON_LOCATOR).click();
        return this;
    }

    public void signOut() {
        getElement(USER_SIGN_OUT_BUTTON_LOCATOR).click();
    }


    public String getAllertMessage() {
        return getElement(ALERT_MESSAGE_TEXT_LOCATOR).getText();
    }

    public TwitterMainPage closeTweetDialog() {
        getElement(CLOSE_DIALOG_BUTTON_LOCATOR).click();
        return this;
    }
}
