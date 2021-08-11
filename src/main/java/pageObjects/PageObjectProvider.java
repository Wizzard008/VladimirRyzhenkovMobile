package pageObjects;

import io.appium.java_client.AppiumDriver;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import pageObjects.nativePages.BudgetPage;
import pageObjects.nativePages.LoginPage;
import pageObjects.nativePages.RegistrationPage;
import pageObjects.webPages.GoogleResultsWebPage;
import pageObjects.webPages.GoogleSearchWebPage;
import setup.IPageObject;

import java.lang.reflect.Field;

public class PageObjectProvider implements IPageObject {

    List<Object> testPageObjects;

    public PageObjectProvider(String appType, AppiumDriver appiumDriver) throws Exception {
        testPageObjects = new ArrayList<>();
        System.out.println("Current app type: " + appType);
        switch (appType) {
            case "web":
                testPageObjects.add(new GoogleResultsWebPage(appiumDriver));
                testPageObjects.add(new GoogleSearchWebPage(appiumDriver));
                break;
            case "native":
                testPageObjects.add(new BudgetPage(appiumDriver));
                testPageObjects.add(new LoginPage(appiumDriver));
                testPageObjects.add(new RegistrationPage(appiumDriver));
                break;
            default:
                throw new Exception("Can't create a page object for " + appType);
        }
    }

    @Override
    public WebElement getWelement(String pageName, String weName) throws NoSuchFieldException, IllegalAccessException {
        Field field;
        for (Object pageObject : testPageObjects) {
            if (pageObject.getClass().getSimpleName().equals(pageName)) {
                field = pageObject.getClass().getDeclaredField(weName);
                field.setAccessible(true);
                return (WebElement) field.get(pageObject);
            }
        }
        return null;
    }
}
