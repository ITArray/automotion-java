package net.itarray.automotion.validation;

import net.itarray.automotion.internal.ResponsiveUIChunkValidatorBase;
import net.itarray.automotion.internal.UIValidatorBase;
import net.itarray.automotion.validation.properties.Resolution;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UISnapshot {

    private final ResponsiveUIValidator responsiveUIValidator;
    private final String name;
    private final Resolution resolution;

    public UISnapshot(ResponsiveUIValidator responsiveUIValidator, String name, Resolution resolution) {
        this.responsiveUIValidator = responsiveUIValidator;
        this.name = name;
        this.resolution = resolution;
        resolution.applyTo(responsiveUIValidator.driver);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return String.format("%s - resolution: %s", name, resolution);
    }

    public ResponsiveUIValidator getResponsiveUIValidator() {
        return responsiveUIValidator;
    }

    public UIElementValidator findElement(WebElement webElement, String readableNameOfElement) {
        return new UIValidatorBase(this, webElement, readableNameOfElement);
    }

    public ChunkUIElementValidator findElements(List<WebElement> webElements) {
        return new ResponsiveUIChunkValidatorBase(this, webElements);
    }
}
