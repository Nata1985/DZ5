package lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;
import static utils.SeleniumHelper.waitElement;

class MyWarehouseTests {
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
    void Bill() throws InterruptedException {
        login();
        WebElement el = waitElement(driver, "//*[contains(@src, 'procurement')]");
        el.click();
        driver.findElement(By.xpath("//span[text()='Счета поставщиков']")).click();
        driver.findElement(By.xpath("//span[text()='Счет']")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//div[contains(.,'Контрагент') and @class='gwt-Label']/../../td[2]/div/div/div[1]/div[1]/input")).click();
        WebElement we = driver.findElement(By.xpath("//div[contains(.,'Контрагент') and @class='gwt-Label']/../../td[2]/div/div/div[1]/div[1]/input"));
        we.sendKeys("розничный покупатель");
        Thread.sleep(10000);
        we.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//span[text()='Сохранить']")).click();
        Thread.sleep(10000);
        Assertions.assertEquals(driver.findElement(By.xpath("//div [.=\"Счет создан\"]")).isDisplayed(), true);
        assertThat(driver.findElement(By.xpath("//div [.=\"Счет создан\"]")), isDisplayed());
    }

    @Test
    void CreditSlip() throws InterruptedException {
        login();
        Thread.sleep(10000);
        WebElement el = waitElement(driver, "//*[contains(@src, 'money')]");
        el.click();
        driver.findElement(By.xpath("//span[text()='Платежи']")).click();
        driver.findElement(By.xpath("//span[text()='Приход']")).click();
        driver.findElement(By.xpath("//td[text()='Приходный ордер']")).click();
        driver.findElement(By.xpath("//div[.='Контрагент' and @class='gwt-Label']/../../td[4]/div/div/div/div/input")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//div[@title='natafufka23'] ")).click();
        driver.findElement(By.xpath("//span[text()='Сохранить']")).click();
        Thread.sleep(10000);
        Assertions.assertEquals(driver.findElement(By.xpath("//div[.=\"Приходный ордер создан\"]")).isDisplayed(), true);
        assertThat(driver.findElement(By.xpath("//div[.=\"Приходный ордер создан\"]")), isDisplayed());
    }
    @Test
    void Contract () throws InterruptedException {
        login();
        Thread.sleep(10000);
        WebElement el = waitElement(driver, "//*[contains(@src, \"contragents\")]");
        el.click();
        driver.findElement(By.xpath("//span[text()='Договоры']")).click();
        driver.findElement(By.xpath("//div[@role=\"button\"and @class=\"b-popup-button b-popup-button-enabled b-popup-button-gray tutorial-step-add-item\"]")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//span[.='Контрагент' and @class=\"b-validation-label required\"]/../../../td[2]/div/div/div/input")).sendKeys("розничный покупатель");;
        Thread.sleep(10000);
        driver.findElement(By.xpath("//span[.='Контрагент' and @class=\"b-validation-label required\"]/../../../td[2]/div/div/div/input")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//span[text()='Сохранить']")).click();
        Thread.sleep(10000);
        Assertions.assertEquals(driver.findElement(By.xpath("//div[.=\"Договор создан\"]")).isDisplayed(), true);
        assertThat(driver.findElement(By.xpath("//div[.=\"Договор создан\"]")), isDisplayed());
    }

    @Test
    void Return() throws InterruptedException {
        login();
        Thread.sleep(10000);
        WebElement el = waitElement(driver, "//*[contains(@src, \"selling\")]");
        el.click();
        driver.findElement(By.xpath("//span[text()='Возвраты покупателей']")).click();
        driver.findElement(By.xpath("//span[text()='Возврат']")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//div[.='Контрагент' and @class='gwt-Label']/../../td[2]/div/div/div/div/input")).sendKeys("розничный покупатель");;
        Thread.sleep(10000);
        driver.findElement(By.xpath("//div[.='Контрагент' and @class='gwt-Label']/../../td[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//span[text()='Сохранить']")).click();
        Thread.sleep(10000);
        Assertions.assertEquals(driver.findElement(By.xpath("//div[.=\"Возврат создан\"]")).isDisplayed(), true);
        assertThat(driver.findElement(By.xpath("//div[.=\"Возврат создан\"]")), isDisplayed());
    }





    @AfterEach
    void tearDown() {
        driver.quit();
    }

    void login() throws InterruptedException {
        driver.get("https://www.moysklad.ru/login/");
        driver.findElement(By.xpath("//input[@name='j_username']")).sendKeys("admin@natafufka23");
        driver.findElement(By.xpath("//input[@name='j_password']")).sendKeys("601f95388e");
        driver.findElement(By.xpath("//*[@type='submit']")).click();
    }


}


