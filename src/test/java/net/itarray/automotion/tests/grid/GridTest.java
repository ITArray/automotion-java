package net.itarray.automotion.tests.grid;

import net.itarray.automotion.internal.ResponsiveUIValidatorBase;
import net.itarray.automotion.validation.ChunkUIElementValidator;
import net.itarray.automotion.validation.ResponsiveUIValidator;
import net.itarray.automotion.validation.UISnapshot;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import rectangles.DummyDriverFacade;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class GridTest {
    protected ChunkUIElementValidator chunkValidator;

    @Before
    public void setUp() {
        DummyDriverFacade driverFacade = new DummyDriverFacade();
        driverFacade.setPageSize(new Dimension(2000, 1000));
        ResponsiveUIValidator uiValidator = new ResponsiveUIValidator(driverFacade);
        UISnapshot snapshot = uiValidator.snapshot();
        List<WebElement> elements = createElemements();
        chunkValidator = snapshot.findElements(elements);
    }


    public void assertValid() {
        ResponsiveUIValidatorBase base = (ResponsiveUIValidatorBase) chunkValidator;
        String lastMessage = base.getErrors().getLastMessage();
        assertThat(lastMessage).isNull();
    }

    public void assertInvalid() {
        ResponsiveUIValidatorBase base = (ResponsiveUIValidatorBase) chunkValidator;
        String lastMessage = base.getErrors().getLastMessage();
        assertThat(lastMessage).isNotNull();
    }

    public abstract List<WebElement> createElemements();
}
