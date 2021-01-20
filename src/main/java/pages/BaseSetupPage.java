package pages;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseSetupPage {
    public static RemoteWebDriver webDriver = null ;
    private static final String CREDENTIALS_TEMPLATE = "//div[@id='left']//input[contains(@name,'%1$s')]";
    private String txbLogin = String.format(CREDENTIALS_TEMPLATE, "username");
    private String txbPassword = String.format(CREDENTIALS_TEMPLATE, "password");

//    public WebDriver webDriver;

    @BeforeClass
    @Parameters({"browser", "os"})
    public void startDriver(String browser, String osName, ITestContext report) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//
//        //browser setup
//        if(browser.contains("Chrome")) {
//            desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
//        } else if (browser.contains("Firefox")) {
//            desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
//        }
//
//        // os setup
//        if(osName.contains("Linux")) {
////            desiredCapabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
//        } else if (osName.contains("Win")) {
//            desiredCapabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
//
//        } else if(osName.contains("Mac")){
//            desiredCapabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.MAC);
//        }
//
//
//        URL url = null;
//        try {
//            url = new URL("http://localhost:4444/wd/hub");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        webDriver = new RemoteWebDriver(url, desiredCapabilities);
//        webDriver.navigate().to("https://t2.devero.com/?site=qaautomation");

        String testcaseName = report.getCurrentXmlTest().getName();

        String host = "localhost";
        if(browser.contains("Chrome")) {
            desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
        } else if (browser.contains("Firefox")) {
            desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
        }
            desiredCapabilities.setCapability("testFileNameTemplate",testcaseName);
        if(System.getProperty("HUB_HOST")!=null) {
            host=System.getProperty("HUB_HOST");
        }

        String completeURL = "http://"+host+":4444/wd/hub";

        try {
            webDriver = new RemoteWebDriver(new URL(completeURL),desiredCapabilities);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        webDriver.get("https://t2.devero.com/?site=qaautomation");

        //driver.manage().window().maximize();

        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }


    public void login() {
        WebElement username = webDriver.findElement(By.xpath(txbLogin));
        username.sendKeys("sa");
        WebElement password = webDriver.findElement(By.xpath(txbPassword));
        password.sendKeys("testing123");
        WebElement submit = webDriver.findElement(By.id("submitButton"));
        submit.click();

    }
    
    @AfterClass
    public void closeBrowser()
    {
    	webDriver.close();
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
     * This method is used across the project to switch to rendered UI 2.0 form
     * page inside iframe if and only if angularVersion property in
     * selenium.properties is set to 3.1
     */
    public void switchToIframeIfPresent() {
        WebDriverWait wait=new WebDriverWait(webDriver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
        WebElement iframeElement =webDriver.findElement(By.tagName("iframe"));
        webDriver.switchTo().frame(iframeElement);
    }

    /**
     * This method is used across the project to switch out of rendered UI 2.0
     * page inside iframe to the parent UI 3.0 page if and only if
     * angularVersion property in selenium.properties is set to 3.1
     */
    public void switchBackFromIframeIfPresent() {
        webDriver.switchTo().defaultContent();
    }
}
