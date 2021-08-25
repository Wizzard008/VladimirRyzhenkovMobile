package setup;

import org.openqa.selenium.WebElement;

public interface IPageObject {

    WebElement getWelement(String pageName, String weName) throws NoSuchFieldException, IllegalAccessException, InstantiationException;

}
