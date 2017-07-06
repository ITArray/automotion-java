package net.itarray.automotion.tests.errors;

import net.itarray.automotion.internal.Errors;
import net.itarray.automotion.internal.ResponsiveUIValidatorBase;
import net.itarray.automotion.validation.ChunkUIElementValidator;
import net.itarray.automotion.validation.ResponsiveUIValidator;
import net.itarray.automotion.validation.UIElementValidator;
import net.itarray.automotion.validation.UISnapshot;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import rectangles.DummyDriverFacade;

import java.util.Arrays;
import java.util.Collections;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.DummyWebElement.createElement;

public class ErrorMessagesRegressionTest {

    private WebElement element;
    private String elementName;
    private ResponsiveUIValidatorBase base;

    @Before
    public void setUp() {
        element = createElement(100, 200, 500, 400);
        elementName = "under test";
    }

    public UIElementValidator createElementValidator() {
        ResponsiveUIValidator uiValidator = new ResponsiveUIValidator(new DummyDriverFacade());
        UISnapshot snapshot = uiValidator.snapshot();
        UIElementValidator result = snapshot.findElement(this.element, elementName);
        base = (ResponsiveUIValidatorBase) result;
        return result;
    }

    public ChunkUIElementValidator createChunkValidator(WebElement other) {
        ResponsiveUIValidator uiValidator = new ResponsiveUIValidator(new DummyDriverFacade());
        UISnapshot snapshot = uiValidator.snapshot();
        ChunkUIElementValidator result = snapshot.findElements(Arrays.asList(element, other));
        base = (ResponsiveUIValidatorBase) result;
        return result;
    }

    @Test
    public void sameOffsetLeftAs() {
        createElementValidator().sameOffsetLeftAs(createElement(105, 200, 500, 400), "specifying");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same left offset as element 'specifying'");
    }

    @Test
    public void sameOffsetLeftAsWithList() {
        createElementValidator().sameOffsetLeftAs(singletonList(createElement(105, 200, 500, 400)));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same left offset as element 'with properties: tag=[null], id=[null], class=[null], text=[], coord=[105,200], size=[395,200]'");
    }

    @Test
    public void sameOffsetLeftAsWithChunk() {
        createChunkValidator(createElement(105, 200, 500, 400)).sameLeftOffset();
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element #1 has not the same left offset as element #2");
    }

    @Test
    public void sameOffsetRightAs() {
        createElementValidator().sameOffsetRightAs(createElement(100, 200, 505, 400), "specifying");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same right offset as element 'specifying'");
    }

    @Test
    public void sameOffsetRightAsWithList() {
        createElementValidator().sameOffsetRightAs(singletonList(createElement(100, 200, 505, 400)));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same right offset as element 'with properties: tag=[null], id=[null], class=[null], text=[], coord=[100,200], size=[405,200]'");
    }

    @Test
    public void sameOffsetRightAsWithChunk() {
        createChunkValidator(createElement(100, 200, 505, 400)).sameRightOffset();
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element #1 has not the same right offset as element #2");
    }

    @Test
    public void sameOffsetTopAs() {
        createElementValidator().sameOffsetTopAs(createElement(100, 205, 500, 400), "specifying");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same top offset as element 'specifying'");
    }

    @Test
    public void sameOffsetTopAsWithList() {
        createElementValidator().sameOffsetTopAs(singletonList(createElement(100, 205, 500, 400)));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same top offset as element 'with properties: tag=[null], id=[null], class=[null], text=[], coord=[100,205], size=[400,195]'");
    }

    @Test
    public void sameOffsetTopAsWithChunk() {
        createChunkValidator(createElement(100, 205, 500, 400)).sameTopOffset();
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element #1 has not the same top offset as element #2");
    }

    @Test
    public void sameOffsetBottomAs() {
        createElementValidator().sameOffsetBottomAs(createElement(100, 200, 500, 405), "specifying");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same bottom offset as element 'specifying'");
    }

    @Test
    public void sameOffsetBottomAsWithList() {
        createElementValidator().sameOffsetBottomAs(singletonList(createElement(100, 200, 500, 405)));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same bottom offset as element 'with properties: tag=[null], id=[null], class=[null], text=[], coord=[100,200], size=[400,205]'");
    }

    @Test
    public void sameOffsetBottomAsWithChunk() {
        createChunkValidator(createElement(100, 200, 500, 405)).sameBottomOffset();
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element #1 has not the same bottom offset as element #2");
    }

    @Test
    public void withLeftElement() {
        createElementValidator().withLeftElement(createElement(105, 205, 505, 405));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Left element aligned not properly");
    }

    @Test
    public void withRightElement() {
        createElementValidator().withRightElement(createElement(105, 205, 505, 405));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Right element aligned not properly");
    }

    @Test
    public void withTopElement() {
        createElementValidator().withTopElement(createElement(105, 205, 505, 405));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Above element aligned not properly");
    }

    @Test
    public void withBottomElement() {
        createElementValidator().withBottomElement(createElement(105, 205, 505, 405));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Below element aligned not properly");
    }

    @Test
    public void sameWidthAs() {
        createElementValidator().sameWidthAs(createElement(100, 200, 505, 405), "specifying");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same width as element 'specifying'. Width of 'under test' is 400px. Width of element is 405px");
    }

    @Test
    public void sameWidthAsWithList() {
        createElementValidator().sameWidthAs(singletonList(createElement(100, 200, 505, 405)));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same width as element 'with properties: tag=[null], id=[null], class=[null], text=[], coord=[100,200], size=[405,205]'. Width of 'under test' is 400px. Width of element is 405px");
    }

    @Test
    public void sameHeightAs() {
        createElementValidator().sameHeightAs(createElement(100, 200, 505, 405), "specifying");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same height as element 'specifying'. Height of 'under test' is 200px. Height of element is 205px");
    }

    @Test
    public void sameHeightAsWithList() {
        createElementValidator().sameHeightAs(singletonList(createElement(100, 200, 505, 405)));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same height as element 'with properties: tag=[null], id=[null], class=[null], text=[], coord=[100,200], size=[405,205]'. Height of 'under test' is 200px. Height of element is 205px");
    }

    @Test
    public void sameSizeAs() {
        createElementValidator().sameSizeAs(createElement(100, 200, 505, 405), "specifying");
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same size as element 'specifying'. Size of 'under test' is 400px x 200px. Size of element is 405px x 205px");
    }
    @Test
    public void sameSizeAsWithList() {
        createElementValidator().sameSizeAs(singletonList(createElement(100, 200, 505, 405)));
        Errors errors = base.getErrors();
        assertThat(errors.getLastMessage())
                .isEqualTo("Element 'under test' has not the same size as element 'with properties: tag=[null], id=[null], class=[null], text=[], coord=[100,200], size=[405,205]'. Size of 'under test' is 400px x 200px. Size of element is 405px x 205px");
    }

}
