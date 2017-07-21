package net.itarray.automotion.tests;

import net.itarray.automotion.validation.ChunkUIElementValidator;
import net.itarray.automotion.validation.UIElementValidator;
import net.itarray.automotion.validation.properties.Padding;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static net.itarray.automotion.validation.properties.Expression.percentOrPixels;
import static net.itarray.automotion.validation.properties.Condition.*;
import static org.mockito.Mockito.*;
import static rectangles.DummyWebElement.createElement;

public class NativeNamingBackwardsCompatibilityTest {

    private WebElement reference;
    private List<WebElement> references;
    private UIElementValidator validator;
    private ChunkUIElementValidator chunkValidator;

    @Before
    public void setUp() {
        reference = createElement(17, 19, 67, 89);
        references = Arrays.asList(reference);
        validator = spy(UIElementValidator.class);
        chunkValidator = spy(ChunkUIElementValidator.class);
    }


    @Test
    public void withLeftElement() {
        validator.withLeftElement(reference);
        verify(validator).isRightOf(reference);
    }

    @Test
    public void withLeftElementInRange() {
        validator.withLeftElement(reference, 5, 7);
        verify(validator).isRightOf(reference, 5, 7);
    }

    @Test
    public void withRightElement() {
        validator.withRightElement(reference);
        verify(validator).isLeftOf(reference);
    }

    @Test
    public void withRightElementInRange() {
        validator.withRightElement(reference, 5, 7);
        verify(validator).isLeftOf(reference, 5, 7);
    }

    @Test
    public void withTopElement() {
        validator.withTopElement(reference);
        verify(validator).isBelow(reference);
    }

    @Test
    public void withTopElementInRange() {
        validator.withTopElement(reference, 5, 7);
        verify(validator).isBelow(reference, 5, 7);
    }

    @Test
    public void withBottomElement() {
        validator.withBottomElement(reference);
        verify(validator).isAbove(reference);
    }

    @Test
    public void withBottomElementInRange() {
        validator.withBottomElement(reference, 5, 7);
        verify(validator).isAbove(reference, 5, 7);
    }

    @Test
    public void withCssValue() {
            validator.withCssValue("key", "value");
        verify(validator).hasCssValue("key", "value");
    }

    @Test
    public void withoutCssValue() {
            validator.withoutCssValue("key", "value");
        verify(validator).doesNotHaveCssValue("key", "value");
    }

    @Test
    public void sameOffsetLeftAs() {
            validator.sameOffsetLeftAs(reference, "reference");
        verify(validator).isLeftAlignedWith(reference, "reference");
    }

    @Test
    public void sameOffsetLeftAsList() {
            validator.sameOffsetLeftAs(references);
        verify(validator).isLeftAlignedWith(references);
    }

    @Test
    public void sameLeftOffsetChunk() {
        chunkValidator.sameLeftOffset();
        verify(chunkValidator).areLeftAligned();
    }
    
    @Test
    public void sameOffsetRightAs() {
            validator.sameOffsetRightAs(reference, "reference");
        verify(validator).isRightAlignedWith(reference, "reference");
    }

    @Test
    public void sameOffsetRightAsList() {
            validator.sameOffsetRightAs(references);
        verify(validator).isRightAlignedWith(references);
    }

    @Test
    public void sameRightOffsetChunk() {
        chunkValidator.sameRightOffset();
        verify(chunkValidator).areRightAligned();
    }

    @Test
    public void sameOffsetTopAs() {
            validator.sameOffsetTopAs(reference, "reference");
        verify(validator).isTopAlignedWith(reference, "reference");
    }

    @Test
    public void sameOffsetTopAsList() {
            validator.sameOffsetTopAs(references);
        verify(validator).isTopAlignedWith(references);
    }

    @Test
    public void sameTopOffsetChunk() {
        chunkValidator.sameTopOffset();
        verify(chunkValidator).areTopAligned();
    }

    @Test
    public void sameOffsetBottomAs() {
            validator.sameOffsetBottomAs(reference, "reference");
        verify(validator).isBottomAlignedWith(reference, "reference");
    }

    @Test
    public void sameOffsetBottomAsList() {
            validator.sameOffsetBottomAs(references);
        verify(validator).isBottomAlignedWith(references);
    }
    
    @Test
    public void sameBottomOffsetChunk() {
        chunkValidator.sameBottomOffset();
        verify(chunkValidator).areBottomAligned();
    }

    @Test
    public void minWidth() {
        validator.minWidth(9);
        verify(validator).hasWidth(greaterOrEqualTo(percentOrPixels(9)));
    }

    @Test
    public void maxWidth() {
        validator.maxWidth(9);
        verify(validator).hasWidth(lessOrEqualTo(percentOrPixels(9)));
    }

    @Test
    public void widthBetween() {
        validator.widthBetween(9, 13);
        verify(validator).hasWidth(between(percentOrPixels(9)).and(percentOrPixels(13)));
    }

    @Test
    public void minHeight() {
        validator.minHeight(9);
        verify(validator).hasHeight(greaterOrEqualTo(percentOrPixels(9)));
    }

    @Test
    public void maxHeight() {
        validator.maxHeight(9);
        verify(validator).hasHeight(lessOrEqualTo(percentOrPixels(9)));
    }

    @Test
    public void heightBetween() {
        validator.heightBetween(9, 13);
        verify(validator).hasHeight(between(percentOrPixels(9)).and(percentOrPixels(13)));
    }

    @Test
    public void sameWidthAs() {
        validator.sameWidthAs(reference, "reference");
        verify(validator).hasEqualWidthAs(reference, "reference");
    }

    @Test
    public void sameWidthAsList() {
        validator.sameWidthAs(references);
        verify(validator).hasEqualWidthAs(references);
    }

    @Test
    public void sameHeightAs() {
        validator.sameHeightAs(reference, "reference");
        verify(validator).hasEqualHeightAs(reference, "reference");
    }

    @Test
    public void sameHeightAsList() {
        validator.sameHeightAs(references);
        verify(validator).hasEqualHeightAs(references);
    }

    @Test
    public void sameSizeAs() {
        validator.sameSizeAs(reference, "reference");
        verify(validator).hasEqualSizeAs(reference, "reference");
    }

    @Test
    public void sameSizeAsList() {
        validator.sameSizeAs(references);
        verify(validator).hasEqualSizeAs(references);
    }

    @Test
    public void notSameSizeAs() {
        validator.notSameSizeAs(reference, "reference");
        verify(validator).hasDifferentSizeAs(reference, "reference");
    }

    @Test
    public void notSameSizeAsList() {
        validator.notSameSizeAs(references);
        verify(validator).hasDifferentSizeAs(references);
    }

    @Test
    public void insideOf() {
        validator.insideOf(reference, "reference");
        verify(validator).isInsideOf(reference, "reference");
    }

    @Test
    public void insideOfChunk() {
        chunkValidator.insideOf(reference, "reference");
        verify(chunkValidator).areInsideOf(reference, "reference");
    }

    @Test
    public void insideOfPadding() {
        Padding padding = new Padding(3);
        validator.insideOf(reference, "reference", padding);
        verify(validator).isInsideOf(reference, "reference", padding);
    }

    @Test
    public void notOverlapWith() {
        validator.notOverlapWith(reference, "reference");
        verify(validator).isNotOverlapping(reference, "reference");
    }

    @Test
    public void notOverlapWithList() {
        validator.notOverlapWith(references);
        verify(validator).isNotOverlapping(references);
    }

    @Test
    public void overlapWith() {
        validator.overlapWith(reference, "reference");
        verify(validator).isOverlapping(reference, "reference");
    }

    @Test
    public void equalLeftRightOffset() {
        validator.equalLeftRightOffset();
        verify(validator).isCenteredOnPageHorizontally();
    }

    @Test
    public void equalTopBottomOffset() {
        validator.equalTopBottomOffset();
        verify(validator).isCenteredOnPageVertically();
    }

    @Test
    public void areNotOverlappedWithEachOther() {
        chunkValidator.areNotOverlappedWithEachOther();
        verify(chunkValidator).doNotOverlap();
    }

    @Test
    public void withSameSize() {
        chunkValidator.withSameSize();
        verify(chunkValidator).haveEqualSize();
    }

    @Test
    public void withSameWidth() {
        chunkValidator.withSameWidth();
        verify(chunkValidator).haveEqualWidth();
    }

    @Test
    public void withSameHeight() {
        chunkValidator.withSameHeight();
        verify(chunkValidator).haveEqualHeight();
    }

    @Test
    public void withNotSameSize() {
        chunkValidator.withNotSameSize();
        verify(chunkValidator).haveDifferentSizes();
    }

    @Test
    public void withNotSameWidth() {
        chunkValidator.withNotSameWidth();
        verify(chunkValidator).haveDifferentWidths();
    }

    @Test
    public void withNotSameHeight() {
        chunkValidator.withNotSameHeight();
        verify(chunkValidator).haveDifferentHeights();
    }
    @Test
    public void equalLeftRightOffsetChunk() {
        chunkValidator.equalLeftRightOffset();
        verify(chunkValidator).areCenteredOnPageVertically();
    }

    @Test
    public void equalTopBottomOffsetChunk() {
        chunkValidator.equalTopBottomOffset();
        verify(chunkValidator).areCenteredOnPageHorizontally();
    }
}
