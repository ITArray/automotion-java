package rectangles;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIValidator;
import util.validator.UIValidator;

import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;

public class AlignmentTest {

    private long windowWidth;
    private long windowHeight;
    private int originX;
    private int originY;
    private int cornerX;
    private int cornerY;
    private int delta;
    private WebElement root;

    @Before
    public void setUp() {
        windowWidth = 1920L;
        windowHeight = 1080L;
        originX = 100;
        originY = 600;
        cornerX = 300;
        cornerY = 900;
        delta = 2;
        root = createElement(originX, originY, cornerX, cornerY);
    }

    @Test
    public void isLeftAlignedWithElementsWithEqualOriginX() {
        WebElement other = createElement(originX, up(originY), up(cornerX), down(cornerY));

        assertThat(leftAligned(root, other)).isTrue();
    }

    @Test
    public void isNotLeftAlignedWithElementsWithSmallerOriginX() {
        WebElement other = createElement(down(originX), up(originY), down(cornerX), down(cornerY));

        assertThat(leftAligned(root, other)).isFalse();
    }

    @Test
    public void isNotLeftAlignedWithElementsWithGreaterOriginX() {
        WebElement other = createElement(up(originX), down(originY), down(cornerX), up(cornerY));

        assertThat(leftAligned(root, other)).isFalse();
    }



    @Test
    public void isTopAlignedWithElementsWithEqualOriginY() {
        WebElement other = createElement(up(originX), originY, up(cornerX), down(cornerY));

        assertThat(topAligned(root, other)).isTrue();
    }

    @Test
    public void isNotTopAlignedWithElementsWithSmallerOriginY() {
        WebElement other = createElement(up(originX), down(originY), down(cornerX), down(cornerY));

        assertThat(topAligned(root, other)).isFalse();
    }

    @Test
    public void isNotTopAlignedWithElementsWithGreaterOriginY() {
        WebElement other = createElement(down(originX), up(originY), down(cornerX), up(cornerY));

        assertThat(topAligned(root, other)).isFalse();
    }



    @Ignore
    @Test
    public void isRightAlignedWithElementsWithEqualCornerX() {
        WebElement other = createElement(up(originX), up(originY), cornerX, down(cornerY));

        assertThat(rightAligned(root, other)).isTrue();
    }

    @Test
    public void isNotRightAlignedWithElementsWithLesserCornerX() {
        WebElement other = createElement(up(originX), up(originY), down(cornerX), down(cornerY));

        assertThat(rightAligned(root, other)).isFalse();
    }

    @Test
    public void isNotRightAlignedWithElementsWithGreaterCornerX() {
        WebElement other = createElement(up(originX), up(originY), up(cornerX), down(cornerY));

        assertThat(rightAligned(root, other)).isFalse();
    }



    @Ignore
    @Test
    public void isBottomAlignedWithElementsWithEqualCornerY() {
        WebElement other = createElement(up(originX), up(originY), down(cornerX), cornerY);

        assertThat(bottomAligned(root, other)).isTrue();
    }

    @Test
    public void isNotBottomAlignedWithElementsWithLesserCornerY() {
        WebElement other = createElement(up(originX), up(originY), down(cornerX), down(cornerY));

        assertThat(bottomAligned(root, other)).isFalse();
    }

    @Test
    public void isNotBottomAlignedWithElementsWithGreaterCornerY() {
        WebElement other = createElement(up(originX), up(originY), down(cornerX), up(cornerY));

        assertThat(bottomAligned(root, other)).isFalse();
    }



    private int up(int value) {
        return value+delta;
    }

    private int down(int value) {
        return value-delta;
    }


    private boolean leftAligned(WebElement root, WebElement other) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.sameOffsetLeftAs(webElement, "Blub"));
    }

    private boolean rightAligned(WebElement root, WebElement other) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.sameOffsetRightAs(webElement, "Blub"));
    }

    private boolean topAligned(WebElement root, WebElement other) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.sameOffsetTopAs(webElement, "Blub"));
    }

    private boolean bottomAligned(WebElement root, WebElement other) {
        return validate(root, other, (uiValidator, webElement) -> uiValidator.sameOffsetBottomAs(webElement, "Blub"));
    }

    private boolean validate(WebElement root, WebElement other, BiConsumer<UIValidator, WebElement> assumption) {
        WebDriver driver = new DummyWebDriver(windowWidth, windowHeight);
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(driver).init();

        UIValidator validator = temporary.findElement(root, "Bla");

        assumption.accept(validator, other);
        return validator.validate();
    }

    @NotNull
    private DummyWebElement createElement(int originX, int originY, int cornerX, int cornerY) {
        return new DummyWebElement(
                new Point(originX, originY),
                new Dimension(cornerX-originX, cornerY-originY));
    }

}
