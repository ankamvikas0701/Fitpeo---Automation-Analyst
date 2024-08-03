package Main;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("deprecation")
public class FitPeoTest {
	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ankam\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testRevenueCalculator() throws Exception {
		// 1. Navigate to the FitPeo Homepage
		driver.get("http://fitpeo.com");
		Thread.sleep(1000);
		// 2. Navigate to the Revenue Calculator Page
        driver.findElement(By.linkText("Revenue Calculator")).click();
		Thread.sleep(1000);

		// 3. Scroll Down to the Slider section
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(2000);
		js.executeScript("window.scroll(0,300)");

		// 4. Adjust the Slider
		WebElement sliderball = driver
				.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div/div/span[1]/span[3]/input"));
//        WebElement slider = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/div/span[1]/span[3]")); // Replace with actual slider ID
//		Actions ad = new Actions(driver);
//        ad.moveToElement(slider).clickAndHold().moveByOffset(-50, 0).release().perform();
//        Thread.sleep(1000);
//		ad.dragAndDropBy(sliderball, 1800, 454).perform();
//        Thread.sleep(5000);

		// 5. Updating the Text Field
		WebElement textField = driver.findElement(By.id(":r0:")); // Replace with actual text field ID
//		textField.clear();
		Thread.sleep(1000);
//		textField.clear();
		textField.sendKeys(Keys.BACK_SPACE);
		textField.sendKeys(Keys.BACK_SPACE);
		textField.sendKeys(Keys.BACK_SPACE);

		textField.sendKeys("560");

		assertEquals("560", textField.getAttribute("value"));

		// 6. Validating Slider Value
		assertEquals("560", sliderball.getAttribute("aria-valuenow"));

		// 7. Select CPT Codes
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[1]/label/span[1]/input")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[2]/label/span[1]/input")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[3]/label/span[1]/input")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[8]/label/span[1]/input")).click();
		Thread.sleep(1000);

		// 8. Validating Total Recurring Reimbursement
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		Thread.sleep(2000);
		js1.executeScript("window.scroll(0,300)");
		WebElement reimbursementHeader = driver
				.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[1]/div/div[3]/p[2]"));
		String exp = "$110700";
		String actual = reimbursementHeader.getText();
		if (exp == actual) {
			System.out.println("verified");
		} else
			System.out.println("not verified");
//		System.out.println(reimbursementHeader.getText());
	}

//    @After
//    public void tearDown() {
//        driver.close();
//    }
}
