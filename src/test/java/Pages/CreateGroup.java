package Pages;

import net.bytebuddy.asm.Advice;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CreateGroup {

    WebDriver driver;
    public CreateGroup(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//span[contains(text(),'ADD PARTNER')]") private WebElement addPartnerBtn;
    @FindBy(css="tr[class] input") private List<WebElement> allChechboxes;
    @FindBy(xpath = "//button[2]/span[1][contains(text(),'ADD PARTNER')]") private WebElement addPartnrBtn;
    @FindBy(css = "button[type='submit']") private WebElement sendToPartnersBtn;
    @FindBy(css = "input[aria-invalid='true']") private List<WebElement> requiredFields;
    @FindBy(xpath = "//h2[contains(text(),'DESTINATION(S) DETAILS')]/..//div[contains(@aria-pressed,'false')]") private WebElement requiredFieldCountry;
    @FindBy(xpath = "//span[contains(text(),'Required')]") private List<WebElement> errors_1;
    @FindBy(xpath = "//p[contains(text(),'Required')]") private List<WebElement> errors_2;
    @FindBy(xpath = "//p[contains(text(),'error')]") private  List<WebElement> errors_3;
    @FindBy(xpath = "//tr[2]/td[5]") private List<WebElement> dateStartCells;
    @FindBy(xpath = "//tr[4]/td[6]") private List<WebElement> dateEndCells;
    @FindBy(css="[role] [role='option']:nth-of-type(1)") private WebElement countryListBoxElement;



    public void createGroupErrorsChecking() throws InterruptedException {
        Thread.sleep(1000);
        scrollToElement(addPartnerBtn);
        addPartnerBtn.click();
        Thread.sleep(1000);
        Assert.assertTrue(allChechboxes.size()!=0);
        allChechboxes.get(0).click();
        addPartnrBtn.click();
        Thread.sleep(1000);

        //All fields are empty
        sendToPartnersBtn.click();
        Assert.assertEquals(7,requiredFields.size());
        Assert.assertEquals(true,requiredFieldCountry.isEnabled());
        Assert.assertEquals(4, errors_1.size());
        Assert.assertEquals(3,errors_2.size());
        Assert.assertEquals(1,errors_3.size());

         //Group Request Name
        requiredFields.get(0).sendKeys(" ");
        sendToPartnersBtn.click(); sendToPartnersBtn.click();
        Assert.assertEquals(6,requiredFields.size());
        Assert.assertEquals(3, errors_1.size());
        Assert.assertEquals(3,errors_2.size());
        driver.switchTo().activeElement();
//        Assert.assertEquals();

         //Number of Guests
        requiredFields.get(1).sendKeys("1");
        sendToPartnersBtn.click();
        Assert.assertEquals(5,requiredFields.size());
        Assert.assertEquals(3, errors_1.size());
        Assert.assertEquals(2,errors_2.size());

        //Group Departure Location
        requiredFields.get(2).sendKeys(" ");
        sendToPartnersBtn.click();
        Assert.assertEquals(4,requiredFields.size());
        Assert.assertEquals(2, errors_1.size());
        Assert.assertEquals(2,errors_2.size());

        //City #1
        requiredFields.get(2).sendKeys(" ");
        sendToPartnersBtn.click();
        Assert.assertEquals(3,requiredFields.size());
        Assert.assertEquals(1, errors_1.size());
        Assert.assertEquals(2,errors_2.size());

        //Check-in Check-out
        requiredFields.get(2).click();
        dateStartCells.get(2).click();
        dateEndCells.get(2).click();
        Thread.sleep(500);
        sendToPartnersBtn.click();
        Assert.assertEquals(2,requiredFields.size());
        Assert.assertEquals(1, errors_1.size());
        Assert.assertEquals(1,errors_2.size());

        //Number of Rooms
        Thread.sleep(500);
        requiredFields.get(1).sendKeys("76");
        sendToPartnersBtn.click();
        Assert.assertEquals(1,requiredFields.size());
        Assert.assertEquals(1, errors_1.size());
        Assert.assertEquals(0,errors_2.size());

        //Group Nationality
        requiredFields.get(0).sendKeys(" ");
        sendToPartnersBtn.click();
        Assert.assertEquals(0,requiredFields.size());
        Assert.assertEquals(0, errors_1.size());
        Assert.assertEquals(0,errors_2.size());

        //Country
        requiredFieldCountry.click();
        Thread.sleep(500);
        countryListBoxElement.sendKeys(Keys.ENTER);
        Thread.sleep(500);
        Assert.assertEquals(0,errors_3.size());

        //
    }








    private void scrollToElement(WebElement element){
        Actions actions=new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }
}
