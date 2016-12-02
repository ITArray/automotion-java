import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import util.driver.WebDriverFactory;
import util.validator.ResponsiveUIValidator;

import static util.validator.ResponsiveUIValidator.Units.PERCENT;
import static util.validator.ResponsiveUIValidator.Units.PX;

@Ignore
public class ResponsiveValidatorTest {

    @Test
    public void testThatResponsiveValidatorWorks() {
        System.setProperty("IS_LOCAL", "TRUE");
        System.setProperty("BROWSER", "Firefox");

        WebDriverFactory driverFactory = new WebDriverFactory();
        WebDriver driver = driverFactory.getDriver();
        driver.manage().window().maximize();

        driver.get("https://www.facey.top");

        TestPage page = new TestPage(driver);

        ResponsiveUIValidator responsiveValidator = new ResponsiveUIValidator(driver);

        boolean res1 = responsiveValidator.init()
                .findElements(page.images())
                .alignedAsGrid(1, 4)
                .areNotOverlappedWithEachOther()
                .withSameSize()
                .drawMap()
                .validate();

//        responsiveValidator.init()
//                .findElement(page.newPhotos(), "New Photos")
//                .changeMetricsUnitsTo(PERCENT)
//                .widthBetween(30, 40)
//                .heightBetween(5, 8)
//                .changeMetricsUnitsTo(PX)
//                .minOffset(2, 30, 50, 30)
//                .maxOffset(5, 40, 70, 40)
//                .notOverlapWith(page.header(), "Header")
//                .notOverlapWith(page.myPhotos(), "My Photos")
//                .notOverlapWith(page.topPhotos(), "Top Photos")
//                .sameOffsetRightAs(page.logo(), "Logo")
//                .withTopElement(page.header())
//                .drawMap()
//                .validate();
//
//        responsiveValidator.init()
//                .findElement(page.newPhotos(), "New Photos")
//                .widthBetween(300, 400)
//                .heightBetween(20, 50)
//                .minOffset(10, 500, 500, 600)
//                .maxOffset(200, 2000, 2000, 1000)
//                .notOverlapWith(page.header(), "Header")
//                .notOverlapWith(page.myPhotos(), "My Photos")
//                .notOverlapWith(page.topPhotos(), "Top Photos")
//                .sameOffsetRightAs(page.logo(), "Logo")
//                .drawMap()
//                .validate();

        responsiveValidator.generateReport("Base_Page");

        driver.quit();

        Assert.assertTrue("Validation is failed", res1);
    }
}
