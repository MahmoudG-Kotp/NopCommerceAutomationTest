package org.final_project_software_testing_amit.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.final_project_software_testing_amit.Hooks;
import org.final_project_software_testing_amit.pages.P02_Login;
import org.final_project_software_testing_amit.pages.P03_HomePage;
import org.openqa.selenium.support.Color;
import org.testng.asserts.SoftAssert;

public class D02_Login {
    private final P02_Login loginPage = new P02_Login();
    private final P03_HomePage homePage = new P03_HomePage();

    @Given("Navigate to login page")
    public void navigateToLoginPage() {
        Hooks.Browser.navigateTo(loginPage.URL);
    }

    @Given("User enter data {string} as email and {string} as password")
    public void enterEmailAndPassword(String email, String password) {
        loginPage.emailEditText.sendKeys(email);
        loginPage.passwordEditText.sendKeys(password);
    }

    @When("Login button clicked")
    public void loginButtonClicked() {
        loginPage.loginButton.click();
    }

    @Then("Login proceed successfully")
    public void assertLoginProceedSuccessfully() {
        SoftAssert assertLoginSuccessful = new SoftAssert();
        assertLoginSuccessful.assertEquals(Hooks.Browser.getDriver().getCurrentUrl(), homePage.URL);
        assertLoginSuccessful.assertTrue(homePage.myAccountTab.isDisplayed());
        assertLoginSuccessful.assertAll();
    }

    @Then("Login not proceeding")
    public void assertLoginNotProceeding() {
        SoftAssert assertLoginUnsuccessful = new SoftAssert();
        assertLoginUnsuccessful.assertTrue(loginPage.unsuccessfulLoginMessage.isDisplayed());
        assertLoginUnsuccessful.assertEquals(
                Color.fromString(loginPage.unsuccessfulLoginMessage.getCssValue("color")).asHex(),
                loginPage.unsuccessfulMessageColorHEX);
        assertLoginUnsuccessful.assertAll();
    }
}
