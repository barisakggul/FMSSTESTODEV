package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    @FindBy(xpath ="//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    WebElement dashboardTitle;

    public DashboardPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void userInDashboard(){
        Assert.assertEquals(dashboardTitle.getText(),"Dashboard");
    }
}
