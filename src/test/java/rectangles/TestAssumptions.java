package rectangles;

import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIChunkValidator;
import util.validator.ResponsiveUIValidator;
import util.validator.UIValidator;
import util.validator.properties.Padding;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

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

    public static boolean isVerticallyLeftAlignedWith(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.isVerticallyLeftAlignedWith(other, "Blub"));
    }

    public static boolean isVerticallyLeftAlignedWith(WebElement root, List<WebElement> elements) {
        return validate(root, elements, UIValidator::isVerticallyLeftAlignedWith);
    }

    public static boolean areVerticallyLeftAligned(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::areVerticallyLeftAligned);
    }

    public static boolean isVerticallyRightAlignedWith(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.isVerticallyRightAlignedWith(other, "Blub"));
    }

    public static boolean isVerticallyRightAlignedWith(WebElement root, List<WebElement> elements) {
        return validate(root, elements, UIValidator::isVerticallyRightAlignedWith);
    }

    public static boolean areVerticallyRightAligned(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::areVerticallyRightAligned);
    }

    public static boolean isHorizontallyTopAlignedWith(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.isHorizontallyTopAlignedWith(other, "Blub"));
    }

    public static boolean isHorizontallyTopAlignedWith(WebElement root, List<WebElement> elements) {
        return validate(root, elements, UIValidator::isHorizontallyTopAlignedWith);
    }

    public static boolean areHorizontallyTopAligned(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::areHorizontallyTopAligned);
    }

    public static boolean isHorizontallyBottomAlignedWith(WebElement root, WebElement other) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.isHorizontallyBottomAlignedWith(other, "Blub"));
    }

    public static boolean isHorizontallyBottomAlignedWith(WebElement root, List<WebElement> elements) {
        return validate(root, elements, UIValidator::isHorizontallyBottomAlignedWith);
    }

    public static boolean areHorizontallyBottomAligned(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::areHorizontallyBottomAligned);
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

    public static boolean insideOf(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.insideOf(other, "Blub"));
    }

    public static boolean insideOf(WebElement root, WebElement other, Padding padding) {
        return validate(root, uiValidator -> uiValidator.insideOf(other, "Blub", padding));
    }

    public static boolean insideOf(List<WebElement> elements, WebElement container) {
        return validate(elements, validator -> validator.insideOf(container, "Bla"));
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
        return validate(root, uiValidator -> uiValidator.hasHeightBetween(min, max));
    }

    public static boolean hasWidthBetween(WebElement root, int min, int max) {
        return validate(root, uiValidator -> uiValidator.hasWidthBetween(min, max));
    }

    public static boolean hasWidthGreaterOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasWidthGreaterOrEqualTo(value));
    }

    public static boolean hasWidthLessOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasWidthLessOrEqualTo(value));
    }

    public static boolean hasHeightGreaterOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasHeightGreaterOrEqualTo(value));
    }

    public static boolean hasHeightLessOrEqualTo(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.hasHeightLessOrEqualTo(value));
    }
}
