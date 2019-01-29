package Pages;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    @FindBy(css="button[aria-label='Delete']") private WebElement deleteButton;



    public void createGroupErrorsChecking() throws InterruptedException {
        Helper helper= new Helper(driver);
        helper.waitForClikable(addPartnerBtn);
        addPartnerBtn.click();
        Thread.sleep(500);
        Assert.assertTrue(allChechboxes.size()!=0);
        allChechboxes.get(0).click();
        addPartnrBtn.click();


        //All fields are empty
        helper.waitForClikable(sendToPartnersBtn);
        sendToPartnersBtn.click();
        helper.assertSize(7,requiredFields);
        Assert.assertEquals(true,requiredFieldCountry.isDisplayed());
        helper.assertSize(4,errors_1);
        helper.assertSize(3,errors_2);
        helper.assertSize(1,errors_3);
        WebElement hint_1=driver.switchTo().activeElement();
        helper.assertText("e.g. Italy Fashion Tour",hint_1,"placeholder");

        //Group Request Name
        helper.sendKeys(requiredFields,0," ");
        sendToPartnersBtn.click(); sendToPartnersBtn.click();
        helper.assertSize(6,requiredFields);
        helper.assertSize(3,errors_1);
        helper.assertSize(3,errors_2);
        WebElement hint_2=driver.switchTo().activeElement();
        helper.assertText("e.g. Italian, Greek, Swedish",hint_2,"placeholder");

        //Group Nationality
        helper.sendKeys(requiredFields,0," ");
        sendToPartnersBtn.click();
        helper.assertSize(5,requiredFields);
        helper.assertSize(2,errors_1);
        helper.assertSize(3,errors_2);
        WebElement hint_3=driver.switchTo().activeElement();
        helper.assertText("e.g. 50",hint_3,"placeholder");

         //Number of Guests
        helper.sendKeys(requiredFields,0,"1");
        sendToPartnersBtn.click();
        helper.assertSize(4,requiredFields);
        helper.assertSize(2,errors_1);
        helper.assertSize(2,errors_2);
        WebElement hint_4=driver.switchTo().activeElement();
        helper.assertText("e.g. 25",hint_4,"placeholder");

        //Number of Rooms
        helper.sendKeys(requiredFields,0,"76");
        sendToPartnersBtn.click();
        helper.assertSize(3,requiredFields);
        helper.assertSize(2,errors_1);
        helper.assertSize(1,errors_2);
        WebElement hint_5=driver.switchTo().activeElement();
        helper.assertText("e.g. Krakow or Moscow",hint_5,"placeholder");

        //Group Departure Location
        helper.sendKeys(requiredFields,0," ");
        sendToPartnersBtn.click();
        helper.assertSize(2,requiredFields);
        helper.assertSize(1,errors_1);
        helper.assertSize(1,errors_2);
        WebElement hint_6=driver.switchTo().activeElement();
        helper.assertText("e.g. Rome or New York",hint_6,"placeholder");

        //City #1
        helper.sendKeys(requiredFields,0," ");
        sendToPartnersBtn.click();
        helper.assertSize(1,requiredFields);
        helper.assertSize(0,errors_1);
        helper.assertSize(1,errors_2);
        WebElement hint_7=driver.switchTo().activeElement();
        Assert.assertEquals(true, hint_7.isEnabled());

        //Check-in Check-out
        requiredFields.get(0).click();
        dateStartCells.get(2).click();
        dateEndCells.get(2).click();
        Thread.sleep(500);
        sendToPartnersBtn.click();
        helper.assertSize(0,requiredFields);
        helper.assertSize(0,errors_1);
        helper.assertSize(0,errors_2);

        //Country
        requiredFieldCountry.click();
        helper.waitForClikable(countryListBoxElement);
        countryListBoxElement.sendKeys(Keys.ENTER);
        helper.getWebDriverWait();
        helper.assertSize(0,errors_3);

        //Disable Button Send To Partners Without Selected Partner
        Thread.sleep(500);
        deleteButton.click();
        Assert.assertEquals(false,sendToPartnersBtn.isEnabled());
    }


}
