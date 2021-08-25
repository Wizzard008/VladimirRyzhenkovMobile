package pageObjects.webPages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleResultsWebPage {

    @FindBy(xpath = "//*[@id='rso']//*[contains(text(),'EPAM')]")
    WebElement searchResults;

    public GoogleResultsWebPage(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);

    }

}
