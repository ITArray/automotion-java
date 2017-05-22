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

    public static boolean sameOffsetLeftAs(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.sameOffsetLeftAs(other, "Blub"));
    }

    public static boolean sameOffsetRightAs(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.sameOffsetRightAs(other, "Blub"));
    }

    public static boolean sameOffsetTopAs(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.sameOffsetTopAs(other, "Blub"));
    }

    public static boolean sameOffsetBottomAs(WebElement root, WebElement other) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.sameOffsetBottomAs(other, "Blub"));
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

    public static boolean sameHeightAs(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.sameHeightAs(other, "Blub"));
    }

    public static boolean sameHeightAs(WebElement root, List<WebElement> others) {
        return validate(root, others, UIValidator::sameHeightAs);
    }

    public static boolean sameSizeAs(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.sameSizeAs(other, "Blub"));
    }

    public static boolean sameSizeAs(WebElement root, List<WebElement> others) {
        return validate(root, uiValidator -> uiValidator.sameSizeAs(others));
    }

    public static boolean notSameSizeAs(WebElement root, WebElement other) {
        return validate(root, uiValidator -> uiValidator.notSameSizeAs(other, "Blub"));
    }

    public static boolean notSameSizeAs(WebElement root, List<WebElement> others) {
        return validate(root, uiValidator -> uiValidator.notSameSizeAs(others));
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
