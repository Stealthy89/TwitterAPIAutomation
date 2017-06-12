import core.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.net.URL;

public class RestAPITests {

    private static Twitter twitter;

    @BeforeClass
    public void getSSLCertificates() throws Exception {
        SslUtil.ensureSslCertIsInKeystore("startssl", new URL("https://www.startcomca.com/certs/online2017/StartComCertificationAuthorityG3.cacert.pem").openStream());

    }

    @BeforeMethod
    public void setUp() throws Exception {

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(Config.getCredentials("userAPI").getoAuthConsumerKey())
                .setOAuthConsumerSecret(Config.getCredentials("userAPI").getoAuthConsumerSecret())
                .setOAuthAccessToken(Config.getCredentials("userAPI").getoAuthAccessToken())
                .setOAuthAccessTokenSecret(Config.getCredentials("userAPI").getoAuthAccessTokenSecret())
                .setJSONStoreEnabled(true);

        TwitterFactory factory = new TwitterFactory(configurationBuilder.build());
        twitter = factory.getInstance();

    }


    @Test
    public void whenGetHometimeLineThenFieldsExist() throws Exception {

        JSONObject object;

        for (Status status : twitter.getHomeTimeline()) {
            String json = TwitterObjectFactory.getRawJSON(status);
            object = new JSONObject(json);

            Assert.assertTrue(object.has("created_at"));
            Assert.assertTrue(object.has("retweet_count"));
            Assert.assertTrue(object.has("text"));
        }
    }

    @Test
    public void whenDestroyStatusThenItIsReallyDestroyed() throws Exception {

        Status newStatus = twitter.updateStatus("Tweet to Destroy");
        long statusId = newStatus.getId();
        Status status = twitter.destroyStatus(newStatus.getId());

        Assert.assertEquals(statusId, status.getId());

        ResponseList<Status> statuses = twitter.getUserTimeline();

        for (Status statusItem : statuses) {
            Assert.assertFalse(statusItem.getId() == statusId);
        }
    }


    @Test
    public void checkThatStatusTextIsUpdated() throws Exception {
        String text = "New Tweet" + Math.random();
        Status newStatus = twitter.updateStatus(text);

        Assert.assertEquals(newStatus.getText(), text);
    }

    @Test
    public void checkTweetDuplication() throws Exception {

        Status newStatus = twitter.updateStatus("New Tweet" + Math.random());

        try {

            twitter.updateStatus(newStatus.getText());
        } catch (TwitterException t) {
            int statusCode = t.getStatusCode();

            Assert.assertEquals(403, statusCode);

        }

    }


}


