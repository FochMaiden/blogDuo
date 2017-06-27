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
public class Tests {

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
        public void userLogOut() throws Exception {

            browser.findElement(By.id("logout")).click();

            assertEquals("Welcome",browser.findElement(By.id("welcome")).getText());

        }


        @Test
        public void userTriesToRegisterAndSucceeds() throws Exception{
            browser.get("http://localhost:8080");
            browser.findElement(By.id("register")).click();
            browser.findElement(By.id("name")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
            browser.findElement(By.id("email")).sendKeys(RandomStringUtils.randomAlphanumeric(12) +"@gmail.com");
            browser.findElement(By.id("password")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
            browser.findElement(By.id("submit")).click();


            assertEquals("Użytkownik został pomyślnie zarejestrowany",browser.findElement(By.className("successMSG")).getText() );
        }





    @Test
    public void userTriesToCreateBlogPostAndSucceeds() throws Exception{

        browser.findElement(By.id("nowypost")).click();
        browser.findElement(By.id("title")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("text")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("post")).click();


        assertEquals("Pomyślnie stworzono post",browser.findElement(By.className("successMSG")).getText() );
    }


    @Test
    public void userTriesToDeleteBlogPostAndSucceeds() throws Exception{

        browser.findElement(By.id("nowypost")).click();
        browser.findElement(By.id("title")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("text")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("post")).click();
        browser.findElement(By.id("posty")).click();
        browser.findElement(By.id("usun")).click();
        String url =  browser.getCurrentUrl();


        assertEquals("http://localhost:8080/admin/blogPosts?message=Usunieto+posta",url);
    }

    @Test
    public void userTriesToEditBlogPostAndSucceeds() throws Exception{

        browser.findElement(By.id("nowypost")).click();
        browser.findElement(By.id("title")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("text")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("post")).click();
        browser.findElement(By.id("posty")).click();

        browser.findElement(By.id("edytuj")).click();
        browser.findElement(By.id("save")).click();

        String url =  browser.getCurrentUrl();


        assertEquals("http://localhost:8080/admin/blogPosts?message=zosta%C5%82+pomy%C5%9Blnie+zedytowany",url);
    }


        @AfterClass
        public static void tearDown() {

            browser.quit();
        }

}
