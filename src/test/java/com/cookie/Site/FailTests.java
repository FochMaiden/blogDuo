package com.cookie.Site;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.*;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;


/**
 * Created by FochMaiden
 */
public class FailTests {

    private static WebDriver browser;


    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/FochMaiden/Downloads/chromedriver");// mac - C:\Users\Gosia\Downloads\chromedriver.exe
        browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Before
    public void userTriesToLoginAndSucceeds(){
        browser.get("http://localhost:8080");
        browser.findElement(By.id("login")).click();
        browser.findElement(By.id("email")).sendKeys("gosia.xq@gmail.com");
        browser.findElement(By.id("password")).sendKeys("qweqwe123");
        browser.findElement(By.id("submit")).click();
    }

    @Test
    public void userTriesToLoginAndFails() throws Exception{
        browser.get("http://localhost:8080");
        browser.findElement(By.id("login")).click();
        browser.findElement(By.id("email")).sendKeys("");
        browser.findElement(By.id("password")).sendKeys("");
        browser.findElement(By.id("submit")).click();


        assertEquals("Email luh hasło nieprawidłowe",browser.findElement(By.id("error")).getText() );
    }



    @Test
    public void userTriesToRegisterAndFails() throws Exception{
        browser.get("http://localhost:8080");
        browser.findElement(By.id("register")).click();
        browser.findElement(By.id("name")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("email")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("password")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("submit")).click();


        assertEquals("Proszę podać prawidłowy Email",browser.findElement(By.className("validation-message")).getText() );

    }



    @Test
    public void userTriesToreateNewPostWithTheSameTitile() throws Exception{

        browser.findElement(By.id("nowypost")).click();
        browser.findElement(By.id("title")).sendKeys("a");
        browser.findElement(By.id("text")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("post")).click();
        browser.findElement(By.id("nowypost")).click();
        browser.findElement(By.id("title")).sendKeys("a");
        browser.findElement(By.id("text")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("post")).click();


        assertEquals("Post o danym tytule już istnieje",browser.findElement(By.className("error")).getText() );
    }



    @Test
    public void userTriesToEditThePostAndResigns() throws Exception{

        browser.findElement(By.id("nowypost")).click();
        browser.findElement(By.id("title")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("text")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("post")).click();
        browser.findElement(By.id("posty")).click();

        browser.findElement(By.id("edytuj")).click();
        browser.findElement(By.id("cancel")).click();

        String url =  browser.getCurrentUrl();


        assertEquals("http://localhost:8080/admin/blogPosts",url);
    }


    @AfterClass
    public static void tearDown() {

        browser.quit();
    }

}
