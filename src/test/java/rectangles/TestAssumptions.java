package rectangles;

import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIChunkValidator;
import util.validator.ResponsiveUIValidator;
import util.validator.UIValidator;
import util.validator.properties.Padding;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static net.itarray.automotion.validation.properties.Condition.*;
import static rectangles.DummyDriverFacade.createWebDriver;

public class TestAssumptions {
    public static boolean validate(WebElement root, Consumer<UIValidator> assumption, int tolerance) {
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(createWebDriver()).withTolerance(tolerance).init();

        UIValidator validator = temporary.findElement(root, "Bla");

        assumption.accept(validator);
        return validator.validate();
    }

    public static boolean validate(WebElement root, WebElement other, BiConsumer<UIValidator, WebElement> assumption, int tolerance) {
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(createWebDriver()).withTolerance(tolerance).init();

        UIValidator validator = temporary.findElement(root, "Bla");

        assumption.accept(validator, other);
        return validator.validate();
    }

    public static boolean validate(WebElement root, List<WebElement> others, BiConsumer<UIValidator, List<WebElement>> assumption, int tolerance) {
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(createWebDriver()).withTolerance(tolerance).init();

        UIValidator validator = temporary.findElement(root, "Bla");

        assumption.accept(validator, others);
        return validator.validate();
    }

    public static boolean validate(List<WebElement> elements, Consumer<ResponsiveUIChunkValidator> assumption, int tolerance) {
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(createWebDriver()).withTolerance(tolerance).init();

        ResponsiveUIChunkValidator validator = temporary.findElements(elements);

        assumption.accept(validator);
        return validator.validate();
    }

    public static boolean minOffset(WebElement root, int top, int right, int bottom, int left) {
        return validate(root, uiValidator -> uiValidator.minOffset(top, right, bottom, left), 0);
    }

    public static boolean hasLeftOffsetToPageGreaterOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasLeftOffsetToPage(greaterOrEqualTo(value)), 0);
    }

    public static boolean hasLeftOffsetToPageLessOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasLeftOffsetToPage(lessOrEqualTo(value)), 0);
    }

    public static boolean hasRightOffsetToPageGreaterOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasRightOffsetToPage(greaterOrEqualTo(value)), 0);
    }

    public static boolean hasRightOffsetToPageLessOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasRightOffsetToPage(lessOrEqualTo(value)), 0);
    }

    public static boolean hasTopOffsetToPageGreaterOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasTopOffsetToPage(greaterOrEqualTo(value)), 0);
    }

    public static boolean hasTopOffsetToPageLessOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasTopOffsetToPage(lessOrEqualTo(value)), 0);
    }

    public static boolean hasBottomOffsetToPageGreaterOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasBottomOffsetToPage(greaterOrEqualTo(value)), 0);
    }

    public static boolean hasBottomOffsetToPageLessOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasBottomOffsetToPage(lessOrEqualTo(value)), 0);
    }

    public static boolean maxOffset(WebElement root, int top, int right, int bottom, int left) {
        return validate(root, uiValidator -> uiValidator.maxOffset(top, right, bottom, left), 0);
    }

    public static boolean isCenteredOnPageHorizontally(WebElement root) {
        return validate(root, UIValidator::isCenteredOnPageHorizontally, 0);
    }

    public static boolean areCenteredOnPageVertically(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::areCenteredOnPageVertically, 0);
    }

    public static boolean isCenteredOnPageVertically(WebElement root) {
        return validate(root, UIValidator::isCenteredOnPageVertically, 0);
    }

    public static boolean areCenteredOnPageHorizontally(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::areCenteredOnPageHorizontally, 0);
    }

    public static boolean isLeftAlignedWith(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.isLeftAlignedWith(other, "Blub"), 0);
    }

    public static boolean isLeftAlignedWith(WebElement root, List<WebElement> elements) {
        return validate(root, elements, UIValidator::isLeftAlignedWith, 0);
    }

    public static boolean areLeftAligned(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::areLeftAligned, 0);
    }

    public static boolean isRightAlignedWith(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.isRightAlignedWith(other, "Blub"), 0);
    }

    public static boolean isRightAlignedWith(WebElement root, List<WebElement> elements) {
        return validate(root, elements, UIValidator::isRightAlignedWith, 0);
    }

    public static boolean areRightAligned(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::areRightAligned, 0);
    }

    public static boolean isTopAlignedWith(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.isTopAlignedWith(other, "Blub"), 0);
    }

    public static boolean isTopAlignedWith(WebElement root, List<WebElement> elements) {
        return validate(root, elements, UIValidator::isTopAlignedWith, 0);
    }

    public static boolean areTopAligned(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::areTopAligned, 0);
    }

    public static boolean isBottomAlignedWith(WebElement root, WebElement other) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.isBottomAlignedWith(other, "Blub"), 0);
    }

    public static boolean isBottomAlignedWith(WebElement root, List<WebElement> elements) {
        return validate(root, elements, UIValidator::isBottomAlignedWith, 0);
    }

    public static boolean areBottomAligned(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::areBottomAligned, 0);
    }

    public static boolean isAbove(WebElement root, WebElement other, int minMargin, int maxMargin) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.isAbove(other, between(minMargin).and(maxMargin)), 0);
    }

    public static boolean isAbove(WebElement other, WebElement root) {
        return validate(root, other, UIValidator::isAbove, 0);
    }

    public static boolean isBelow(WebElement root, WebElement other, int minMargin, int maxMargin) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.isBelow(other, between(minMargin).and(maxMargin)), 0);
    }

    public static boolean isBelow(WebElement root, WebElement other) {
        return validate(root, other, UIValidator::isBelow, 0);
    }

    public static boolean isRightOf(WebElement root, WebElement other, int minMargin, int maxMargin) {
        return validate(root, uiValidator -> uiValidator.isRightOf(other, between(minMargin).and(maxMargin)), 0);
    }

    public static boolean isRightOf(WebElement root, WebElement other) {
        return validate(root, other, UIValidator::isRightOf, 0);
    }

    public static boolean isLeftOf(WebElement root, WebElement other, int minMargin, int maxMargin) {
        return validate(root, uiValidator -> uiValidator.isLeftOf(other, between(minMargin).and(maxMargin)), 0);
    }

    public static boolean isLeftOf(WebElement root, WebElement other) {
        return validate(root, other, UIValidator::isLeftOf, 0);
    }

    public static boolean isOverlapping(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.isOverlapping(other, "Blub"), 0);
    }

    public static boolean isNotOverlapping(WebElement root, WebElement other) {
        return isNotOverlapping(root, other, 0);
    }

    public static boolean isNotOverlapping(WebElement root, WebElement other, int tolerance) {
        return validate(root, uiValidator -> uiValidator.isNotOverlapping(other, "Blub"), tolerance);
    }

    public static boolean doNotOverlap(List<WebElement> elements) {
        return doNotOverlap(elements, 0);
    }

    public static boolean doNotOverlap(List<WebElement> elements, int tolerance) {
        return validate(elements, ResponsiveUIChunkValidator::doNotOverlap, tolerance);
    }

    public static boolean isInsideOf(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.isInsideOf(other, "Blub"), 0);
    }

    public static boolean isInsideOf(WebElement root, WebElement other, Padding padding) {
        return validate(root, uiValidator -> uiValidator.isInsideOf(other, "Blub", padding), 0);
    }

    public static boolean areInsideOf(List<WebElement> elements, WebElement container) {
        return validate(elements, validator -> validator.areInsideOf(container, "Bla"), 0);
    }

    public static boolean hasEqualWidthAs(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.hasEqualWidthAs(other, "Blub"), 0);
    }

    public static boolean hasEqualWidthAs(WebElement root, List<WebElement> others) {
        return validate(root, uiValidator -> uiValidator.hasEqualWidthAs(others), 0);
    }

    public static boolean haveEqualWidth(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::haveEqualWidth, 0);
    }

    public static boolean haveDifferentWidths(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::haveDifferentWidths, 0);
    }

    public static boolean hasEqualHeightAs(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.hasEqualHeightAs(other, "Blub"), 0);
    }

    public static boolean hasEqualHeightAs(WebElement root, List<WebElement> others) {
        return validate(root, others, UIValidator::hasEqualHeightAs, 0);
    }

    public static boolean haveEqualHeight(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::haveEqualHeight, 0);
    }

    public static boolean haveDifferentHeights(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::haveDifferentHeights, 0);
    }

    public static boolean hasEqualSizeAs(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.hasEqualSizeAs(other, "Blub"), 0);
    }

    public static boolean hasEqualSizeAs(WebElement root, List<WebElement> others) {
        return validate(root, uiValidator -> uiValidator.hasEqualSizeAs(others), 0);
    }

    public static boolean haveEqualSize(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::haveEqualSize, 0);
    }

    public static boolean hasDifferentSizeAs(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.hasDifferentSizeAs(other, "Blub"), 0);
    }

    public static boolean hasDifferentSizeAs(WebElement root, List<WebElement> others) {
        return validate(root, uiValidator -> uiValidator.hasDifferentSizeAs(others), 0);
    }

    public static boolean haveDifferentSizes(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::haveDifferentSizes, 0);
    }

    public static boolean hasHeightBetween(WebElement root, int min, int max) {
        return validate(root, uiValidator -> uiValidator.hasHeight(between(min).and(max)), 0);
    }

    public static boolean hasWidthBetween(WebElement root, int min, int max) {
        return validate(root, uiValidator -> uiValidator.hasWidth(between(min).and(max)), 0);
    }

    public static boolean hasWidthGreaterOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasWidth(greaterOrEqualTo(value)), 0);
    }

    public static boolean hasWidthLessOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasWidth(lessOrEqualTo(value)), 0);
    }

    public static boolean hasHeightGreaterOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasHeight(greaterOrEqualTo(value)), 0);
    }

    public static boolean hasHeightLessOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasHeight(lessOrEqualTo(value)), 0);
    }
}
