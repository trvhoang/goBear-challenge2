import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.InsuranceTravel;
import pages.TravelResult;


public class InsuranceTest {
    WebDriver driver = new ChromeDriver();
    InsuranceTravel insurancePage = new InsuranceTravel(driver);
    TravelResult resultPage = new TravelResult(driver);


    @BeforeClass
    public void setup(){
        driver.manage().deleteAllCookies();
        driver.get("https://www.gobear.com/ph?x_session_type=UAT");
        driver.manage().window().maximize();
//        WebDriverWait wait = new WebDriverWait(driver, 20);
//        JavascriptExecutor js = (JavascriptExecutor)driver;
//        wait.until((ExpectedCondition<Boolean>) wd -> js.executeScript("return document.readyState").toString().equals("complete"));
    }

    @Test(priority = 0)
    public void verifyInsurance(){
//        InsuranceTravel insurancePage = new InsuranceTravel(driver);
        insurancePage.goResult();
    }

    @Test(priority = 1)
    public void verifyTravelDefault(){
        Assert.assertTrue(resultPage.verifyCard());
    }

    @Test(priority = 2)
    public void verifyTravelRadio(){
        try {
            Assert.assertTrue(resultPage.verifyRadio());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 3)
    public void verifyTravelChkb(){
        try {
            Assert.assertTrue(resultPage.verifyChkb());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 4)
    public void verifyTravelSld(){
        Assert.assertTrue(resultPage.verifySld());
    }

    @Test(priority = 5)
    public void verifyTravelDrpBox() throws InterruptedException {
        Assert.assertTrue(resultPage.verifyDrpBox());
    }

    @Test(priority = 6)
    public void verifyDatePicker(){
        Assert.assertTrue(resultPage.verifyDatePicker("21-April","to"));
    }

}

