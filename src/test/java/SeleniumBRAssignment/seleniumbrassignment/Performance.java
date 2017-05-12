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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import TestCategory;

import org.openqa.selenium.By;

public class Performance {

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
		log = Logger.getLogger(Performance.class.getName());
		try {
			// Path to log file
			fh = new FileHandler("C:\\Users\\shahe\\workspace\\Selenium_BR_assignment\\log\\log_Performance.log");
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
	@Test
	public void UploadLessThan3Sec(){
			
	//Prepare
	log.info("Preparing - UploadLessThan3Sec()");
	String inputPath = ".//*[@id='header-text']/a";
			
	//Act
	long start = System.currentTimeMillis();
	log.info("Act - UploadLessThan3Sec()");
			
			WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(inputPath)));
			//Assert
			long finish = System.currentTimeMillis();
			long totalTime = finish - start; 
			log.info("Total Time for page load - "+totalTime); 
			log.info("Assert - UploadLessThan3Sec()");
			Assert.assertTrue("ResultStatsTest Fail", result.isDisplayed());
			Assert.assertTrue("ResultStatsTest time Fail", totalTime<3000);
		
			//Prepare
			log.info("Preparing - UploadLessThan3Sec()");
			expected = "Leksaker från BR | Hem";
			//Act
			log.info("Act - testGetTitle()");
			actual = driver.getTitle();
			//Assert
			System.out.println("Assert - UploadLessThan3Sec()");
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
