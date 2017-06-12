package core;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;

public class Config {
    private static final String BASIC_URL = "https://twitter.com/";
    private static String key = "encryptMeAll";

    public Config() {
    }

    static String getBasicUrl() {

        return BASIC_URL;
    }

    public static Credentials getCredentials(String id) throws IndexOutOfBoundsException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new File("src\\main\\java\\core\\Credentials.xml"));


            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();

            switch (id) {
                case "userUI":
                    Node userName = (Node) xpath.compile("//user[@id = '" + id + "']/name").evaluate(document, XPathConstants.NODE);
                    Node userPassword = (Node) xpath.compile("//user[@id = '" + id + "']/password").evaluate(document, XPathConstants.NODE);
                    Node challengeKey = (Node) xpath.compile("//user[@id = '" + id + "']/challengeKey").evaluate(document, XPathConstants.NODE);

                    return new Credentials(userName.getTextContent(), new DesCrypter(Config.key).decrypt(userPassword.getTextContent()), new DesCrypter(Config.key).decrypt(challengeKey.getTextContent()));
                case "userAPI":
                    Node userConsumerKey = (Node) xpath.compile("//user[@id = '" + id + "']/consumerKey").evaluate(document, XPathConstants.NODE);
                    Node userConsumerSecret = (Node) xpath.compile("//user[@id = '" + id + "']/consumerSecret").evaluate(document, XPathConstants.NODE);
                    Node userAccessToken = (Node) xpath.compile("//user[@id = '" + id + "']/accessToken").evaluate(document, XPathConstants.NODE);
                    Node userAccessTokenSecret = (Node) xpath.compile("//user[@id = '" + id + "']/accessTokenSecret").evaluate(document, XPathConstants.NODE);

                    return new Credentials(new DesCrypter(Config.key).decrypt(userConsumerKey.getTextContent()), new DesCrypter(Config.key).decrypt(userConsumerSecret.getTextContent()), new DesCrypter(Config.key).decrypt(userAccessToken.getTextContent()), new DesCrypter(Config.key).decrypt(userAccessTokenSecret.getTextContent()));

            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }


}
