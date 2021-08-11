package pageObjects.webPages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchWebPage {

    @FindBy(css = "input[name='q']")
    WebElement searchField;

    public GoogleSearchWebPage(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);

    }


}
