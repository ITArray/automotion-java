import net.itarray.automotion.tools.driver.WebDriverFactory;
import net.itarray.automotion.tools.helpers.EnvironmentHelper;
import net.itarray.automotion.validation.ResponsiveUIValidator;
import net.itarray.automotion.validation.UISnapshot;
import net.itarray.automotion.validation.properties.Padding;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static net.itarray.automotion.internal.properties.PercentReference.PAGE;
import static net.itarray.automotion.validation.properties.Condition.between;
import static net.itarray.automotion.validation.properties.Expression.percent;
import static net.itarray.automotion.validation.properties.Zoom.zoom;

@Ignore
public class ResponsiveValidatorNewDSLTest {

    private static WebDriver driver;
    private static long start;

    public static void main(String[] args) {
        ManualTestSupport.deleteOutputDirectory();
        ResponsiveValidatorNewDSLTest test = new ResponsiveValidatorNewDSLTest();
        try {
            test.testThatResponsiveValidatorWorks();
        } finally {
            test.tearDown();
            stopTimer();
            ManualTestSupport.openReportInDefaultBrowser();;
        }
    }

    private static void stopTimer() {
        long stop = System.currentTimeMillis();
        System.out.println((stop - start) + " ms");
    }

    private static void time(String msg) {
        long stop = System.currentTimeMillis();
        System.out.println((stop - start) + " ms " + msg);
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

        start = System.currentTimeMillis();

        TestPage page = new TestPage(driver);

        ResponsiveUIValidator responsiveUIValidator = new ResponsiveUIValidator(driver);

        responsiveUIValidator.setLinesColor(Color.BLACK);
        responsiveUIValidator.withTolerance(10);
        SoftAssertions softly = new SoftAssertions();

        time("-1-");
        UISnapshot mainSnapshot = responsiveUIValidator.snapshot("Main");
        boolean success1 = mainSnapshot.findElement(page.topSlider(), "Top Slider")
                .isLeftAlignedWith(page.gridContainer(), "Grid Container")
                .isBottomAlignedWith(page.topTextBlock(), "Text Block")
                .hasWidth(between(percent(300, PAGE)).and(percent(500, PAGE)))
                .hasEqualSizeAs(page.gridElements())
                .isCenteredOnPageHorizontally()
                .isCenteredOnPageVertically()
                .isInsideOf(page.mainContainer(), "Main container", new Padding(10, 50, 10, 20))
                .validate();

        softly.assertThat(success1).isEqualTo(true).overridingErrorMessage("Failed validation of Top Slider element");

        time("-2-");
        boolean success0 = mainSnapshot.findElement(page.gridContainer(), "Grid Container")
                .isCenteredOnPageHorizontally()
                .validate();

        softly.assertThat(success0).isEqualTo(true).overridingErrorMessage("Failed validation of Grid Container");

        time("-3-");
        boolean success01 = mainSnapshot.findElement(page.mainContainer(), "Main Container")
                .isCenteredOnPageHorizontally()
                .validate();

        softly.assertThat(success01).isEqualTo(true).overridingErrorMessage("Failed validation of Main Container");


        time("-4-");
        boolean success2 = mainSnapshot.findElement(page.topTextBlock(), "Top Text block")
                .isRightAlignedWith(page.gridContainer(), "Grid Container")
                .isTopAlignedWith(page.topSlider(), "Top Slider")
                .validate();

        softly.assertThat(success2).isEqualTo(true).overridingErrorMessage("Failed validation of Top Text block");

        time("-5-");
        boolean success3 = mainSnapshot.findElements(page.gridElements())
                .alignedAsGrid(4, 3)
                .haveEqualSize()
                .doNotOverlap()
                .areTopAligned()
                .areCenteredOnPageVertically()
                .areCenteredOnPageHorizontally()
                .validate();

        softly.assertThat(success3).isEqualTo(true).overridingErrorMessage("Failed validation of Grid");

        for (WebElement card : page.gridElements()) {
            time("-6-");
            boolean success = responsiveUIValidator.snapshot("Validation of style for each of cards in a grid view")
                    .findElement(card.findElement(By.className("project-details")), "Project details block")
                    .hasCssValue("background", "#f8f8f8")
                    .hasCssValue("color", "#6f6f6f")
                    .isNotOverlapping(card.findElement(By.className("gallery-hover-4col")), "Image Container")
                    .hasEqualWidthAs(card.findElement(By.className("gallery-hover-4col")), "Image Container")
                    .validate();
            softly.assertThat(success).isEqualTo(true).overridingErrorMessage("Failed validation of Grid in a list");
        }

        int[] zoomRange = {50, 70, 100, 120, 150};

        for (int val : zoomRange) {
            time("-7-");
            boolean success = responsiveUIValidator.snapshot("Validate page", zoom(val)).findElement(page.mainContainer(), "Main container")
                    .isCenteredOnPageHorizontally()
                    .hasEqualWidthAs(page.gridContainer(), "Grid Container")
                    .validate();

            softly.assertThat(success).isEqualTo(true).overridingErrorMessage("Failed validation of Container");
        }

        time("-8-");
        responsiveUIValidator.generateReport("Home Page");
        time("-9-");

        softly.assertAll();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}