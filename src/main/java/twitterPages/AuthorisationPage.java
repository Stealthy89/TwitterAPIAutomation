package twitterPages;

import core.Config;
import core.Credentials;
import org.openqa.selenium.By;

import static elements.Element.getElement;

public class AuthorisationPage {

    private static final By SIGN_IN_NAME_INPUT_LOCATOR = By.id("signin-email");
    private static final By SIGN_IN_PASSWORD_INPUT_LOCATOR = By.id("signin-password");
    private static final By SIGN_IN_BUTTON_LOCATOR = By.xpath("//button[@type='submit' and contains(@class,('submit'))]");
    private static final By CHALENGE_RESPONSE_INPUT_LOCATOR = By.id("challenge_response");
    private static final By CHALENGE_SUBMIT_INPUT_LOCATOR = By.id("email_challenge_submit");

    public AuthorisationPage() {
    }


    private AuthorisationPage inputSignInName(String userName) {

        getElement(SIGN_IN_NAME_INPUT_LOCATOR).sendKeys(userName);
        return this;
    }

    private AuthorisationPage inputSignInPassword(String userPassword) {

        getElement(SIGN_IN_PASSWORD_INPUT_LOCATOR).sendKeys(userPassword);
        return this;
    }

    private AuthorisationPage submitSignIn() {

        getElement(SIGN_IN_BUTTON_LOCATOR).click();
        return this;
    }

    private AuthorisationPage proceedChallengeSingIn(Credentials credentials) {

        getElement(CHALENGE_RESPONSE_INPUT_LOCATOR).sendKeys(credentials.getChallengeKey());
        getElement(CHALENGE_SUBMIT_INPUT_LOCATOR).click();

        return this;
    }

    private AuthorisationPage fillOutUserCredentials(Credentials credentials) {

        inputSignInName(credentials.getSignInName());
        inputSignInPassword(credentials.getSignInPassword());

        return this;

    }

    public TwitterMainPage signInUser(String userId) {

        fillOutUserCredentials(Config.getCredentials(userId))
                .submitSignIn();
        if (getElement(CHALENGE_RESPONSE_INPUT_LOCATOR).isVisible()) {

            proceedChallengeSingIn(Config.getCredentials(userId));
        }
        return new TwitterMainPage();

    }

}
