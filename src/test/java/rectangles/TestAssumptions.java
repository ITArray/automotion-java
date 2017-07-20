package rectangles;

import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIChunkValidator;
import util.validator.ResponsiveUIValidator;
import util.validator.UIValidator;
import util.validator.properties.Padding;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static net.itarray.automotion.internal.properties.Expression.percentOrPixels;
import static net.itarray.automotion.validation.properties.Condition.*;
import static rectangles.DummyDriverFacade.createWebDriver;

public class TestAssumptions {
    public static boolean validate(WebElement root, Consumer<UIValidator> assumption) {
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(createWebDriver()).init();

        UIValidator validator = temporary.findElement(root, "Bla");

        assumption.accept(validator);
        return validator.validate();
    }

    public static boolean validate(WebElement root, WebElement other, BiConsumer<UIValidator, WebElement> assumption) {
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(createWebDriver()).init();

        UIValidator validator = temporary.findElement(root, "Bla");

        assumption.accept(validator, other);
        return validator.validate();
    }

    public static boolean validate(WebElement root, List<WebElement> others, BiConsumer<UIValidator, List<WebElement>> assumption) {
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(createWebDriver()).init();

        UIValidator validator = temporary.findElement(root, "Bla");

        assumption.accept(validator, others);
        return validator.validate();
    }

    public static boolean validate(List<WebElement> elements, Consumer<ResponsiveUIChunkValidator> assumption) {
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(createWebDriver()).init();

        ResponsiveUIChunkValidator validator = temporary.findElements(elements);

        assumption.accept(validator);
        return validator.validate();
    }

    public static boolean minOffset(WebElement root, int top, int right, int bottom, int left) {
        return validate(root, uiValidator -> uiValidator.minOffset(top, right, bottom, left));
    }

    public static boolean hasLeftOffsetToPageGreaterOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasLeftOffsetToPage(greaterOrEqualTo(value)));
    }

    public static boolean hasLeftOffsetToPageLessOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasLeftOffsetToPage(lessOrEqualTo(value)));
    }

    public static boolean hasRightOffsetToPageGreaterOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasRightOffsetToPage(greaterOrEqualTo(value)));
    }

    public static boolean hasRightOffsetToPageLessOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasRightOffsetToPage(lessOrEqualTo(value)));
    }

    public static boolean hasTopOffsetToPageGreaterOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasTopOffsetToPage(greaterOrEqualTo(value)));
    }

    public static boolean hasTopOffsetToPageLessOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasTopOffsetToPage(lessOrEqualTo(value)));
    }

    public static boolean hasBottomOffsetToPageGreaterOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasBottomOffsetToPage(greaterOrEqualTo(value)));
    }

    public static boolean hasBottomOffsetToPageLessOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasBottomOffsetToPage(lessOrEqualTo(value)));
    }

    public static boolean maxOffset(WebElement root, int top, int right, int bottom, int left) {
        return validate(root, uiValidator -> uiValidator.maxOffset(top, right, bottom, left));
    }

    public static boolean equalLeftRightOffset(WebElement root) {
        return validate(root, UIValidator::equalLeftRightOffset);
    }

    public static boolean equalLeftRightOffset(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::equalLeftRightOffset);
    }

    public static boolean equalTopBottomOffset(WebElement root) {
        return validate(root, UIValidator::equalTopBottomOffset);
    }

    public static boolean equalTopBottomOffset(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::equalTopBottomOffset);
    }

    public static boolean isLeftAlignedWith(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.isLeftAlignedWith(other, "Blub"));
    }

    public static boolean isLeftAlignedWith(WebElement root, List<WebElement> elements) {
        return validate(root, elements, UIValidator::isLeftAlignedWith);
    }

    public static boolean areLeftAligned(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::areLeftAligned);
    }

    public static boolean isRightAlignedWith(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.isRightAlignedWith(other, "Blub"));
    }

    public static boolean isRightAlignedWith(WebElement root, List<WebElement> elements) {
        return validate(root, elements, UIValidator::isRightAlignedWith);
    }

    public static boolean areRightAligned(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::areRightAligned);
    }

    public static boolean isTopAlignedWith(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.isTopAlignedWith(other, "Blub"));
    }

    public static boolean isTopAlignedWith(WebElement root, List<WebElement> elements) {
        return validate(root, elements, UIValidator::isTopAlignedWith);
    }

    public static boolean areTopAligned(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::areTopAligned);
    }

    public static boolean isBottomAlignedWith(WebElement root, WebElement other) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.isBottomAlignedWith(other, "Blub"));
    }

    public static boolean isBottomAlignedWith(WebElement root, List<WebElement> elements) {
        return validate(root, elements, UIValidator::isBottomAlignedWith);
    }

    public static boolean areBottomAligned(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::areBottomAligned);
    }

    public static boolean isAbove(WebElement root, WebElement other, int minMargin, int maxMargin) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.isAbove(other, minMargin, maxMargin));
    }

    public static boolean isAbove(WebElement other, WebElement root) {
        return validate(root, other, UIValidator::isAbove);
    }

    public static boolean isBelow(WebElement root, WebElement other, int minMargin, int maxMargin) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.isBelow(other, minMargin, maxMargin));
    }

    public static boolean isBelow(WebElement root, WebElement other) {
        return validate(root, other, UIValidator::isBelow);
    }

    public static boolean isRightOf(WebElement root, WebElement other, int minMargin, int maxMargin) {
        return validate(root, uiValidator -> uiValidator.isRightOf(other, minMargin, maxMargin));
    }

    public static boolean isRightOf(WebElement root, WebElement other) {
        return validate(root, other, UIValidator::isRightOf);
    }

    public static boolean isLeftOf(WebElement root, WebElement other, int minMargin, int maxMargin) {
        return validate(root, uiValidator -> uiValidator.isLeftOf(other, minMargin, maxMargin));
    }

    public static boolean isLeftOf(WebElement root, WebElement other) {
        return validate(root, other, UIValidator::isLeftOf);
    }

    public static boolean overlapWith(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.overlapWith(other, "Blub"));
    }

    public static boolean notOverlapWith(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.notOverlapWith(other, "Blub"));
    }

    public static boolean areNotOverlappedWithEachOther(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::areNotOverlappedWithEachOther);
    }

    public static boolean isInsideOf(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.isInsideOf(other, "Blub"));
    }

    public static boolean isInsideOf(WebElement root, WebElement other, Padding padding) {
        return validate(root, uiValidator -> uiValidator.isInsideOf(other, "Blub", padding));
    }

    public static boolean areInsideOf(List<WebElement> elements, WebElement container) {
        return validate(elements, validator -> validator.areInsideOf(container, "Bla"));
    }

    public static boolean sameWidthAs(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.sameWidthAs(other, "Blub"));
    }

    public static boolean sameWidthAs(WebElement root, List<WebElement> others) {
        return validate(root, uiValidator -> uiValidator.sameWidthAs(others));
    }

    public static boolean withSameWidth(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::withSameWidth);
    }

    public static boolean withNotSameWidth(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::withNotSameWidth);
    }

    public static boolean sameHeightAs(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.sameHeightAs(other, "Blub"));
    }

    public static boolean sameHeightAs(WebElement root, List<WebElement> others) {
        return validate(root, others, UIValidator::sameHeightAs);
    }

    public static boolean withSameHeight(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::withSameHeight);
    }

    public static boolean withNotSameHeight(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::withNotSameHeight);
    }

    public static boolean sameSizeAs(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.sameSizeAs(other, "Blub"));
    }

    public static boolean sameSizeAs(WebElement root, List<WebElement> others) {
        return validate(root, uiValidator -> uiValidator.sameSizeAs(others));
    }

    public static boolean withSameSize(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::withSameSize);
    }

    public static boolean notSameSizeAs(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.notSameSizeAs(other, "Blub"));
    }

    public static boolean notSameSizeAs(WebElement root, List<WebElement> others) {
        return validate(root, uiValidator -> uiValidator.notSameSizeAs(others));
    }

    public static boolean withNotSameSize(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::withNotSameSize);
    }

    public static boolean hasHeightBetween(WebElement root, int min, int max) {
        return validate(root, uiValidator -> uiValidator.hasHeight(between(min).and(max)));
    }

    public static boolean hasWidthBetween(WebElement root, int min, int max) {
        return validate(root, uiValidator -> uiValidator.hasWidth(between(min).and(max)));
    }

    public static boolean hasWidthGreaterOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasWidth(greaterOrEqualTo(value)));
    }

    public static boolean hasWidthLessOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasWidth(lessOrEqualTo(value)));
    }

    public static boolean hasHeightGreaterOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasHeight(greaterOrEqualTo(value)));
    }

    public static boolean hasHeightLessOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasHeight(lessOrEqualTo(value)));
    }
}
