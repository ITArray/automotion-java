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
        createElementValidator().sameOffsetLeftAs(Collections.singletonList(createElement(105, 200, 500, 400)));
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
        createElementValidator().sameOffsetRightAs(Collections.singletonList(createElement(100, 200, 505, 400)));
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
        createElementValidator().sameOffsetTopAs(Collections.singletonList(createElement(100, 205, 500, 400)));
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
        createElementValidator().sameOffsetBottomAs(Collections.singletonList(createElement(100, 200, 500, 405)));
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
}