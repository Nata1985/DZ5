package lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

class Diary1Tests {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;


    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
    }

    @Test
        void Registration() throws InterruptedException {
        driver.get("https://diary.ru/user/registration");
        WebElement loginField = driver.findElement(By.id("signupform-username"));
        loginField.sendKeys("nata64790");
        driver.findElement(By.id("signupform-email")).sendKeys("Natafka29@rambler.ru");
        driver.findElement(By.id("chk_box_user_confirm")).click();
        driver.findElement(By.id("signup_btn")).click();
        Thread.sleep(10000);
        Assertions.assertEquals(driver.findElement(By.xpath("//p[.=\"Выберите дальнейшие действия:\"]")).isDisplayed(), true);
       // assertThat(driver.findElement(By.xpath("//p[.=\"Выберите дальнейшие действия:\"]")), isDisplayed());
    }
    @Test
         void Authorization() throws InterruptedException {
        driver.get("https://diary.ru/user/login");
        WebElement loginField2 = driver.findElement(By.id("loginform-username"));
        loginField2.sendKeys("Nastya1985");
        driver.findElement(By.id("loginform-password")).sendKeys("gjyxbr");
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
        driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")).click();
        Thread.sleep(10000);
        driver.switchTo().parentFrame();
        driver.findElement(By.id("login_btn")).click();
        Thread.sleep(10000);
        Assertions.assertEquals(driver.findElement(By.xpath("//h2[.=\"Популярное\"]")).isDisplayed(), true);
        assertThat(driver.findElement(By.xpath("//h2[.=\"Популярное\"]")), isDisplayed());
    }
    @AfterEach
    void tearDown() {
        driver.quit();
    }


}
