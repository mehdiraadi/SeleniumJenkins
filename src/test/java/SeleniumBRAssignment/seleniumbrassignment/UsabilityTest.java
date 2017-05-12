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

public class UsabilityTest {

	private static  WebDriver driver;
	private static  WebDriverWait wait;
	String baseUrl= "http://www.br.se/";
	public static Logger log;
	static FileHandler fh;
	private static String expected = "";
	private static String actual = "";
	
	@BeforeClass
	
	public static void setupOnce(){
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 3);
		log = Logger.getLogger(UsabilityTest.class.getName());
		try {
			// Path to log file
			fh = new FileHandler("C:\\Users\\shahe\\workspace\\Selenium_BR_assignment\\log\\logUsability.log");
		} catch (Exception e) {e.printStackTrace();}
		log.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);
		System.out.println("@BeforeClass setupOnce()");
	}
	
	@Before
	public void resetData(){
		driver.navigate().to(baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("@Before resetData()");
		expected = "";
		actual = "";
	}


	//Test Case 08 - 	testing the search bar exists and if it works properly
	
		//Testing if search bar available
		@Test
		public void testSearchBarAvailability (){
			//prepare
			expected = "lego nexo knights drickflaska";
			//Act
			WebElement searchBar = driver.findElement(By.id("js-site-search-input"));
			
		    System.out.println("searchBar is displayed:"+ searchBar.isDisplayed());
		    //Assert
		    Assert.assertTrue("Search bar is not enabled", searchBar.isEnabled());
		    
		    log.info("Search bar is displayed and usability test pass");
		    
		}
		@Test
		//Test if category button enabled(usability test)
		public void testCategoryEnabled () throws InterruptedException
		{
			//act
			WebElement category =driver.findElement(By.xpath(".//*[@id='nav']/div[1]/div[1]/ul/li[2]/a"));
			//assert
			Assert.assertTrue("category not enabled", category.isEnabled());
			category.click();
			log.info("Test pass");
		}
		
		@Test
		public void testForCustomerService(){
			
			//Prepare
			log.info("Preparing - testforCustomerService()");
			
			String inputPath = ".//*[@id='content']/div[2]/div/nav/ol/li[2]/a";
			String kundServicePath= ".//*[@id='nav']/div[1]/div[1]/ul/li[6]/a";
			
			//Act
			log.info("Act - testforCustomerService()");
			driver.findElement(By.xpath(kundServicePath)).click();
			WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(inputPath)));
			
			//Assert
			log.info("Assert - testforCustomerService()");
			Assert.assertTrue("ResultStatsTest Fail", result.isDisplayed());
			
			//Prepare
			log.info("Preparing - testforCustomerService()");
			expected = "Kundservice | Leksaker från BR";
			
			//Act
			log.info("Act - testforCustomerService()");
			actual = driver.getTitle();
			
			//Assert
			log.info("Assert - testforCustomerService()");
			Assert.assertEquals("GetTitleTest Fail", expected, actual);
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





