package Pages;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Helper {

    WebDriver driver;
    public Helper(WebDriver driver){
        this.driver=driver;
    }

    public Boolean waitForStaleness(WebElement element) {
        return getWebDriverWait().until(ExpectedConditions.stalenessOf(element));
    }


   public WebElement waitForClikable(WebElement element) {
        return getWebDriverWait().until(ExpectedConditions.elementToBeClickable(element));
    }



    public  Wait<WebDriver> getWebDriverWait() {
        return (Wait<WebDriver>) new FluentWait<>(driver)
                .withTimeout(15, SECONDS)
                .pollingEvery(250, MILLISECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }
    public void scrollToElement(WebElement element){
        Actions actions=new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public void assertSize(int i, List<WebElement> element){
        Assert.assertEquals(i,element.size());
    }

    public void assertText(String i, WebElement element, String attribute){
        Assert.assertEquals(i,element.getAttribute(attribute));
    }

    public void sendKeys(List<WebElement> element, int i, String string){
        element.get(i).sendKeys(string);
    }
}
