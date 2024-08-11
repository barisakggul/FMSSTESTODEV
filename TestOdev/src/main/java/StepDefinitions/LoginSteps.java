package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.DashboardPage;
import pages.LoginPage;

import java.time.Duration;

public class LoginSteps {

    public WebDriver driver;
    private LoginPage loginPage;

    public LoginSteps() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Given("user visits the websites")
    public void user_visits_the_websites() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @When("user enter username {string} and password {string}")
    public void user_enter_username_and_password(String username, String password) {
        loginPage = new LoginPage(driver);
        loginPage.doLogin(username, password);
    }

    @Then("user can view the dashboard page")
    public void user_can_view_the_dashboard_page() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.userInDashboard();
    }

    @Then("browser closes")
    public void browser_closes() {
        driver.quit();
    }

    @Then("user can't view the dashboard page")
    public void userCanTViewTheDashboardPage() {
        loginPage.assertingErrorMessage();
    }
}