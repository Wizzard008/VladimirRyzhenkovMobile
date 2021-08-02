package scenarios;

import static util.TestDataReader.getTestData;

import org.testng.annotations.Test;
import setup.BaseTest;

public class nativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "Verification of register, login, sign-in functionality")
    public void simpleNativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        System.out.println("Starting Simplest Android native test");

        String username = getTestData("username");
        String password = getTestData("password");
        String email = getTestData("email");
        String expectedWebPageTitle = getTestData("expectedWebPageTitle");

        getPo().getWelement("registerBtn").click();
        getPo().getWelement("registrationEmail").sendKeys(email);
        getPo().getWelement("registrationUsername").sendKeys(username);
        getPo().getWelement("registrationPassword").sendKeys(password);
        getPo().getWelement("registrationConfirmPassword").sendKeys(password);
        getPo().getWelement("registerNewAccountBtn").click();

        getPo().getWelement("loginEmail").sendKeys(email);
        getPo().getWelement("loginPwd").sendKeys(password);
        getPo().getWelement("signInBtn").click();

        assert getPo().getWelement("frameTitle").getText()
                      .equals(expectedWebPageTitle) : "Actual App page differ from expected";

        System.out.println("Simplest Android native test is done");
    }
}
