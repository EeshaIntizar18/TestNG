import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

public class Browser {
	public static WebDriver driver;
	int z = 0;
	Boolean signupdesk = false;
	Boolean signupmob = false;
	Boolean compareStrings = false;
	Boolean compareProduct = false;
	String actual;
	Actions actions;
	WebElement accountIcon;
	String expected3 = "reCAPTCHA";
	String expected2 = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
	String expected = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";

	@BeforeClass
	public void openBrowser() throws IOException, InterruptedException, Exception {

		// Setting property for Chrome Driver
		System.setProperty("webdriver.chrome.driver", "/home/rlt/Downloads/chromedriver");

		// Instance of ChromeOption
		ChromeOptions options = new ChromeOptions();

		// Disable notification PopUp
		options.addArguments("--disable-notifications");

		// Instance of ChromeDriver.
		driver = new ChromeDriver(options);

		// Calling Ego HomePage
		driver.get("https://ego.co.uk");
		Thread.sleep(1000);

		// Maximize the browser
		driver.manage().window().maximize();
		Thread.sleep(4500);

	}

	//AdClose
	@Parameters({ "value1" ,"value2"})
	@Test(priority = 2)
	public void adClose(String ad,String fail) throws Exception {
		// Closing PopUp window
		driver.findElement(By.xpath("//div[@data-testid='om-overlays-close']")).click();
		Thread.sleep(1000);
		System.out.println("AdClose," + ad);
	}

	//Login
	@Parameters({ "value1","value2" })
	@Test(priority = 3)
	public void login(String l) throws InterruptedException {

		// Mouse over on account button

		WebElement accountIcon = driver
				.findElement(By.xpath("//*[@id=\"html-body\"]/div[2]/header/div[2]/div/div[3]/div[1]/button"));
		Actions actions = new Actions(driver);
		actions.moveToElement(accountIcon).perform();

		// Clicking on login button
		driver.findElement(By.xpath("//*[@id=\"jq-account-trigger\"]/span")).click();

		// Clicking on email
		WebElement email = driver.findElement(By.id("email"));
		Thread.sleep(1000);

		// Entering email
		email.sendKeys("usman.ali@rltsquare.com");
		Thread.sleep(1000);

		// Selecting password field
		WebElement password = driver.findElement(By.id("pass"));
		Thread.sleep(3000);

		// Entering password
		password.sendKeys("@mani112233");
		Thread.sleep(3000);

		// Selecting SigIn button
		WebElement login = driver.findElement(By.id("send2"));
		Thread.sleep(1000);

		// Clicking on SignIn button.
		login.click();
		Thread.sleep(3000);
		System.out.println("Login," + l);

		// Check login validation
		String ExpectedURL = "https://ego.co.uk/";
		String newUrl = driver.getCurrentUrl();
		if (newUrl.equalsIgnoreCase(ExpectedURL)) {

			System.out.println("Login," + l);
			Thread.sleep(1000);
			z = 1;
		}

		else {

		}
		String actual = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/div/div")).getText();
		String expected3 = "reCAPTCHA";
		String expected2 = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
		String expected = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
		compareStrings = actual.equals(expected) || actual.contains(expected2) || actual.contains(expected3);
		if (compareStrings == true) {
			System.out.println("Login for Desk,"+fail);
			Thread.sleep(2000);
			System.out.println("Account temporarirly disbaled text appeared");
		}

		actual = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/div/div")).getText();

		compareStrings = actual.equals(expected) || actual.contains(expected2) || actual.contains(expected3);
		if (compareStrings == true) {

			// Mouse over on account button
			Thread.sleep(2000);
			accountIcon = driver
					.findElement(By.xpath("//*[@id=\"html-body\"]/div[2]/header/div[2]/div/div[3]/div[1]/button"));
			actions = new Actions(driver);
			actions.moveToElement(accountIcon).perform();
			Thread.sleep(1000);

			// Clicking on create account
			driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/div/div[3]/div[1]/ul/li[2]/a")).click();
			Thread.sleep(2000);

			// Enter first name
			driver.findElement(By.id("firstname")).sendKeys("Test");
			Thread.sleep(500);

			// Enter last name
			driver.findElement(By.id("lastname")).sendKeys("Test");
			Thread.sleep(500);

			// Enter email
			driver.findElement(By.id("email_address")).sendKeys("usman.ali@rltsquare.com");
			Thread.sleep(500);

			// Enter password
			driver.findElement(By.id("password")).sendKeys("@Mani112233");
			Thread.sleep(500);

			// Enter password confirmation
			driver.findElement(By.id("password-confirmation")).sendKeys("@Mani112233");
			Thread.sleep(1000);

			// Enter DOB
			driver.findElement(By.id("dob")).sendKeys("08/09/1998");
			Thread.sleep(1000);

			// Click on Check-Box
			driver.findElement(By.id("assistance_allowed_checkbox")).click();
			Thread.sleep(1000);

			// Click on create account button
			driver.findElement(By.xpath("/html/body/div[2]/main/div[3]/div/form/div/div[1]/button/span")).click();
			Thread.sleep(1000);

			// Check account already exist or not
			String actuals = driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/div[2]/div/div/div")).getText();
			String expected1 = "There is already an account with this email address. If you are sure that it is your email address, ";
			String expected22 = "reCAPTCHA";
			signupdesk = actuals.contains(expected1) || actuals.contains(expected22);

			Thread.sleep(3000);

			if (signupdesk == true) {
				System.out.println("Account already exist");
			} else {

				System.out.println("Account successfuly created");
				System.out.println(2);
				Thread.sleep(1000);
				System.out.println("Signup " + l);

			}
		}
	}

	//Search
	@Parameters({ "value1" })
	@Test(priority = 4)
	public void search(String s) throws Exception {

		// Entering heels in search bar
		Thread.sleep(1000);
		WebElement ar = driver.findElement(By.name("q"));
		ar.sendKeys("heels");
		Thread.sleep(3000);

		// Press ENTER
		ar.sendKeys(Keys.RETURN);
		Thread.sleep(3000);

		System.out.println("Search," + s);

	}

	@Parameters({ "value1" })
	@Test(priority = 5)

	public void filters(String f1) throws Exception {

		// Clicking on size filter
		WebElement f = driver.findElement(By.className("filter-options-title"));
		f.click();
		Thread.sleep(500);

		// Clicking on size 4
		WebElement no = driver.findElement(By.xpath(
				"/html/body/div[2]/main/div[3]/div/div[1]/div[1]/div/div[3]/div[2]/div[1]/div[2]/div/div[2]/div/div[1]/a[3]/div"));
		no.click();
		Thread.sleep(4000);

		// Clicking on color filter
		WebElement color1 = driver.findElement(
				By.xpath("/html/body/div[2]/main/div[3]/div/div[1]/div[1]/div/div[3]/div[2]/div[2]/div[1]/div"));
		Thread.sleep(500);
		color1.click();

		// Clicking on black Color
		WebElement select = driver.findElement(By.className("layer-input-filter"));
		select.click();
		Thread.sleep(4000);

		System.out.println("Filters," + f1);
	}

	//PDP
	@Parameters({ "value1" })
	@Test(priority = 6)
	public void pdp(String p) throws Exception {

		// Clicking on Fiat lace product
		driver.findElement(
				By.xpath("/html/body/div[2]/main/div[3]/div/div[1]/div[4]/div[2]/ol/li[2]/div/div[2]/strong/a"))
				.click();
		Thread.sleep(3000);

		String ExpectedURL1 = "https://ego.co.uk/hab478-fiat-lace-up-square-toe-sculptured-heel-in-black-faux-leather.html";
		String newUrls = driver.getCurrentUrl();
		if (newUrls.equalsIgnoreCase(ExpectedURL1)) {
			System.out.println("Fiat lace product,Pass");

		} else {
			System.out.println("Fiat lace product,Fail");
			driver.quit();

		}

		// Clicking on product size
		driver.findElement(By.className("custom-select__trigger-size")).click();
		Thread.sleep(1000);

		// Selecting size
		driver.findElement(By.id("controlId-item-17")).click();
		Thread.sleep(1500);

		// Clicking on add to cart button
		driver.findElement(By.id("product-addtocart-button")).click();
		Thread.sleep(2000);

		System.out.println("Pdp," + p);
	}

	//View Bag
	@Parameters({ "value1" })
	@Test(priority = 7)
	public void viewBagDesk(String Bag) throws Exception {

		// Clicking on view bag button
		driver.findElement(By.className("view")).click();
		Thread.sleep(4000);

		// Clicking on quantity
		driver.findElement(By
				.xpath("/html/body/div[2]/main/div/div/div[3]/form/div[2]/div/div/div/div/div[2]/div[2]/div[2]/select"))
				.click();
		Thread.sleep(2000);

		// Changing Quantity
		driver.findElement(By.xpath(
				"/html/body/div[2]/main/div/div/div[3]/form/div[2]/div/div/div/div/div[2]/div[2]/div[2]/select/option[2]"))
				.click();
		Thread.sleep(2500);

		// Clicking on checkout button
		driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div/div/div[3]/div[4]/ul/li[1]/button")).click();
		Thread.sleep(2500);
		System.out.println("View bag," + Bag);
	}

	//Shipping address
	@Parameters({ "value1" })
	@Test(priority = 8)
	public void shipping_method(String sm) throws Exception {
		if (z == 1) {

			// Clicking on shipping method
			driver.findElement(By.xpath(
					"/html/body/div[3]/main/div[3]/div/div[3]/div[3]/ol/li[2]/div/div[3]/form/div[2]/table/tbody/tr[2]/td[2]"))
					.click();
			Thread.sleep(3000);

			System.out.println("Shipping method," + sm);
		} else {

			// Enter email
			driver.findElement(By.id("customer-email")).sendKeys("usman.ali@rltsquare.com");
			Thread.sleep(1500);

			// Selecting country drop down
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"/html/body/div[3]/main/div[3]/div/div[3]/div[3]/ol/li[1]/div[2]/form/div/div[1]/div/select"))
					.click();

			// Selecting United kingdom
			Thread.sleep(1000);
			driver.findElement(By.xpath(
					"/html/body/div[3]/main/div[3]/div/div[3]/div[3]/ol/li[1]/div[2]/form/div/div[1]/div/select/option[234]"))
					.click();
			Thread.sleep(1000);

			// Setting First name
			Thread.sleep(1000);
			driver.findElement(By.xpath(
					"/html/body/div[3]/main/div[3]/div/div[3]/div[3]/ol/li[1]/div[2]/form[2]/div/div[2]/div/input"))
					.sendKeys("Test");

			// Setting Last name
			Thread.sleep(500);
			driver.findElement(By.xpath(
					"/html/body/div[3]/main/div[3]/div/div[3]/div[3]/ol/li[1]/div[2]/form[2]/div/div[3]/div/input"))
					.sendKeys("Test");

			// Entering post code
			Thread.sleep(500);
			driver.findElement(By
					.xpath("/html/body/div[3]/main/div[3]/div/div[3]/div[3]/ol/li[1]/div[2]/form/div/div[4]/div/input"))
					.sendKeys("M32 0JT");
			Thread.sleep(250);

			// Entering address line one
			Thread.sleep(500);
			driver.findElement(By.xpath(
					"/html/body/div[3]/main/div[3]/div/div[3]/div[3]/ol/li[1]/div[2]/form/div/fieldset/div/div[1]/div/input"))
					.sendKeys("Unit A1, Thomas Street");

			// Entering address line two
			Thread.sleep(500);
			driver.findElement(By.xpath(
					"/html/body/div[3]/main/div[3]/div/div[3]/div[3]/ol/li[1]/div[2]/form/div/fieldset/div/div[2]/div/input"))
					.sendKeys("Longford Trading Estate Stretford");

			// Entering state
			Thread.sleep(500);
			driver.findElement(By
					.xpath("/html/body/div[3]/main/div[3]/div/div[3]/div[3]/ol/li[1]/div[2]/form/div/div[6]/div/input"))
					.sendKeys("Greater Manchester");

			// Entering city
			Thread.sleep(250);
			Thread.sleep(500);
			driver.findElement(By
					.xpath("/html/body/div[3]/main/div[3]/div/div[3]/div[3]/ol/li[1]/div[2]/form/div/div[7]/div/input"))
					.sendKeys("Manchester");

			// Entering mobile number
			Thread.sleep(500);
			driver.findElement(By.xpath(
					"/html/body/div[3]/main/div[3]/div/div[3]/div[3]/ol/li[1]/div[2]/form/div/div[8]/div/div[1]/input"))
					.sendKeys("+443222204317");
			Thread.sleep(500);

			// Clicking on shipping method
			driver.findElement(By.xpath(
					"/html/body/div[3]/main/div[3]/div/div[3]/div[3]/ol/li[2]/div/div[3]/form/div[2]/table/tbody/tr[2]/td[2]"))
					.click();
			Thread.sleep(3000);
			System.out.println("Shipping Method for Guest," + sm);

		}
	}

	//Continue to Payment method
	@Parameters({ "value1" })
	@Test(priority = 9)

	public void payment_method(String pm) throws Exception {

		// Clicking on continue to Payment method Button
		driver.findElement(By.xpath("//span[text()='CONTINUE TO PAYMENT SECURELY']")).click();
		Thread.sleep(2000);
		System.out.println("Payment method," + pm);
	}

	//Pay-by-Card
	@Parameters({ "value1" })
	@Test(priority = 10)
	public void card(String pay) throws Exception {
		Thread.sleep(500);
		// Clicking on pay now by card
		driver.findElement(By.xpath("//*[@id=\"checkout-payment-method-load\"]/div/div/div[2]/div[1]/label")).click();
		Thread.sleep(500);

		// Entering card details
		driver.switchTo().frame(driver.findElement(
				By.xpath("//*[@id=\"cardContainer\"]/div/div/div[2]/div[1]/div[1]/label/div/span/iframe")));
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@id=\"encryptedCardNumber\"]")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@id=\"encryptedCardNumber\"]")).sendKeys("3700 000000 00002");
		Thread.sleep(500);
		driver.switchTo().defaultContent();

		// Add expire date
		Thread.sleep(1500);

		// Converting iFrame to frame
		driver.switchTo().frame(driver.findElement(
				By.xpath("//*[@id=\"cardContainer\"]/div/div/div[2]/div[1]/div[2]/div[1]/label/div/span/iframe")));
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@id=\"encryptedExpiryDate\"]")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@id=\"encryptedExpiryDate\"]")).sendKeys("0330");
		Thread.sleep(500);
		driver.switchTo().defaultContent();

		// Add CVV
		Thread.sleep(1500);

		// Converting iFrame to frame
		driver.switchTo().frame(driver.findElement(
				By.xpath("//*[@id=\"cardContainer\"]/div/div/div[2]/div[1]/div[2]/div[2]/label/div/span/iframe")));
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@id=\"encryptedSecurityCode\"]")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@id=\"encryptedSecurityCode\"]")).sendKeys("7373");
		Thread.sleep(500);
		driver.switchTo().defaultContent();

		// Add name
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@classnamemodifiers='large']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@classnamemodifiers='large']")).sendKeys("T");
		driver.findElement(By.xpath("//input[@classnamemodifiers='large']")).sendKeys("e");
		driver.findElement(By.xpath("//input[@classnamemodifiers='large']")).sendKeys("s");
		driver.findElement(By.xpath("//input[@classnamemodifiers='large']")).sendKeys("t");
		Thread.sleep(500);

//		 Click on place order button
//		Thread.sleep(1500);
//		driver.findElement(By.xpath("//*[@id=\"adyen-cc-form\"]/div[3]/div/button")).click();

		System.out.println("Pay by card," + pay);

	}

	@AfterClass
	public void exitBrowser() {
		driver.quit();
	}
}
