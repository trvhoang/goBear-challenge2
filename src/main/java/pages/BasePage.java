package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public abstract class BasePage {

    public abstract void waitForDisplayed(WebElement we);

    public abstract void waitForClick(WebElement we);

    public abstract void waitAndClick(By by);
}
