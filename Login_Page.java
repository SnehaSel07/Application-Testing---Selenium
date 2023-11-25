package SeleniumMavenProj;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Locators_practice {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("inputUsername")).sendKeys("sneha.c796@gmail.com");
		driver.findElement(By.name("inputPassword")).sendKeys("Sneh@7196");
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();

		// Sneh@7196
		String error = driver.findElement(By.xpath("//p[@class='error']")).getText();
		System.out.println(error);
		driver.findElement(By.linkText("Forgot your password?")).click();

		// Password reset screen
		Thread.sleep(1000);
		driver.findElement(By.xpath("//form/input[1]")).sendKeys("Sneha");
		driver.findElement(By.xpath("//form/input[2]")).sendKeys("sneha.c796@gmail.com");
		driver.findElement(By.cssSelector("input[type='text']:nth-child(4)")).sendKeys("7208570203");

		// button.reset-pwd-btn
		driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
		System.out.println(driver.findElement(By.cssSelector("form p")).getText());

		// button.go-to-login-btn
		// Temp Password -- rahulshettyacademy

		driver.findElement(By.cssSelector("button.go-to-login-btn")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("inputUsername")).sendKeys("Sneha");
		driver.findElement(By.name("inputPassword")).sendKeys("rahulshettyacademy");

		driver.findElement(By.id("chkboxOne")).click();
		// xpath regex
		//// input[contains(@id,'chkboxOne')]
		// check-box1
		driver.findElement(By.xpath("//input[contains(@id,'chkboxOne')]")).click();

		driver.findElement(By.xpath("//button[text()='Sign In']")).click();

		// Login Success page
		String h2 = driver.findElement(By.xpath("//div/h2")).getText();
		String h1 = driver.findElement(By.xpath("//div/h1")).getText();
		String p = driver.findElement(By.xpath("//div/p")).getText();
		System.out.println(h2 + '\n' + h1 + '\n' + p);

		// css regex
		// button[class*='logout']
		// success page - Log Out button
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.cssSelector("button[class*='logout-btn']")).getText());
		driver.findElement(By.cssSelector("button[class*='logout']")).click();

	}

}
