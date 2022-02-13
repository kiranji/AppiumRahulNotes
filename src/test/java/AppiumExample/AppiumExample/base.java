package AppiumExample.AppiumExample;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class base {

	public static AndroidDriver<AndroidElement> getDriver() throws MalformedURLException {
		// TODO Auto-generated method stub

		AndroidDriver<AndroidElement> driver;

		DesiredCapabilities cap = new DesiredCapabilities();

		// File appDir = new File("src");

		File app = new File("ApiDemos-debug.apk");

//		File app = new File("General-Store.apk");

		
		//cap.setCapability("chromedriverExecutable", "C:\\Users\\kkrid\\AppData\\Roaming\\npm\\node_modules\\appium\\node_modules\\appium-chromedriver\\lib\\chromedriver.exe");

		//cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Copy_of_MYDevice");
		
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Andriod Device");

		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");// new step

		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		
//		cap.setCapability("appPackage", "com.android.contacts");
//		cap.setCapability("appActivity", "com.android.contacts.activities.DialtactsActivity");
	//	cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		return driver;

	}

}
