package AppiumExample.AppiumExample;

import static org.testng.Assert.assertEquals;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.aspectj.weaver.ast.Literal;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class GeneralStoreAPK extends base {

	AndroidDriver<AndroidElement> driver;

	@Test
	public void loginTest() throws MalformedURLException {

		driver = getDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.className("android.widget.Spinner")).click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Austria\"));")
				.click();

		driver.findElement(By.className("android.widget.Button")).click();

	}

	@Test
	public void loginTestToast() throws MalformedURLException {

		driver = getDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();

		driver.findElement(By.className("android.widget.Button")).click();

		String toastMessgae = driver.findElement(By.xpath("//*[@class='android.widget.Toast']")).getAttribute("name");

		System.out.println(toastMessgae);
		assertEquals(toastMessgae, "Please enter your name");

	}

	@Test
	public void searchProduct() throws MalformedURLException {

		driver = getDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		// driver.findElement(By.className("android.widget.Spinner")).click();
		// driver.findElementByAndroidUIAutomator("new UiScrollable(new
		// UiSelector()).scrollIntoView(text(\"Austria\"));")
		// .click();

		driver.findElement(By.className("android.widget.Button")).click();

		// Till above login test same code

		String txt = "Jordan Lift Off";

		driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Jordan Lift Off\").instance(0))"));

		List<AndroidElement> list = driver.findElements(By.id("com.androidsample.generalstore:id/productName"));

		System.out.println("count of " + list.size());
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().equals(txt)) {
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
			}
			System.out.println("looping");
		}

	}

	@Test
	public void VerifySelectedProductAreCheckedOutCorrectly() throws MalformedURLException, InterruptedException {

		driver = getDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.className("android.widget.Button")).click();
		String txt = "Jordan Lift Off";

		driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\""
						+ txt + "\").instance(0))"));

		List<AndroidElement> list = driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart"));
		List<AndroidElement> productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName"));
		String arr[] = new String[productName.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = productName.get(i).getText();
			System.out.println(arr[i]);
		}
		if (list.size() == productName.size()) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).click();
			}
		}
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(3000);

		List<AndroidElement> productNameAtCheckOut = driver
				.findElements(By.id("com.androidsample.generalstore:id/productName"));

		for (int i = 0; i < productName.size(); i++) {
			// System.out.println(arr[i] + " " + productNameAtCheckOut.get(i).getText());
			assertEquals(productNameAtCheckOut.get(i).getText(), arr[i]);

		}

		List<AndroidElement> productPrice = driver
				.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		double sum = 0;
		for (int i = 0; i < productPrice.size(); i++) {
			double amt = getAmount(productPrice.get(i).getText());
			sum = sum + amt;
		}
		assertEquals(sum,
				getAmount(driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText()));

		TouchAction ta = new TouchAction(driver);
		WebElement checkBox = driver.findElement(By.xpath("//*[@class='android.widget.CheckBox']"));
		ta.tap(TapOptions.tapOptions().withElement(ElementOption.element(checkBox))).perform();
		WebElement termsConditions = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		ta.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(termsConditions))
				.withDuration(Duration.ofSeconds(3))).release().perform();
		Thread.sleep(3000);
		driver.findElement(By.id("android:id/button1")).click();

		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(3000);// Here sleep is added to redirect to web page so that context Handles is
							// returned correctly
		Set<String> context = driver.getContextHandles();
		System.out.println(context);

//		Iterator itr=context.iterator();
//		while(itr.hasNext()) {
//		String context1=itr.next().toString();
		// if(context1.contains(""))
		// }

		driver.context("WEBVIEW_com.androidsample.generalstore");
		//driver.findElement(By.name("q")).sendKeys("hello", Keys.ENTER);
		 driver.findElement(By.xpath("//*[@name='q']")).sendKeys("hello");
		 driver.findElement(By.xpath("//*[@name='q']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
	}
	
	@Test
	public void chromeBrowserTest() throws MalformedURLException {
		driver = getDriver();
		driver.get("https://www.google.co.in/");	
		driver.findElement(By.name("q")).sendKeys("hello",Keys.ENTER);
	}
	
	

	public static double getAmount(String amt) {
		return Double.parseDouble(amt.substring(1));

	}

}
