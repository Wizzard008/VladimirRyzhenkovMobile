package setup;

import static java.lang.String.format;
import static util.TokenReader.getSecretToken;

import io.appium.java_client.AppiumDriver;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pageObjects.PageObjectProvider;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest implements IDriver {

    private static AppiumDriver appiumDriver; // singleton
    private static IPageObject po;

    @Override
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    public IPageObject getPo() {
        return po;
    }

    @Parameters({"platformName", "appType", "deviceName", "browserName", "app", "appiumHub", "projectName",
                    "deviceUdid","appPackage","appActivity","bundleId"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName, String appType, @Optional("") String deviceName
        , @Optional("") String browserName, @Optional("") String app
        , String appiumHub, @Optional("") String projectName, @Optional("") String deviceUdid
        , @Optional("") String appPackage, @Optional("") String appActivity, @Optional("") String bundleId) throws Exception {

        System.out.println("Before: app type - " + appType);
        setAppiumDriver(platformName, deviceName, browserName, app, appiumHub, projectName, deviceUdid, appPackage, appActivity, bundleId);
        setPageObject(appType, appiumDriver);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName, String deviceName, String browserName, String app
        , String appiumHub, String projectName, String deviceUdid, String appPackage, String appActivity,
                                 String bundleId)
        throws UnsupportedEncodingException, MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        if (app.endsWith(".apk")) {
            capabilities.setCapability("app", (new File(app)).getAbsolutePath());
        }
        capabilities.setCapability("UDID", deviceUdid);
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("bundleId", bundleId);

        String safetyIdentifier="";
        String key="";
        if(!projectName.equalsIgnoreCase("")){
            safetyIdentifier = "s";
            key=getSecretToken();
        }


        String utfKey = URLEncoder.encode(key, StandardCharsets.UTF_8.name());
        appiumDriver = new AppiumDriver(
            new URL(format("http%s://%s:%s@%s/wd/hub", safetyIdentifier, projectName, utfKey, appiumHub)),
            capabilities);

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private static void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        po = new PageObjectProvider(appType, appiumDriver);
    }

    public void waitForPageToLoadCompletely() {
        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), 10).until(
            wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
    }
}
