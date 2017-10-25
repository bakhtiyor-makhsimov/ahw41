package core;

import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class IE {
	
	static WebDriver driver;
	
	By by;
	
	public static boolean isPresent(final By by) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty()) return true;
		else return false;
		}

	public static void main(String[] args) throws InterruptedException {

		Logger logger = Logger.getLogger("");
		logger.setLevel(Level.OFF);

		String driverPath = "./resources/webdrivers/pc/IEDriverServer.exe";
		
		String url = "https://www.macys.com/shop/product/apple-watch-series-3-gps-cellular-42mm-space-black-stainless-steel-case-with-black-sport-band?ID=5302660&CategoryID=55285";
		
		if (!System.getProperty("os.name").contains("Windows")) {
			throw new IllegalArgumentException("Internet Explorer is available only on Windows");
			}
			
		DesiredCapabilities IEDesiredCapabilities = DesiredCapabilities.internetExplorer();
		IEDesiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		IEDesiredCapabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
		IEDesiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		IEDesiredCapabilities.setJavascriptEnabled(true);
		IEDesiredCapabilities.setCapability("enablePersistentHover", false);

		System.setProperty("webdriver.ie.driver", driverPath);

		driver = new InternetExplorerDriver(IEDesiredCapabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
//		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		driver.get(url);
//      //*[@id='productHeaderBox']/div[1]/div/div/div/div/a
//                  //*[@id='productHeaderBox']/div[1]/div/div/div/div/h1
//                  //*[@id='productHeaderBox']/section[2]/div/div[1]/span

		By brandNameLink = By.xpath("//div[1]/div/div[1]/div/div/div/div/a");
		By brandbrandName = By.xpath("//div[1]/div/div[1]/div/div/div/div/h1");
		By singlePrice = By.xpath("//div[1]/div/section[2]/div/div[1]/span");
		By reviewStars = By.xpath("//*[@id='reviewsSummary']//span[@class='reviewsSummary']");
		By addToCartButton = By.xpath("//button[contains(@class, 'addToBagButton')]");
		By addToListButton = By.xpath("//*[@id='orderPanel5302660']/div/div[1]/div[3]/div");

		System.out.println("Browser: IE");
		System.out.println("Page URL: " + driver.getCurrentUrl());
		System.out.println("01. Element [brandNameLink]: " + (isPresent(brandNameLink) ? "Exists" : " Not exist "));
		System.out.println("02. Element [brandbrandName]: " + (isPresent(brandbrandName) ? "Exists" : " Not exist "));
		System.out.println("03. Element [singlePrice]: " + (isPresent(singlePrice) ? "Exists" : " Not exist"));
		System.out.println("04. Element [reviewStars]: " + (isPresent(reviewStars) ? "Exists" : " Not exist"));
		System.out.println("05. Element [addToCartButton]: " + (isPresent(addToCartButton) ? "Exists" : " Not exist"));
		System.out.println("06. Element [addToListButton]: " + (isPresent(addToListButton) ? "Exists" : " Not exist"));
		driver.quit();
		
	}
}