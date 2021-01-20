package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;

import static pages.BaseSetupPage.webDriver;

public class BasePage {
    WebDriverWait wait = null;
    private static final String btnSave = "//input[@value='Save']";
    /**
     * Parameterized constructor
     * @param formLocator
     * @param title
     */
    public BasePage(By formLocator, String title) {

        wait=new WebDriverWait(webDriver,20);
        // Wait till the element is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(formLocator));
        WebElement pageObject = webDriver.findElement(formLocator);
        Assert.assertTrue(pageObject.isDisplayed(), "Page object is not found");

    }
    /**
     * Get the unic suffix based on the current date
     */
    public static String getTimestamp() {
        return getCurrentDate("yyyyMMddHHmmss");
    }

    /**
     * get current date in the custom pattern
     */
    public static String getCurrentDate(String pattern) {
        return formatDate(new Date(), pattern);
    }
    /**
     * Format date to string using custom pattern
     * @param date - date to be formatted
     * @param pattern - custom pattern of the date
     */
    public static String formatDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * Click via Action.
     */
    public void clickViaAction(WebElement element) {
        Coordinates cor=((Locatable)element).getCoordinates();
        cor.inViewPort();
        Actions action = new Actions(webDriver);
        action.moveToElement(element);
        action.click(element);
        action.perform();
    }

    public void saveForm(){
        WebElement element = webDriver.findElement(By.xpath(btnSave));
        element.click();
    }
}
