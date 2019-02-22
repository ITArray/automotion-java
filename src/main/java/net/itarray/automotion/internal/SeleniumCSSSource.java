package net.itarray.automotion.internal;

import org.openqa.selenium.WebElement;

public class SeleniumCSSSource extends CSSSource{

    private final WebElement webElement;

    public SeleniumCSSSource(WebElement webElement) {
        this.webElement = webElement;
    }

    @Override
    public String getCssValue(String propertyName) {
        return webElement.getCssValue(propertyName);
    }
}
