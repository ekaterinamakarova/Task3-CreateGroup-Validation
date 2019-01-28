import Pages.CreateGroup;
import Pages.Initial;
import Pages.Main;
import Pages.SignIn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;


public class TestClass {
    private ChromeDriver driver;
    FileReaderClass readerClass = new FileReaderClass();


    @BeforeTest
    public void setUp() throws IOException {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "normal");
        options.addArguments("start-maximized");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);
        driver.get(readerClass.readFromFile(0));
        }


        @Test(description = "Login with credentials")
        public void login () throws IOException {
            Initial initial = new Initial(driver);
            SignIn signIn = new SignIn(driver); Main main = new Main(driver);
            initial.toSignPage();
            signIn.signin(readerClass.readFromFile(1), readerClass.readFromFile(2));

        }

        @Test (dependsOnMethods = {"login"}, description = "Create Group page checking")
        public void createGroupchecking() throws InterruptedException {
            Main main=new Main(driver);
            CreateGroup createGroup = new CreateGroup(driver);
            main.toCreateGroupPage();
            createGroup.createGroupChecking();
        }

        @AfterTest
        public void exit () {
          //  driver.quit();
        }



    }

