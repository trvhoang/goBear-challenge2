package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TravelResult extends BasePage{
    WebDriver driver;
    public TravelResult(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void waitForDisplayed(WebElement we){
        WebDriverWait wait = new WebDriverWait(driver , 15);
        wait.until(ExpectedConditions.visibilityOf(we));
    }

    public void waitForClick(WebElement we){
        WebDriverWait wait = new WebDriverWait(driver , 15);
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


    @FindBy(how = How.CSS, using = "#travel-quote-list > div:nth-child(2) > div.grid-row")
    private WebElement cardList;

    public boolean verifyCard(){

        waitForDisplayed(cardList);
//        waitForClick(cardList);

        int noOfCard = cardList.findElements(By.xpath("//div[@class=\'col-sm-4 card-full\']")).size();
        if(noOfCard > 3){
            return true;
        }
        return false;
    }

    //promotions
    @FindBy(how = How.XPATH, using = "//*[@id=\'collapseFilter\']/div[2]/div/div[2]/div")
    private WebElement promRad;
    @FindBy(how = How.XPATH, using="//*[@id=\'collapseFilter\']/div[2]/div/div[1]/div")
    private WebElement allRad;
    public boolean verifyRadio() throws InterruptedException {
        waitForDisplayed(promRad);
        waitForClick(promRad);
        waitForDisplayed(allRad);
        waitForClick(allRad);

        promRad.click();
        int weTest = driver.findElements(By.cssSelector("#travel-quote-list > div:nth-child(2) > div")).size();
        if(weTest < 3){
            allRad.click();
            return true;
        }
        return false;
    }


    //insurers
    @FindBy(how = How.XPATH, using = "//*[@id=\'collapseFilter\']/div[3]/div/div/div[1]")
    private WebElement insurerChkb;
    public boolean verifyChkb() throws InterruptedException {
        waitForDisplayed(insurerChkb);
        waitForClick(insurerChkb);

        if (cardList.findElements(By.xpath("//div[@class=\'col-sm-4 card-full\']")).size() > 3){
            insurerChkb.click();
            waitForDisplayed(cardList);
            waitForClick(cardList);
            insurerChkb.click();
            return true;
        }
        return false;
    }

    //personal accident
    @FindBy(how = How.CSS, using = "#collapseSeemore > div:nth-child(1) > div > div > div.slider-handle.max-slider-handle.round")
    private WebElement peractSld;
    @FindBy(how = How.XPATH, using = "//*[@id=\'collapseSeemoreBtn\']/a[2]/link")
    private WebElement seemoreButton;

    public boolean verifySld(){
        waitForDisplayed(seemoreButton);
        waitForClick(seemoreButton);
        seemoreButton.click();

        waitForDisplayed(peractSld);
        waitForClick(peractSld);
        int sldWidth = peractSld.getSize().width;
        Actions moveSld = new Actions(driver);
        moveSld.clickAndHold(peractSld);
        moveSld.moveByOffset(-150,0).build().perform();
        peractSld.click();

        if(cardList.findElements(By.xpath("//div[@class=\'col-sm-4 card-full\']")).size() > 3){
            moveSld.clickAndHold(peractSld);
            moveSld.moveByOffset(150,0).build().perform();
            peractSld.click();
            return true;
        }

        return false;
    }

    //dropdown
    @FindBy(how = How.CSS, using = "#detailCollapse > div > div.field.destination-field > div > div > div > button")
    private WebElement drpBox;
    @FindBy(how = How.XPATH, using = "//span[@class='two-col' and text()= 'Morocco']")
    private WebElement option;

    public boolean verifyDrpBox() throws InterruptedException {
        waitForDisplayed(drpBox);
        waitForClick(drpBox);
        drpBox.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        Actions actions = new Actions(driver);
        actions.moveToElement(option);

        waitForDisplayed(option);
        actions.moveToElement(option).click().perform();

        if(cardList.findElements(By.xpath("//div[@class=\'col-sm-4 card-full\']")).size() >= 3){
            return true;
        }
        return false;
    }

    //calendar picker
    @FindBy(how = How.XPATH, using = "//div[@class='date-from']")
    private WebElement fromDate;
    @FindBy(how = How.XPATH, using = "//div[@class='date-to']")
    private WebElement toDate;
    @FindBy(how = How.XPATH, using = "//div[@class='datepicker-days']")
    private WebElement datePicker;
    @FindBy(how = How.XPATH, using = "//th[@class='next']")
    private WebElement datePickerNext;


    public boolean verifyDatePicker(String date, String type){
        By fdBy = By.xpath("//div[@class='date-from']");
        By tdBy = By.xpath("//div[@class='date-to']");
        By dnextBy = By.xpath("//th[@class='next']");
        String[] splitDateTime = date.split("-");
        String day = splitDateTime[0];
        String month = splitDateTime[1];


        switch(type){
            case "from":
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                waitForDisplayed(fromDate);
                waitForClick(fromDate);
                waitAndClick(fdBy);
                break;

            case "to":
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                waitForDisplayed(toDate);
                waitForClick(toDate);
                waitAndClick(tdBy);
                break;
        }

        waitForDisplayed(datePicker);
        waitForClick(datePicker);
        while (true){
            String pickerMonth = driver.findElement(By.xpath("//th[@class='datepicker-switch']")).getText();
            if(pickerMonth.contains(month)){
                break;
            }else {
                waitForDisplayed(datePickerNext);
                waitForClick(datePickerNext);
                waitAndClick(dnextBy);
            }
        }

        WebElement datePicker = driver.findElement(By.xpath("//table[@class='table-condensed']"));
        WebElement pickDate = datePicker.findElement(By.xpath(".//td[text()='" + day +"']"));
        By pdateBy = By.xpath(".//td[text()='" + day +"']");
        waitForClick(pickDate);
        waitAndClick(pdateBy);
        return true;
    }

}

