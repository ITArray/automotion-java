package rectangles;

import net.itarray.automotion.internal.ResponsiveUIValidatorBase;
import net.itarray.automotion.validation.ResponsiveUIValidator;
import net.itarray.automotion.validation.UIElementValidator;
import net.itarray.automotion.validation.UISnapshot;
import net.itarray.automotion.validation.Units;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.DummyWebElement.createElement;
import static rectangles.DummyWebElement.createRootElement;
import static rectangles.RectangleFixture.down;
import static rectangles.RectangleFixture.up;

public class PercentageToPageIntegrationTest {
    private DummyWebElement element;
    private DummyWebElement other;
    private String elementName;
    private ResponsiveUIValidatorBase base;

    @Before
    public void setUp() {
        element = (DummyWebElement) createElement(100, 200, 500, 400);
        other = (DummyWebElement) createElement(600, 200, 1300, 400);
        elementName = "under test";
    }

    public UIElementValidator createElementValidator() {
        DummyDriverFacade driverFacade = new DummyDriverFacade();
        driverFacade.setPageSize(new Dimension(2000, 1000));
        ResponsiveUIValidator uiValidator = new ResponsiveUIValidator(driverFacade);
        UISnapshot snapshot = uiValidator.snapshot();
        UIElementValidator result = snapshot.findElement(element, elementName);
        base = (ResponsiveUIValidatorBase) result;
        return result;
    }

    @Test
    public void distanceToTheLeftIs100Pixels() {
        boolean validate = createElementValidator().isLeftOf(other, 100, 100).validate();
        assertThat(validate).isTrue();
    }

    @Test
    public void distanceToTheLeftIs5Percent() {
        boolean validate = createElementValidator().changeMetricsUnitsTo(Units.PERCENT).isLeftOf(other, 5, 5).validate();
        assertThat(validate).isTrue();
    }

    @Test
    public void distanceToTheLeftNot4Percent() {
        createElementValidator().changeMetricsUnitsTo(Units.PERCENT).isLeftOf(other, 4, 4);
        String lastMessage = base.getErrors().getLastMessage();
        assertThat(lastMessage).isNotNull();
    }

    @Test
    public void distanceToTheLeftNot6Percent() {
        createElementValidator().changeMetricsUnitsTo(Units.PERCENT).isLeftOf(other, 6, 6);
        String lastMessage = base.getErrors().getLastMessage();
        assertThat(lastMessage).isNotNull();
    }
}
