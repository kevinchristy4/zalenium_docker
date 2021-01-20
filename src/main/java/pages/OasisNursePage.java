package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static pages.BaseSetupPage.webDriver;

public class OasisNursePage extends BasePage {
    private static final String TEXTFIELD_TEMPLATE = "//input[@class='text-field']";
    private static final String  FINAL_PAGE = "//span[@class=\"haslink\" and contains(text(),\">\")]";
    private static final By MAIN_LOCATOR = By.xpath("//td[contains(.,'OASIS DISCHARGE ASSESSMENT')]");
    private static final String MAIN_NAME = "Oasis Discharge Assessment";
    /**
     * Parameterized constructor
     *
     * @param formLocator
     * @param title
     */
    public OasisNursePage() {
        super(MAIN_LOCATOR, MAIN_NAME);
    }

    /**
     * Fill all textboxes include calendars.
     */
    public void fillTextFields() {
        do {
                List<WebElement> list = webDriver.findElements(By.xpath(TEXTFIELD_TEMPLATE));
                for (WebElement txbElement : list) {
                    if (txbElement.isDisplayed()) {
                        String elementName = txbElement.getAttribute("name").replace("value(", "").replace(")", "");
                        String name = elementName;
                        txbElement.clear();
                        try {
                            String calendarAttribute = txbElement.getAttribute("onblur");
                            String date = getCurrentDate("MM/dd/yyyy");
                            if (!calendarAttribute.isEmpty())
                                txbElement.sendKeys(date);
                        } catch (Exception e) {
                            txbElement.sendKeys(name);
                            refillTestFields(txbElement, name);
                        }
                    }
                }
            if (webDriver.findElements(By.xpath(FINAL_PAGE)).size() != 0) {
                webDriver.findElement(By.xpath(FINAL_PAGE)).click();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            }   while (webDriver.findElements(By.xpath(FINAL_PAGE)).size() != 0) ;
    }

    /**
     * Assert text field value and refill if not equal with input.
     *
     * @param text    Input text
     * @param element Web element
     */
    private void refillTestFields(WebElement element, String text) {
        if (!element.getAttribute("value").equals(text)) {
            element.clear();
            element.sendKeys(text);
        }
    }

}
