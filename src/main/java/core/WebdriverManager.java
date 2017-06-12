package core;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import twitterPages.AuthorisationPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class WebdriverManager {
    private static WebDriver driver;

    public WebdriverManager() {

    }

    public static WebDriver getInstanse() {

        if (driver == null) {

            final File file = new File(PropertyLoader.loadProperty("path.webDriver"));
            System.setProperty(PropertyLoader.loadProperty("webDriver"), file.getAbsolutePath());

            driver = new ChromeDriver();

        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;

    }

    public static AuthorisationPage open() {

        WebdriverManager.getInstanse().get(Config.getBasicUrl());
        return new AuthorisationPage();
    }

    public static void close() {

        driver.close();
    }
}
