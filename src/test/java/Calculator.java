import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Calculator {
    AndroidDriver<AndroidElement> driver = null;
    DesiredCapabilities capabilities = new DesiredCapabilities();
    public void initialize() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"AOSP on IA Emulator");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"ru.farpost.dromfilter");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"ru.farpost.dromfilter.app.ui.LauncherActivity");
        capabilities.setCapability(MobileCapabilityType.NO_RESET,true);
        try {
            driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"),capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void checkCalculate() {
        initialize();
//        driver.findElementById("ru.farpost.dromfilter:id/later_button").click();
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.findElementById("ru.farpost.dromfilter:id/search_firm_models").click();
        driver.findElementById("ru.farpost.dromfilter:id/search").click();
        driver.findElementById("ru.farpost.dromfilter:id/search").sendKeys("Tesla");
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView[2]/android.widget.RelativeLayout[1]/android.widget.TextView").click();
        driver.findElementById("ru.farpost.dromfilter:id/show_button").click();
        driver.findElementById("ru.farpost.dromfilter:id/bulletin").click();
        assert(driver.findElementByAndroidUIAutomator("new UiSelector().packageName(\"ru.farpost.dromfilter\").resourceId(\"ru.farpost.dromfilter:id/detail_title\")").getText().contains("Tesla"));
    }
}