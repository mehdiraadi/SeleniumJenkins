package SeleniumBRAssignment.seleniumbrassignment;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import TestCategory;

public class TestShoppingCartHeaderUpdate {

	private static  WebDriver driver;
	private static  WebDriverWait wait;
	String baseUrl= "http://www.br.se/";
	public static Logger log;
	static FileHandler fh;
	private static String expected = "";
	private static String actual = "";
	String underConsTitle = "Under Construction";

	@BeforeClass
	
	public static void setupOnce(){
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 4);
		log = Logger.getLogger(TestShoppingCartHeaderUpdate.class.getName());
		try {
			// Path to log file
			fh = new FileHandler("C:\\Users\\shahe\\workspace\\Selenium_BR_assignment\\log\\log_ShoppingCartAll.log");
		} catch (Exception e) {e.printStackTrace();}
		log.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);
		System.out.println("@BeforeClass setupOnce()");
	}
	
	@Before
	public void resetData() throws InterruptedException{
		driver.navigate().to(baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		System.out.println("@Before resetData()");
		expected = "";
		actual = "";
		Thread.sleep(3000);
	}
	//  Testing if the shopping cart updates when i add a product
		@Test
		
		public void testShoppingCartHeaderUpdate() throws InterruptedException{
			//preparing
			expected="(1)";
			
			log.info("Expected value in shopping cart is :"+ expected);
			//Act
			//searching for a product
			WebElement searchBar = driver.findElement(By.id("js-site-search-input"));
			searchBar.sendKeys("LEGO Creator 31057 Supersnurr");
			log.info("searching for LEGO Creator 31057 Supersnurr");
			
			//Clicking go
			WebElement go= driver.findElement(By.id("btnSearch"));
			go.click();
			
			log.info("Go button works");
			//Waiting for next page to load
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='result1-products']/div[2]/div/ul/li[1]/div/div/a/img")));
			
			//putting product into shopping cart
			WebElement basket= driver.findElement(By.xpath(".//*[@id='result1-products']/div[2]/div/ul/li[1]/div/a"));
			basket.click();
			Thread.sleep(3000);
			
			//Assert
			WebElement update  =driver.findElement(By.xpath(".//*[@id='iconbar']/div[4]/a/span/span"));
			Assert.assertTrue("basket not working", update.getText().contains(expected));
			log.info("Test Pass");
			
			
			}
		
		
	@After
	public void tearDown() throws Exception {
		try{
		driver.close();
		}
		catch(Exception e){
			
		}
	}

	
	
	@AfterClass
	public static void tearDownAfterClass() {
		
			System.out.println("@AfterClass tearDownOnce()");
			driver.close();
			try{
				Thread.sleep(5000);
			}catch(InterruptedException e){
				System.out.println(e.getStackTrace());
			}
			driver.quit();
		}

}
