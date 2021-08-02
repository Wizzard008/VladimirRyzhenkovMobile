package scenarios;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import setup.BaseTest;

public class webMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "Verify Google web page search functionality")
    public void simpleWebTest()
        throws NoSuchFieldException, IllegalAccessException, InstantiationException {

        System.out.println("Simple Google web page test is starting");

        getDriver().get("https://www.google.com/");

        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), 10).until(
            wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        getPo().getWelement("searchField").sendKeys("EPAM");
        getPo().getWelement("searchField").sendKeys(Keys.ENTER);

        assert getPo().getWelement("searchResults").isDisplayed() : "No results displayed";

        // Log that test finished
        System.out.println("Simple Google web page test is completed");
    }
}
