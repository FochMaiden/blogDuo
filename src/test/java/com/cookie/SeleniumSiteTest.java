package com.cookie;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.cookie.model.User;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;


/**
 * Created by FochMaiden
 */
public class SiteTest {

        private static WebDriver browser;


        @BeforeClass
        public static void setup() {
            System.setProperty("webdriver.chrome.driver", "/Users/FochMaiden/Downloads/chromedriver");
                browser = new ChromeDriver();
                browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        @Test
        public void loginTest() throws Exception {
            browser.get("http://localhost:8080");
            browser.findElement(By.id("login")).click();

            browser.findElement(By.id("email")).sendKeys("gosia.xq@gmail.com");
            browser.findElement(By.id("password")).sendKeys("qweqwe123");

            browser.findElement(By.id("submit")).click();

            assertEquals("Welcome Gosia", browser.findElement(By.tagName("span")).getText());
        }

        @Test
        public void failedLoginTest() throws Exception{
            browser.get("http://localhost:8080");
            browser.findElement(By.id("login")).click();

            browser.findElement(By.id("email")).sendKeys("");
            browser.findElement(By.id("password")).sendKeys("");

            browser.findElement(By.id("submit")).click();


            assertEquals("Email luh hasło nieprawidłowe",browser.findElement(By.id("error")).getText() );
        }

        @Test
        public void logoutTest() throws Exception {
            browser.get("http://localhost:8080");
            browser.findElement(By.id("login")).click();

            browser.findElement(By.id("email")).sendKeys("gosia.xq@gmail.com");
            browser.findElement(By.id("password")).sendKeys("qweqwe123");

            browser.findElement(By.id("submit")).click();

            browser.findElement(By.id("logout")).click();

            assertEquals("Welcome",browser.findElement(By.id("welcome")).getText());

        }

        @Test
        public void registerTest() throws Exception{
            browser.get("http://localhost:8080");
            browser.findElement(By.id("register")).click();

            browser.findElement(By.id("name")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
            browser.findElement(By.id("email")).sendKeys(RandomStringUtils.randomAlphanumeric(12) +"@gmail.com");
            browser.findElement(By.id("password")).sendKeys(RandomStringUtils.randomAlphanumeric(12));

            browser.findElement(By.id("submit")).click();
            assertEquals("Użytkownik został pomyślnie zarejestrowany",browser.findElement(By.className("successMSG")).getText() );
        }


    @Test
    public void failedRegisterTest() throws Exception{
        browser.get("http://localhost:8080");
        browser.findElement(By.id("register")).click();

        browser.findElement(By.id("name")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("email")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("password")).sendKeys(RandomStringUtils.randomAlphanumeric(12));

        browser.findElement(By.id("submit")).click();
        assertEquals("Proszę podać prawidłowy Email",browser.findElement(By.className("validation-message")).getText() );

    }



    @Test
    public void createBlogPost() throws Exception{
        browser.get("http://localhost:8080");
        browser.findElement(By.id("login")).click();

        browser.findElement(By.id("email")).sendKeys("gosia.xq@gmail.com");
        browser.findElement(By.id("password")).sendKeys("qweqwe123");

        browser.findElement(By.id("submit")).click();

        browser.findElement(By.id("nowypost")).click();

        browser.findElement(By.id("title")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("text")).sendKeys(RandomStringUtils.randomAlphanumeric(12));

        browser.findElement(By.id("post")).click();

        assertEquals("Pomyślnie stworzono post",browser.findElement(By.className("successMSG")).getText() );


    }

        @AfterClass
        public static void tearDown() {

            browser.quit();
        }

}
