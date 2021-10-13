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

        getPo().getWelement("LoginPage","registerBtn").click();

        getPo().getWelement("RegistrationPage","registrationEmail").sendKeys(email);
        getPo().getWelement("RegistrationPage","registrationUsername").sendKeys(username);
        getPo().getWelement("RegistrationPage","registrationPassword").sendKeys(password);
        getPo().getWelement("RegistrationPage","registrationConfirmPassword").sendKeys(password);
        getPo().getWelement("RegistrationPage","agreementBtn").click();
        getPo().getWelement("RegistrationPage","registerNewAccountBtn").click();

        getPo().getWelement("LoginPage","loginEmail").sendKeys(email);
        getPo().getWelement("LoginPage","loginPwd").sendKeys(password);
        getPo().getWelement("LoginPage","signInBtn").click();

        assert getPo().getWelement("BudgetPage","frameTitle").getText()
                      .contains(expectedWebPageTitle) : "Actual App page differ from expected";

        System.out.println("Simplest Android native test is done");
    }
}
