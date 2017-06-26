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
public class SeleniumSiteTest {

        private static WebDriver browser;


        @BeforeClass
        public static void setup() {
            System.setProperty("webdriver.chrome.driver", "/Users/FochMaiden/Downloads/chromedriver");// mac - C:\Users\Gosia\Downloads\chromedriver.exe
                browser = new ChromeDriver();
                browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        @Before
        @Ignore
        public void login(){
            browser.get("http://localhost:8080");
            browser.findElement(By.id("login")).click();
            browser.findElement(By.id("email")).sendKeys("gosia.xq@gmail.com");
            browser.findElement(By.id("password")).sendKeys("qweqwe123");
            browser.findElement(By.id("submit")).click();
    }


        @Test
        public void failedLoginTest() throws Exception{
            browser.get("http://localhost:8080");
            browser.findElement(By.id("login")).click();
            browser.findElement(By.id("email")).sendKeys("");
            browser.findElement(By.id("password")).sendKeys("");
            browser.findElement(By.id("submit")).click();

            File scrFile = ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("c:\\tmp\\failedLoginTest.png"));

            assertEquals("Email luh hasło nieprawidłowe",browser.findElement(By.id("error")).getText() );
        }

        @Test
        public void logoutTest() throws Exception {
                        browser.findElement(By.id("logout")).click();

            File scrFile = ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("c:\\tmp\\logoutTest.png"));

            assertEquals("Welcome",browser.findElement(By.id("welcome")).getText());

        }


        @Test
        @Ignore
        public void registerTest() throws Exception{
            browser.get("http://localhost:8080");
            browser.findElement(By.id("register")).click();
            browser.findElement(By.id("name")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
            browser.findElement(By.id("email")).sendKeys(RandomStringUtils.randomAlphanumeric(12) +"@gmail.com");
            browser.findElement(By.id("password")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
            browser.findElement(By.id("submit")).click();

            File scrFile = ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("c:\\tmp\\registerTest.png"));

            assertEquals("Użytkownik został pomyślnie zarejestrowany",browser.findElement(By.className("successMSG")).getText() );
        }


    @Test
@Ignore
    public void failedRegisterTest() throws Exception{
        browser.get("http://localhost:8080");
        browser.findElement(By.id("register")).click();
        browser.findElement(By.id("name")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("email")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("password")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("submit")).click();

        File scrFile = ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(""));

        assertEquals("Proszę podać prawidłowy Email",browser.findElement(By.className("validation-message")).getText() );

    }



    @Test
    public void createBlogPost() throws Exception{

        browser.findElement(By.id("nowypost")).click();
        browser.findElement(By.id("title")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("text")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("post")).click();

        File scrFile = ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("c:\\tmp\\createBlogPost.png"));

        assertEquals("Pomyślnie stworzono post",browser.findElement(By.className("successMSG")).getText() );
    }

    @Test
    public void createFailBlogPost() throws Exception{

        browser.findElement(By.id("nowypost")).click();
        browser.findElement(By.id("title")).sendKeys("a");
        browser.findElement(By.id("text")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("post")).click();
        browser.findElement(By.id("nowypost")).click();
        browser.findElement(By.id("title")).sendKeys("a");
        browser.findElement(By.id("text")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("post")).click();

        File scrFile = ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("c:\\tmp\\createBlogPost.png"));

        assertEquals("Post o danym tytule już istnieje",browser.findElement(By.className("error")).getText() );
    }

    @Test
    public void deleteBlogPost() throws Exception{

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
    public void editBlogPost() throws Exception{

        browser.findElement(By.id("nowypost")).click();
        browser.findElement(By.id("title")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("text")).sendKeys(RandomStringUtils.randomAlphanumeric(12));
        browser.findElement(By.id("post")).click();
        browser.findElement(By.id("posty")).click();

        browser.findElement(By.id("edytuj")).click();
        browser.findElement(By.id("save")).click();

        String url =  browser.getCurrentUrl();


        assertEquals("http://http://localhost:8080/admin/blogPosts?message=został+pomyślnie+zedytowany",url);
    }

    @Test
    public void editCancelBlogPost() throws Exception{

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
