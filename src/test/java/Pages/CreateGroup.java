package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateGroup {

    WebDriver driver;
    public CreateGroup(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="input[name='groups.name']") private WebElement addPartnetBtn;
    @FindBy(css = "input[name='checkbox_63']") private  WebElement firstCheckBox;
    @FindBy(xpath = "//span[contains(text(),'ADD PARTNER')]") private WebElement groupNameField;


    public void createGroupChecking() throws InterruptedException {
        Thread.sleep(1000);
        scrollToElement(addPartnetBtn);
        addPartnetBtn.click();
        firstCheckBox.click();
        //groupNameField.sendKeys(" ");


    }

    private void scrollToElement(WebElement element){
        Actions actions=new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }
}
