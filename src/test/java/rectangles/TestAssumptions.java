package rectangles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIValidator;
import util.validator.UIValidator;

import java.util.function.BiConsumer;

public class TestAssumptions {
    public static boolean validate(WebElement root, WebElement other, BiConsumer<UIValidator, WebElement> assumption) {
        WebDriver driver = new DummyWebDriver();
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(driver).init();

        UIValidator validator = temporary.findElement(root, "Bla");

        assumption.accept(validator, other);
        return validator.validate();
    }

    public static boolean sameOffsetLeftAs(WebElement root, WebElement other) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.sameOffsetLeftAs(webElement, "Blub"));
    }

    public static boolean sameOffsetRightAs(WebElement root, WebElement other) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.sameOffsetRightAs(webElement, "Blub"));
    }

    public static boolean sameOffsetTopAs(WebElement root, WebElement other) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.sameOffsetTopAs(webElement, "Blub"));
    }

    public static boolean sameOffsetBottomAs(WebElement root, WebElement other) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.sameOffsetBottomAs(webElement, "Blub"));
    }

    public static boolean withBottomElement(WebElement root, WebElement other, int minMargin, int maxMargin) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.withBottomElement(webElement, minMargin, maxMargin));
    }

    public static boolean withBottomElement(WebElement other, WebElement root) {
        return validate(root, other, UIValidator::withBottomElement);
    }

    public static boolean withTopElement(WebElement root, WebElement other, int minMargin, int maxMargin) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.withTopElement(webElement, minMargin, maxMargin));
    }

    public static boolean withTopElement(WebElement root, WebElement other) {
        return validate(root, other, UIValidator::withTopElement);
    }

    public static boolean withLeftElement(WebElement root, WebElement other, int minMargin, int maxMargin) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.withLeftElement(webElement, minMargin, maxMargin));
    }

    public static boolean withLeftElement(WebElement root, WebElement other) {
        return validate(root, other, UIValidator::withLeftElement);
    }

    public static boolean withRightElement(WebElement root, WebElement other, int minMargin, int maxMargin) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.withRightElement(webElement, minMargin, maxMargin));
    }

    public static boolean withRightElement(WebElement root, WebElement other) {
        return validate(root, other, UIValidator::withRightElement);
    }

    public static boolean overlapWith(WebElement root, WebElement other) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.overlapWith(webElement, "Blub"));
    }

    public static boolean notOverlapWith(WebElement root, WebElement other) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.notOverlapWith(webElement, "Blub"));
    }
}
