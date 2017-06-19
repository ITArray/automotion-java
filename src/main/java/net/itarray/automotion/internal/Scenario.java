package net.itarray.automotion.internal;

import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIChunkValidator;
import util.validator.ResponsiveUIValidator;
import util.validator.UIValidator;

import java.awt.*;

public class Scenario extends ResponsiveUIValidator {

    private final String name;
    private final ResponsiveUIValidator factory;

    public Scenario(DriverFacade driver, String scenarioName, ResponsiveUIValidator factory) {
        super(driver);
        this.name = scenarioName;
        this.factory = factory;
    }

    public UIValidator findElement(WebElement element, String readableNameOfElement) {
        return new UIValidator(this, driver, element, readableNameOfElement);
    }

    public ResponsiveUIChunkValidator findElements(java.util.List<WebElement> elements) {
        return new ResponsiveUIChunkValidator(this, driver, elements);
    }

    public String getName() {
        return name;
    }

    /**
     * @deprecated As of release 2.0, replaced by{@link util.validator.ResponsiveUIValidator#setTopBarMobileOffset(boolean)}
     */
    @Deprecated()
    public void setTopBarMobileOffset(boolean state) {
        factory.setTopBarMobileOffset(state);
    }

    /**
     * @deprecated As of release 2.0, replaced by{@link util.validator.ResponsiveUIValidator#setColorForRootElement(java.awt.Color)}
     */
    @Deprecated()
    public void setColorForRootElement(Color color) {
        factory.setColorForRootElement(color);
    }

    /**
     * @deprecated As of release 2.0, replaced by{@link util.validator.ResponsiveUIValidator#setColorForHighlightedElements(java.awt.Color)}
     */
    @Deprecated()
    public void setColorForHighlightedElements(Color color) {
        factory.setColorForHighlightedElements(color);
    }

    /**
     * @deprecated As of release 2.0, replaced by{@link util.validator.ResponsiveUIValidator#setLinesColor(java.awt.Color)}
     */
    @Deprecated()
    public void setLinesColor(Color color) {
        factory.setLinesColor(color);
    }

}
