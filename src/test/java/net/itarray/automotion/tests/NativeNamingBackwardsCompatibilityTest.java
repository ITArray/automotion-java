package net.itarray.automotion.tests;

import net.itarray.automotion.validation.UIElementValidator;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.mockito.Mockito.*;
import static rectangles.DummyWebElement.createElement;
import static rectangles.DummyWebElement.createRootElement;

public class NativeNamingBackwardsCompatibilityTest {

    private WebElement root;
    private WebElement other;
    private UIElementValidator validator;

    @Before
    public void setUp() {
        root = createRootElement();
        other = createElement(17, 19, 67, 89);
        validator = spy(UIElementValidator.class);
    }


    @Test
    public void withLeftElement() {
        validator.withLeftElement(other);
        verify(validator).isRightOf(other);
    }

    @Test
    public void withLeftElementInRange() {
        validator.withLeftElement(other, 5, 7);
        verify(validator).isRightOf(other, 5, 7);
    }

    @Test
    public void withRightElement() {
        validator.withRightElement(other);
        verify(validator).isLeftOf(other);
    }

    @Test
    public void withRightElementInRange() {
        validator.withRightElement(other, 5, 7);
        verify(validator).isLeftOf(other, 5, 7);
    }

    @Test
    public void withTopElement() {
        validator.withTopElement(other);
        verify(validator).isBelow(other);
    }

    @Test
    public void withTopElementInRange() {
        validator.withTopElement(other, 5, 7);
        verify(validator).isBelow(other, 5, 7);
    }

    @Test
    public void withBottomElement() {
        validator.withBottomElement(other);
        verify(validator).isAbove(other);
    }

    @Test
    public void withBottomElementInRange() {
        validator.withBottomElement(other, 5, 7);
        verify(validator).isAbove(other, 5, 7);
    }

}
