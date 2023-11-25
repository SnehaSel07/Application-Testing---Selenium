package SeleniumMavenProj;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Standalone_Test {

	public static void main(String args[]) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		String ogProd = "ZARA COAT 3";

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		// username--> sneha.c796@gmail.com
		// Pass-->Sneh@7196

		driver.findElement(By.id("userEmail")).sendKeys("sneha.c796@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sneh@7196");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(ogProd)).findFirst()
				.orElse(null);
		prod.findElement(By.xpath("//div[@class='row']//div[1]//div[1]//div[1]//button[2]")).click();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.xpath("//div/div/h3"));

		Boolean match = cartProducts.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(ogProd));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();

		// handling drop-down

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build()
				.perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

		// Scrolling until the element.
		// Create instance of Javascript executor
		JavascriptExecutor je = (JavascriptExecutor) driver;

		// Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.cssSelector(".action__submit"));

		// now execute query which actually will scroll until that element is not
		// appeared on page.
		je.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		// Extract the text and verify
		driver.findElement(By.cssSelector(".action__submit")).click();
		System.out.println(driver.findElement(By.className("hero-primary")).getText());

	}

}
