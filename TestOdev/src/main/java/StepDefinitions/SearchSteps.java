package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class SearchSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/akgul/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("User is logged")
    public void navigateToLoginPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        usernameField.sendKeys("Admin");
        passwordField.sendKeys("admin123");
        loginButton.click();
    }

    @And("User is on the dashboard page")
    public void userIsOnTheDashboardPage() {
        WebElement dashboardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[contains(text(),'Dashboard')]")));
        assertTrue("User is not on the dashboard page", dashboardElement.isDisplayed());
    }

    @When("User enters {string} in the search box")
    public void userEntersInTheSearchBox(String searchInput) {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
        searchBox.sendKeys(searchInput);
    }

    @When("User clicks the search button")
    public void userClicksTheSearchButton() {
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Search']")));
        searchButton.click();
    }

    @Then("The results page should display relevant results for {string}")
    public void theResultsPageShouldDisplayRelevantResultsFor(String expectedResult) {
        WebElement resultsDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")));
        String resultsText = resultsDiv.getText();
        assertTrue("Results should contain the keyword: " + expectedResult, resultsText.contains(expectedResult));
    }

    @Then("The results page should display {string} message")
    public void theResultsPageShouldDisplayMessage(String expectedMessage) {
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert-message']")));
        String actualMessage = messageElement.getText();
        assertTrue("Message should be: " + expectedMessage, actualMessage.contains(expectedMessage));
    }

    @When("User leaves the search box empty")
    public void userLeavesTheSearchBoxEmpty() {
        WebElement searchBox = driver.findElement(By.xpath("//input[@placeholder='Search']"));
        searchBox.clear();
    }

    @Then("An alert should be displayed saying {string}")
    public void anAlertShouldBeDisplayedSaying(String expectedAlert) {
        WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert-message']")));
        String actualAlert = alertElement.getText();
        assertTrue("Alert should display: " + expectedAlert, actualAlert.contains(expectedAlert));
    }

    @When("User enters {string} \\(spaces) in the search box")
    public void userEntersSpacesInTheSearchBox(String searchInput) {
        WebElement searchBox = driver.findElement(By.xpath("//input[@placeholder='Search']"));
        searchBox.sendKeys(searchInput);
    }

    @When("User enters {string} and {string} in the search box")
    public void userEntersAndInTheSearchBox(String searchInput1, String searchInput2) {
        WebElement searchBox = driver.findElement(By.xpath("//input[@placeholder='Search']"));
        searchBox.sendKeys(searchInput1 + " " + searchInput2);
    }

    @Then("The results page should display relevant results for {string} or {string}")
    public void theResultsPageShouldDisplayRelevantResultsForOr(String searchInput1, String searchInput2) {
        WebElement resultsDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")));
        String resultsText = resultsDiv.getText();
        boolean containsKeyword1 = resultsText.contains(searchInput1);
        boolean containsKeyword2 = resultsText.contains(searchInput2);
        assertTrue("Results should contain either: " + searchInput1 + " or " + searchInput2, containsKeyword1 || containsKeyword2);
    }

    @When("User enters a query that returns no results")
    public void userEntersAQueryThatReturnsNoResults() {
        WebElement searchBox = driver.findElement(By.xpath("//input[@placeholder='Search']"));
        searchBox.sendKeys("NonExistentQuery");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
