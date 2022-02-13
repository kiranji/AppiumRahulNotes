package AppiumExample.AppiumExample;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.annotations.Test;

import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;;

public class AppiumDemoAPK extends base {

	AndroidDriver<AndroidElement> driver;

	@Test
	public void myTest() throws MalformedURLException {

		driver = getDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//android.widget.TextView[@text='Preference']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='3. Preference dependencies']")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		// android.widget.RelativeLayout[2]
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		driver.findElement(By.className("android.widget.EditText")).sendKeys("Hello");
		List<AndroidElement> ele = driver.findElements(By.className("android.widget.Button"));
		ele.get(1).click();
		// driver.findElementByAndroidUIAutomator("class(\"android.widget.RelativeLayout\")").click();

		// driver.findElementByAnd
	}

	@Test
	public void myTest2() throws MalformedURLException {
		driver = getDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		TouchAction action = new TouchAction(driver);
		WebElement viewMenu = driver.findElement(By.xpath("//android.widget.TextView[@text='Views']"));

		action.tap(TapOptions.tapOptions().withElement(ElementOption.element(viewMenu))).perform();

		WebElement expandableList = driver.findElementByAndroidUIAutomator("text(\"Expandable Lists\")");

		action.tap(TapOptions.tapOptions().withElement(ElementOption.element(expandableList))).perform();
		driver.findElement(By.xpath("//*[@text='1. Custom Adapter']")).click();

		WebElement peopleNames = driver.findElement(By.xpath("//*[@text='People Names']"));

		action.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(peopleNames))
				.withDuration(Duration.ofSeconds(2))).release().perform();

		assertTrue(driver.findElement(By.id("android:id/title")).isDisplayed());

	}

	@Test
	public void myTest3() throws MalformedURLException, InterruptedException {

		driver = getDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		TouchAction action = new TouchAction(driver);

		// System.out.println(driver.findElementsByAndroidUIAutomator("new
		// UiSelector().clickable(true)").size());

		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Views\"));");

		WebElement viewMenu = driver.findElement(By.xpath("//android.widget.TextView[@text='Views']"));
		action.tap(TapOptions.tapOptions().withElement(ElementOption.element(viewMenu))).perform();

		WebElement dateWidget = driver.findElement(By.xpath("//*[@text='Date Widgets']"));
		action.tap(TapOptions.tapOptions().withElement(ElementOption.element(dateWidget))).perform();

		driver.findElement(By.xpath("//*[@text='2. Inline']")).click();

		driver.findElement(By.xpath("//*[@index='9']")).click();

		WebElement first = driver.findElement(By.xpath("//*[@content-desc='15']"));
		WebElement second = driver.findElement(By.xpath("//*[@content-desc='45']"));

		// action.longPress(LongPressOptions.longPressOptions().withElement(element(first)).withDuration(ofSeconds(2))).moveTo(element(second)).release().perform();

		Thread.sleep(3000);
		first.click();
		action.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(first))
				.withDuration(Duration.ofSeconds(10))).moveTo(ElementOption.element(second)).release().perform();

	}

	@Test
	public void myTest4() throws MalformedURLException, InterruptedException {

		driver = getDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		TouchAction action = new TouchAction(driver);

		// System.out.println(driver.findElementsByAndroidUIAutomator("new
		// UiSelector().clickable(true)").size());

		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Views\"));");

		WebElement viewMenu = driver.findElement(By.xpath("//android.widget.TextView[@text='Views']"));
		action.tap(TapOptions.tapOptions().withElement(ElementOption.element(viewMenu))).perform();

		driver.findElement(By.xpath("//android.widget.TextView[@text='Drag and Drop']")).click();

		WebElement Source = driver.findElements(By.className("android.view.View")).get(0);
		WebElement dest = driver.findElements(By.className("android.view.View")).get(1);

		action.longPress(ElementOption.element(Source)).moveTo(ElementOption.element(dest)).release().perform();

	}

	@Test
	public void PreInstalledTest() throws MalformedURLException, InterruptedException {
		driver = getDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		WebElement frame = driver.findElement(By.id("com.android.contacts:id/dialpad_fragment_content"));
		// code to verticalscroll
		Dimension dimension = frame.getSize();
		int height = dimension.getHeight();
		int start = (int) (height * 0.4);
		int end = (int) (height * 0.2);
		TouchAction action = new TouchAction((PerformsTouchActions) driver);
		action.press(PointOption.point(0, start)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
				.moveTo(PointOption.point(0, end)).release().perform();
		Thread.sleep(3000);

		// code to horizontalscroll
		frame = driver.findElement(By.id("android:id/content"));
		dimension = frame.getSize();
		int middle = (int) (dimension.height / 2);
		start = (int) (dimension.getWidth() * .9);
		end = (int) (dimension.getWidth() * 0.2);
		action.press(PointOption.point(start, middle)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
				.moveTo(PointOption.point(end, middle)).release().perform();

	}

	@Test
	public void dateScrollerTest() throws MalformedURLException, InterruptedException {
		driver = getDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		TouchAction action = new TouchAction(driver);

		// System.out.println(driver.findElementsByAndroidUIAutomator("new
		// UiSelector().clickable(true)").size());

		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Views\"));");

		WebElement viewMenu = driver.findElement(By.xpath("//android.widget.TextView[@text='Views']"));
		action.tap(TapOptions.tapOptions().withElement(ElementOption.element(viewMenu))).perform();

		WebElement dateWidget = driver.findElement(By.xpath("//*[@text='Date Widgets']"));
		action.tap(TapOptions.tapOptions().withElement(ElementOption.element(dateWidget))).perform();

		driver.findElement(By.xpath("//*[@text='1. Dialog']")).click();

		driver.findElement(By.xpath("//*[@text='CHANGE THE DATE']")).click();
		WebElement ele = driver.findElements(By.xpath("//*[@class='android.widget.EditText']")).get(0);
		Thread.sleep(2000);

//		Dimension dimension=ele.getSize();
//		int height = dimension.getHeight();
//		int middle=dimension.getWidth()/2;
//		int start = (int) (height * 0.8);
//		int end = (int) (height * 0.2);
//		action.press(PointOption.point(middle, start)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
//				.moveTo(PointOption.point(middle, end)).release().perform();
//		Thread.sleep(3000);

		ele.click();
		ele.clear();
		ele.sendKeys("15");

		ele = driver.findElements(By.xpath("//*[@class='android.widget.EditText']")).get(1);
		ele.click();
		ele.clear();
		ele.sendKeys("Mar");

		ele = driver.findElements(By.xpath("//*[@class='android.widget.EditText']")).get(2);
		ele.click();
		ele.clear();
		ele.sendKeys("2021");

	}

	@Test
	public void pickerTest() throws MalformedURLException {
		driver = getDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		TouchAction action = new TouchAction(driver);

		// System.out.println(driver.findElementsByAndroidUIAutomator("new
		// UiSelector().clickable(true)").size());

		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Views\"));");

		WebElement viewMenu = driver.findElement(By.xpath("//android.widget.TextView[@text='Views']"));
		action.tap(TapOptions.tapOptions().withElement(ElementOption.element(viewMenu))).perform();

		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Picker\"));");

		driver.findElement(By.xpath("//*[@text='Picker']")).click();

		WebElement ele = driver.findElement(By.xpath("//*[@class='android.widget.LinearLayout']"));

		Dimension dimension = ele.getSize();

		int height = dimension.getHeight();
		int start = (int) (height * 0.6);
		int end = (int) (height * 0.2);
		int width = dimension.getWidth() / 2;

		action.press(PointOption.point(width, start)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
				.moveTo(PointOption.point(width, end)).release().perform();

	}

	@Test
	public void seekBarTest() throws MalformedURLException {
		driver = getDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		TouchAction action = new TouchAction(driver);
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Views\"));");

		WebElement viewMenu = driver.findElement(By.xpath("//android.widget.TextView[@text='Views']"));
		action.tap(TapOptions.tapOptions().withElement(ElementOption.element(viewMenu))).perform();

		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Seek Bar\"));");

		driver.findElement(By.xpath("//*[@text='Seek Bar']")).click();
		WebElement ele = driver.findElement(By.id("io.appium.android.apis:id/seek"));
		int xaxis=ele.getLocation().x;
		int yaxis=ele.getLocation().y;
		int width=ele.getSize().getWidth();
		int end=(int) (width*.8);
		action.longPress(PointOption.point(xaxis,yaxis)).moveTo(PointOption.point(end,yaxis)).release().perform();

	}

}
