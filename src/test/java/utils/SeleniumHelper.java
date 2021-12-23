package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumHelper {
    public static final int MAX_WAIT_RETRY = 10;

    public static WebElement waitElement(WebDriver driver, String xPath) throws InterruptedException {
       WebElement we = null;
       for (int i = 0; i < MAX_WAIT_RETRY; i++) {
           try {
               we = driver.findElement(By.xpath(xPath));
               return we;
           } catch (NoSuchElementException e) {
               Thread.sleep(1000);
           }
       }
       return null;
   }
}
