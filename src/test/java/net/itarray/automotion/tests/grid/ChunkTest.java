package net.itarray.automotion.tests.grid;

import net.itarray.automotion.internal.ResponsiveUIValidatorBase;
import net.itarray.automotion.validation.Chunk;
import net.itarray.automotion.validation.ChunkUIElementValidator;
import net.itarray.automotion.validation.Element;
import net.itarray.automotion.validation.ResponsiveUIValidator;
import net.itarray.automotion.validation.UISnapshot;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import rectangles.DummyDriverFacade;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.DummyWebElement.createElement;

@PageSize
public abstract class ChunkTest {
    protected ChunkUIElementValidator chunkValidator;

    @Before
    public void setUp() {
        DummyDriverFacade driverFacade = new DummyDriverFacade();

        PageSize pageSize = getClass().getAnnotation(PageSize.class);
        driverFacade.setPageSize(new Dimension(pageSize.xy()[0], pageSize.xy()[1]));

        ResponsiveUIValidator uiValidator = new ResponsiveUIValidator(driverFacade);
        UISnapshot snapshot = uiValidator.snapshot();

        List<WebElement> webElements = newArrayList();
        Chunk chunk = getClass().getAnnotation(Chunk.class);
        for (Element element : chunk.value()) {
            webElements.add(createElement(
                    element.value()[0],
                    element.value()[1],
                    element.value()[2],
                    element.value()[3]
            ));
        }
        boolean allowEmpty = getClass().getAnnotation(AllowEmpty.class) != null;
        chunkValidator =
                allowEmpty ?
                        snapshot.findZeroOrMoreElements(webElements) :
                        snapshot.findElements(webElements);
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
}
