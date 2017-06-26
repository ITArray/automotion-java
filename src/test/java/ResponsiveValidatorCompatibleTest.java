import http.helpers.EnvironmentHelper;
import net.itarray.automotion.validation.Report;
import net.itarray.automotion.validation.properties.Padding;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import util.driver.DriverHelper;
import util.driver.WebDriverFactory;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Ignore
public class ResponsiveValidatorCompatibleTest {

    private static WebDriver driver;

    public static void main(String[] args) {
        ResponsiveValidatorCompatibleTest test = new ResponsiveValidatorCompatibleTest();
        try {
            test.testThatResponsiveValidatorWorks();
        } finally {
            test.tearDown();
        }
    }
    
    @Test
    public void testThatResponsiveValidatorWorks() {
        Map<String, String> sysProp = new HashMap<>();
        //sysProp.put("BROWSER", "Chrome");
        //sysProp.put("IS_LOCAL", "true");
        sysProp.put("IS_HEADLESS", "true");
        sysProp.put(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/Users/" + System.getProperty("user.name") + "/Downloads/phantomjs-2.1.1-macosx/bin/phantomjs");
        EnvironmentHelper.setEnv(sysProp);
        WebDriverFactory driverFactory = new WebDriverFactory();
        driver = driverFactory.getDriver();
        driver.get("http://visual.itarray.net");
        driver.manage().window().maximize();

        TestPage page = new TestPage(driver);

        Report uiValidator = new Report(driver);

        uiValidator.setLinesColor(Color.BLACK);
        SoftAssertions softly = new SoftAssertions();

        boolean success1 = uiValidator.init("Validation of Top Slider Element")
                .findElement(page.topSlider(), "Top Slider")
                .sameOffsetLeftAs(page.gridContainer(), "Grid Container")
                .sameOffsetBottomAs(page.topTextBlock(), "Text Block")
                .changeMetricsUnitsTo(Report.Units.PX)
                .widthBetween(300, 500)
                .sameSizeAs(page.gridElements())
                .equalLeftRightOffset()
                .equalTopBottomOffset()
                .insideOf(page.mainContainer(), "Main container", new Padding(10, 50, 10, 20))
                .drawMap()
                .validate();

        softly.assertThat(success1).isEqualTo(true).overridingErrorMessage("Failed validation of Top Slider element");

        boolean success0 = uiValidator.init("Validation of Grid view")
                .findElement(page.gridContainer(), "Grid Container")
                .equalLeftRightOffset()
                .drawMap()
                .validate();

        softly.assertThat(success0).isEqualTo(true).overridingErrorMessage("Failed validation of Grid Container");

        boolean success01 = uiValidator.init("Validation of Main container")
                .findElement(page.mainContainer(), "Main Container")
                .equalLeftRightOffset()
                .drawMap()
                .validate();

        softly.assertThat(success01).isEqualTo(true).overridingErrorMessage("Failed validation of Main Container");


        boolean success2 = uiValidator.init("Validation of Top Text block")
                .findElement(page.topTextBlock(), "Top Text block")
                .sameOffsetRightAs(page.gridContainer(), "Grid Container")
                .sameOffsetTopAs(page.topSlider(), "Top Slider")
                .drawMap()
                .validate();

        softly.assertThat(success2).isEqualTo(true).overridingErrorMessage("Failed validation of Top Text block");

        boolean success3 = uiValidator.init("Validation of a grid view")
                .findElements(page.gridElements())
                .alignedAsGrid(4, 3)
                .withSameSize()
                .areNotOverlappedWithEachOther()
                .sameTopOffset()
                .equalLeftRightOffset()
                .equalTopBottomOffset()
                .drawMap()
                .validate();

        softly.assertThat(success3).isEqualTo(true).overridingErrorMessage("Failed validation of Grid");

        for (WebElement card : page.gridElements()) {
            boolean success = uiValidator.init("Validation of style for each of cards in a grid view")
                    .findElement(card.findElement(By.className("project-details")), "Project details block")
                    .withCssValue("background", "#f8f8f8")
                    .withCssValue("color", "#6f6f6f")
                    .notOverlapWith(card.findElement(By.className("gallery-hover-4col")), "Image Container")
                    .sameWidthAs(card.findElement(By.className("gallery-hover-4col")), "Image Container")
                    .drawMap()
                    .validate();
            softly.assertThat(success).isEqualTo(true).overridingErrorMessage("Failed validation of Grid in a list");
        }

        int[] zoomRange = {50, 70, 100, 120, 150};

        for (int val : zoomRange) {
            DriverHelper.zoomInOutPage(driver, val);
            boolean success = uiValidator.init("Validate on page zoom " + val + "%")
                    .findElement(page.mainContainer(), "Main container")
                    .equalLeftRightOffset()
                    .sameWidthAs(page.gridContainer(), "Grid Container")
                    .drawMap()
                    .validate();

            softly.assertThat(success).isEqualTo(true).overridingErrorMessage("Failed validation of Container");
        }

        uiValidator.generateReport("Home Page");

        softly.assertAll();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}