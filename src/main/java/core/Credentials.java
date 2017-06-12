package core;


public class Credentials {

    private  String signInName;
    private  String signInPassword;
    private  String challengeKey;

    private  String oAuthConsumerKey;
    private  String oAuthConsumerSecret;
    private  String oAuthAccessToken;
    private  String oAuthAccessTokenSecret;


    public Credentials(String signInName,String signInPassword,String challengeKey) {
        this.signInName = signInName;
        this.signInPassword = signInPassword;
        this.challengeKey = challengeKey;
    }

    public Credentials(String oAuthConsumerKey,String oAuthConsumerSecret,String oAuthAccessToken,String oAuthAccessTokenSecret) {
        this.oAuthConsumerKey = oAuthConsumerKey;
        this.oAuthConsumerSecret = oAuthConsumerSecret;
        this.oAuthAccessToken = oAuthAccessToken;
        this.oAuthAccessTokenSecret = oAuthAccessTokenSecret;
    }

    public String getSignInName() {
        return signInName;
    }

    public String getSignInPassword() {
        return signInPassword;
    }

    public String getoAuthConsumerKey() {
        return oAuthConsumerKey;
    }

    public String getoAuthConsumerSecret() {
        return oAuthConsumerSecret;
    }

    public String getoAuthAccessToken() {
        return oAuthAccessToken;
    }

    public String getoAuthAccessTokenSecret() {
        return oAuthAccessTokenSecret;
    }

    public String getChallengeKey() {
        return challengeKey;
    }
}
