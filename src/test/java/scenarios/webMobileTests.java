package scenarios;

import static util.TestDataReader.getTestData;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import setup.BaseTest;

public class webMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "Verify Google web page search functionality")
    public void simpleWebTest()
        throws NoSuchFieldException, IllegalAccessException, InstantiationException {

        System.out.println("Simple Google web page test is starting");

        getDriver().get(getTestData("url"));

        waitForPageToLoadCompletely();

        getPo().getWelement("GoogleSearchWebPage","searchField").sendKeys(getTestData("searchPhrase"));
        getPo().getWelement("GoogleSearchWebPage","searchField").sendKeys(Keys.ENTER);

        waitForPageToLoadCompletely();

        assert getPo().getWelement("GoogleResultsWebPage","searchResults").isDisplayed() : "No results displayed";

        // Log that test finished
        System.out.println("Simple Google web page test is completed");
    }
}
