package SeleniumBRAssignment.seleniumbrassignment;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
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



public class FunctionalTest {
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
		log = Logger.getLogger(FunctionalTest.class.getName());
		try {
			// Path to log file
			fh = new FileHandler("C:\\Users\\shahe\\workspace\\Selenium_BR_assignment\\log\\log_Functional.log");
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
	@Test
	public void testResultStats(){
		
		//Prepare
		System.out.println("Preparing - testResultStats()");
		
		String inputPath = ".//*[@id='iconbar']/div[1]/a[1]/div";
		
		//Act
	
	
		System.out.println("Act - testResultStats()");
		
		WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(inputPath)));
		//Assert
		
		System.out.println("Assert - testResultStats()");
		Assert.assertTrue("ResultStatsTest Fail", result.isDisplayed());
		
	}

	@Test
	public void verifieraWrongInput()throws InterruptedException{
		
		String usernamePath=".//*[@id='j_username']";
		String passwordPath=".//*[@id='j_password']";
		String iconPath =".//*[@id='iconbar']/div[1]/a[1]/div";
		//Prepare
		String username="jhjh";
		String password="jhjh";
		log.info("wrong username:"+username +"Wrong password"+password);
		
	    log.info("Preparing - testWrongLogin()");
		driver.findElement(By.xpath(iconPath)).click();
		log.info("Preparing1 - testWrongLogin()");
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(".//*[@id='content']/div[2]/div/div[1]/h2"))));
		
		//Act
		log.info("Act - testWrongLogin()");
		Thread.sleep(3000);	
		
		driver.findElement(By.id("j_username")).sendKeys(username);
				

		log.info("Act3 - testWrongLogin()");

		driver.findElement(By.tagName("input").id("j_password")).sendKeys(password);
		Thread.sleep(9000);

		//click on login button
	    driver.findElement(By.xpath(".//*[@id='loginForm']/div[3]/button")).click();
		

		WebElement result=wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(".//*[@id='content']/div[1]/div/button"))));
		//We are in the login page
		
		//Assert
	
		Assert.assertTrue("ResultStatsTest Fail", result.isDisplayed());
		log.info("Test passed");
	}
	
	
	
	

	//Test Case 08 - 	testing the search bar exists and if it works properly
	
		
		//Testing if search bar works properly
		@Test
		public void testSearchBarFunction(){
			
			WebElement searchBar = driver.findElement(By.id("js-site-search-input"));
		    
		    searchBar.sendKeys("lego nexo knights drickflaska");
		    
		    WebElement go =driver.findElement(By.id("btnSearch"));
		    go.click();
		    
		    log.info("Go button works");
		    
		    WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='search-header-placeholder']/div[1]/div/p")));
		     
			System.out.println(result.getText());
			
		
			log.info("Expected is :"+ expected);
			
	        //Assert
		   Assert.assertTrue("Searchbar fails ", result.getText().contains(expected));
		   
		   log.info("Search bar works perfect and tast pass");
		    
		    }
		//Testing category button 
		@Test
		public void testCategoryButton() throws InterruptedException
		{
			//Act
			//Clicking category button
			WebElement category =driver.findElement(By.xpath(".//*[@id='nav']/div[1]/div[1]/ul/li[2]/a"));
			category.click();

			
		List<WebElement> allElements = driver.findElements(By.xpath(".//*[@id='dd_1']/div[1]/div/ul//a")); 
		String[] linkTexts = new String[allElements.size()];
		int i=0;
	    
	//Saving all the category list in an array
		for (WebElement element: allElements) {
		
		    linkTexts[i]=element.getText();
			
			i++;
			
		}
		System.out.println(Arrays.toString(linkTexts));
		
		log.info(Arrays.toString(linkTexts));
		
		log.info("Whole category list printed out");
		
		//Assert
		for (int a=0;a<allElements.size();a++){
			
			Assert.assertNotNull("empty list", linkTexts[a]);
		}
		
		
		
	//Testing if each link in category list work
		for (String t : linkTexts) {
	        driver.findElement(By.linkText(t)).click();
	        
	        
	       if (driver.getTitle().equals(underConsTitle)) {
	            System.out.println("\"" + t + "\""
	                    + " is under construction.");
	        } else {
	            System.out.println("\"" + t + "\""
	                    + " is working.");
	        }
	       // driver.navigate().back();
	    }
		log.info("Test pass");

		}
		

		
		
		@Test
		public void testPaymentOptionsClickable() throws InterruptedException {
			
			WebElement searchBar = driver.findElement(By.id("js-site-search-input"));
			searchBar.sendKeys("LEGO Creator 31057 Supersnurr");
			
			WebElement go= driver.findElement(By.id("btnSearch"));
			go.click();
			log.info("Successfully chose a product");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='result1-products']/div[2]/div/ul/li[1]/div/a")));
			
			WebElement basket= driver.findElement(By.xpath(".//*[@id='result1-products']/div[2]/div/ul/li[1]/div/a"));
			basket.click();
			log.info("Product is in shopping cart");
			
			Thread.sleep(2000);
			//To check the basket status
			
			WebElement basketStatus =driver.findElement(By.xpath(".//*[@id='iconbar']/div[4]/a/span"));
			basketStatus.click();
			log.info("Basket updated");
			
			//till kassan
			
			WebElement tillKassan= driver.findElement(By.xpath(".//*[@id='basket']/footer/div/div[1]/a"));
			tillKassan.click();
			
			//Sign in as guest
			WebElement email= driver.findElement(By.id("guest.email"));
			email.sendKeys("zaheeryasmi@gmail.com");
			log.info("Entered correct mail id");
			
			WebElement emailConfirm= driver.findElement(By.id("guest.emailConfirm"));
			emailConfirm.sendKeys("zaheeryasmi@gmail.com");
			log.info("Email id confirmed");
			
			WebElement guest=driver.findElement(By.xpath(".//*[@id='toptoyGuestForm']/div[3]/button"));
			guest.click();
			log.info("Going to payment page");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='header-text']/span")));
			
			// Testing if it is possible to print-out different payment options
			
			List <WebElement> elements =driver.findElements(By.xpath(".//*[@type='radio']"));

			for (int i=0;i<elements.size();i++){
			String []	s=new String [elements.size()];
			s[i]=elements.get(i).getAttribute("value");
			
				System.out.println(s[i]);
				log.info(s[i]);
				log.info("Different payment options");
			}
			
			//Testing if it is possible to choose different payment methods
			 boolean bValue = false;
			
			// This statement will return True, in case of first Radio button(betalkort) is selected
			bValue = elements.get(0).isSelected();
			
			// This will check that if the bValue is True means if the first radio button is selected
			if(bValue == true){
				// This will select Second radio button(Nordea), if the first radio button is selected by default
				elements.get(1).click();
				Thread.sleep(1000);
				// This will select the third radio button (PayByBill)
				elements.get(2).click();
				
			}
			log.info("Different payment options are choosable");
			
				
			//Assert
			Assert.assertTrue("Unable to choose different payment methods",elements.get(2).isSelected());
			log.info("Test pass. The user is able to choose from different payment options");
			
			
			
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



