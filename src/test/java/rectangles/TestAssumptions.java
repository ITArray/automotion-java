package rectangles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIChunkValidator;
import util.validator.ResponsiveUIValidator;
import util.validator.UIValidator;
import util.validator.properties.Padding;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class TestAssumptions {
    public static boolean validate(WebElement root, Consumer<UIValidator> assumption) {
        WebDriver driver = new DummyWebDriver();
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(driver).init();

        UIValidator validator = temporary.findElement(root, "Bla");

        assumption.accept(validator);
        return validator.validate();
    }

    public static boolean validate(WebElement root, WebElement other, BiConsumer<UIValidator, WebElement> assumption) {
        WebDriver driver = new DummyWebDriver();
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(driver).init();

        UIValidator validator = temporary.findElement(root, "Bla");

        assumption.accept(validator, other);
        return validator.validate();
    }

    public static boolean validate(WebElement root, List<WebElement> others, BiConsumer<UIValidator, List<WebElement>> assumption) {
        WebDriver driver = new DummyWebDriver();
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(driver).init();

        UIValidator validator = temporary.findElement(root, "Bla");

        assumption.accept(validator, others);
        return validator.validate();
    }

    public static boolean validate(List<WebElement> elements, Consumer<ResponsiveUIChunkValidator> assumption) {
        WebDriver driver = new DummyWebDriver();
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(driver).init();

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

    public static boolean sameOffsetLeftAs(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.sameOffsetLeftAs(other, "Blub"));
    }

    public static boolean sameOffsetLeftAs(WebElement root, List<WebElement> elements) {
        return validate(root, elements, UIValidator::sameOffsetLeftAs);
    }

    public static boolean sameLeftOffset(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::sameLeftOffset);
    }

    public static boolean sameOffsetRightAs(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.sameOffsetRightAs(other, "Blub"));
    }

    public static boolean sameOffsetRightAs(WebElement root, List<WebElement> elements) {
        return validate(root, elements, UIValidator::sameOffsetRightAs);
    }

    public static boolean sameRightOffset(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::sameRightOffset);
    }

    public static boolean sameOffsetTopAs(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.sameOffsetTopAs(other, "Blub"));
    }

    public static boolean sameOffsetTopAs(WebElement root, List<WebElement> elements) {
        return validate(root, elements, UIValidator::sameOffsetTopAs);
    }

    public static boolean sameTopOffset(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::sameTopOffset);
    }

    public static boolean sameOffsetBottomAs(WebElement root, WebElement other) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.sameOffsetBottomAs(other, "Blub"));
    }

    public static boolean sameOffsetBottomAs(WebElement root, List<WebElement> elements) {
        return validate(root, elements, UIValidator::sameOffsetBottomAs);
    }

    public static boolean sameBottomOffset(List<WebElement> elements) {
        return validate(elements, ResponsiveUIChunkValidator::sameBottomOffset);
    }

    public static boolean withBottomElement(WebElement root, WebElement other, int minMargin, int maxMargin) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.withBottomElement(other, minMargin, maxMargin));
    }

    public static boolean withBottomElement(WebElement other, WebElement root) {
        return validate(root, other, UIValidator::withBottomElement);
    }

    public static boolean withTopElement(WebElement root, WebElement other, int minMargin, int maxMargin) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.withTopElement(other, minMargin, maxMargin));
    }

    public static boolean withTopElement(WebElement root, WebElement other) {
        return validate(root, other, UIValidator::withTopElement);
    }

    public static boolean withLeftElement(WebElement root, WebElement other, int minMargin, int maxMargin) {
        return validate(root, uiValidator -> uiValidator.withLeftElement(other, minMargin, maxMargin));
    }

    public static boolean withLeftElement(WebElement root, WebElement other) {
        return validate(root, other, UIValidator::withLeftElement);
    }

    public static boolean withRightElement(WebElement root, WebElement other, int minMargin, int maxMargin) {
        return validate(root, uiValidator -> uiValidator.withRightElement(other, minMargin, maxMargin));
    }

    public static boolean withRightElement(WebElement root, WebElement other) {
        return validate(root, other, UIValidator::withRightElement);
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

    public static boolean heightBetween(WebElement root, int min, int max) {
        return validate(root, uiValidator -> uiValidator.heightBetween(min, max));
    }

    public static boolean widthBetween(WebElement root, int min, int max) {
        return validate(root, uiValidator -> uiValidator.widthBetween(min, max));
    }

    public static boolean minWidth(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.minWidth(value));
    }

    public static boolean maxWidth(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.maxWidth(value));
    }

    public static boolean minHeight(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.minHeight(value));
    }

    public static boolean maxHeight(WebElement root, int value) {
        return validate(root, uiValidator -> uiValidator.maxHeight(value));
    }
}
