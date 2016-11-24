import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import util.driver.WebDriverFactory;
import util.validator.ResponsiveValidator;

@Ignore
public class ResponsiveValidatorTest {

    @Test
    public void testThatResponsiveValidatorWorks() {
        System.setProperty("IS_LOCAL", "TRUE");
        System.setProperty("BROWSER", "Chrome");

        WebDriverFactory driverFactory = new WebDriverFactory();
        WebDriver driver = driverFactory.getDriver();
        driver.manage().window().maximize();

        driver.get("https://www.facey.top");

        TestPage page = new TestPage(driver);

        new ResponsiveValidator(driver)
                .findElement(page.newPhotos(), "New Photos")
                .widthBetween(300, 400)
                .heightBetween(20, 50)
                .minOffset(10, 500, 500, 600)
                .maxOffset(200, 2000, 2000, 1000)
                .notOverlapWith(page.header(), "Header")
                .notOverlapWith(page.myPhotos(), "My Photos")
                .notOverlapWith(page.topPhotos(), "Top Photos")
                .sameOffsetRightAs(page.logo(), "Logo")
                .drawMap()
                .validate();

        driver.quit();
    }

}
