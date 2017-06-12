import core.WebdriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import twitterPages.AuthorisationPage;
import twitterPages.TwitterMainPage;
import twitterPages.TwitterUsersPage;

public class NewTweetTests {
    private AuthorisationPage authorisationPage;
    private TwitterUsersPage twitterUsersPage;
    private String messageText;

    @BeforeClass
    public void setUp() {
        authorisationPage = WebdriverManager.open();
    }

    @AfterClass
    public void tearDown() {
        WebdriverManager.close();

    }

    @Test
    public void testNewUserTweetPost() {

        messageText = "UI test " + Math.random();

        twitterUsersPage =
                authorisationPage
                        .signInUser("userUI")
                        .clickNewTweetButton()
                        .inputNewTweetMessage(messageText)
                        .submitNewTweet()
                        .goToTwitterUsersPage();

        Assert.assertEquals(twitterUsersPage.getLatestTweetMessage(), messageText);

    }

    @Test(dependsOnMethods = "testNewUserTweetPost")
    public void testTweetDesptory() {

        String latestTweetMessage =
                twitterUsersPage
                        .removeTheLatestTweet()
                        .getLatestTweetMessage();

        Assert.assertFalse(latestTweetMessage.equals(messageText));
    }

    @Test(dependsOnMethods = "testTweetDesptory")
    public void testTweetDuplication() {

        TwitterMainPage twitterMainPage =
                new TwitterMainPage()
                        .clickNewTweetButton()
                        .inputNewTweetMessage(messageText)
                        .submitNewTweet()
                        .clickNewTweetButton()
                        .inputNewTweetMessage(messageText)
                        .submitNewTweet();

        Assert.assertEquals(twitterMainPage.getAllertMessage(), "Вы уже отправили этот твит.");

//post-condition
        twitterMainPage.closeTweetDialog()
                .openUserNavigationDropdownPanel()
                .signOut();
    }

}
