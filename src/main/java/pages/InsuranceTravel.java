package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class InsuranceTravel extends BasePage {
    WebDriver driver;
    public InsuranceTravel(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void waitForDisplayed(WebElement we){
        WebDriverWait wait = new WebDriverWait(driver , 3);
        wait.until(ExpectedConditions.visibilityOf(we));
    }

    public void waitForClick(WebElement we){
        WebDriverWait wait = new WebDriverWait(driver , 3);
        wait.until(ExpectedConditions.elementToBeClickable(we));
    }

    public void waitAndClick(By by) {
        int attempts = 0;
        while(attempts < 2) {
            try {
                driver.findElement(by).click();
                break;
            } catch(StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    @FindBy(how = How.XPATH, using = "//a[@aria-controls='Insurance']")
    private WebElement elmInsurance;

    @FindBy(how = How.XPATH, using = "//a[@aria-controls='Travel']")
    private WebElement elmTravel;

    @FindBy(how = How.XPATH, using = "//button[text()='Show my results']")
    private WebElement elmResultButton;

    public void goResult(){
        List<WebElement> weList = new ArrayList<>();
        weList.add(elmInsurance);
        weList.add(elmTravel);
        weList.add(elmResultButton);

        for (WebElement we : weList) {
            waitForDisplayed(we);
            waitForClick(we);
            we.click();

        }
    }
}
