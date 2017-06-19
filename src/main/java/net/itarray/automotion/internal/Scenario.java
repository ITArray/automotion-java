package net.itarray.automotion.internal;

import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIChunkValidator;
import util.validator.ResponsiveUIValidator;
import util.validator.UIValidator;

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

    @Deprecated()
    public void setTopBarMobileOffset(boolean state) {
        factory.setTopBarMobileOffset(state);
    }

}
