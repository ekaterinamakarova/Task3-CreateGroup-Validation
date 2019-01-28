package Pages;

import org.junit.Assert;
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
    @FindBy(css = "input[name='groups.name']") private WebElement groupNameField;


    public void createGroupChecking() throws InterruptedException {
        Thread.sleep(1000);
        scrollToElement(addPartnerBtn);
        addPartnerBtn.click();
        Thread.sleep(1000);
        Assert.assertTrue(allChechboxes.size()!=0);
        System.out.println(allChechboxes.size());
        allChechboxes.get(0).click();
        addPartnrBtn.click();
        Thread.sleep(1000);
        sendToPartnersBtn.click();
        }

        //groupNameField.sendKeys(" ");


    private void scrollToElement(WebElement element){
        Actions actions=new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }
}
