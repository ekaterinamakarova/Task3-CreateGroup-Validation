package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Initial {
    WebDriver driver;
    public  Initial(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "a[href='/welcome/sign-in']") private WebElement signStr;

    public void toSignPage(){
        signStr.click();
    }
}
