package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static pages.BaseSetupPage.webDriver;

public class HomePage extends BasePage {
    private static final String NEW_PATIENT_LOCATOR = "//a[contains(@class,'btn-sm')]/span[text()='New Patient']";
    private static final By HOME_PAGE_LOCATOR = By.xpath("//div[@id='breadcrumb']//span[contains(.,'Home')]");
    private static final String HOME_PAGE_NAME = "Home Page";
    private static final String LINK_TEMPLATE= "//*[text()='%1$s']";

    public HomePage() {
        super(HOME_PAGE_LOCATOR, HOME_PAGE_NAME);
    }


    /**
     * Links of Homeform
     */
    public enum HomeFormLinks {
        //START -Administrative Section
        OASIS_NURSE_SOC("OASIS Nurse Start of Care"),
        OASIS_DISCHARGE("OASIS Discharge"),
        OASIS_PT_RESUMPTION_OF_CARE("OASIS PT Resumption of Care"),
        INBOUND_DOCUMENTS("Inbound Documents");
        //END -MISCELLENEOUS

        private String link;

        /**
         * Define Links
         * @param link - value of link
         */
        private HomeFormLinks(String link) {
            this.link = link;
        }

        /**
         * Get value of link
         * @return link
         */
        public String toString() {
            return link;
        }
    }

    public void selectNewPatient() {
        WebDriverWait wait=new WebDriverWait(webDriver,60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(NEW_PATIENT_LOCATOR)));
        WebElement link = webDriver.findElement(By.xpath(NEW_PATIENT_LOCATOR));
        link.isDisplayed();
        link.click();

    }
    /**
     * To Click any link on any UI3 page using absolute text
     * @author deepthit
     * @param absoluteLinkText  - absolute text of link
     */
    public void clickLinkUsingAbsoluteTextOnUI3(String absoluteLinkText) {
        WebElement link = webDriver.findElement(By.xpath(String.format(LINK_TEMPLATE, absoluteLinkText)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", link);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();",link);
    }
}
