package net.itarray.automotion.validation;

import net.itarray.automotion.internal.ResponsiveUIChunkValidatorBase;
import net.itarray.automotion.internal.UIValidatorBase;
import net.itarray.automotion.internal.ZoomQuery;
import net.itarray.automotion.validation.properties.Resolution;
import net.itarray.automotion.validation.properties.Zoom;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UISnapshot {

    private final ResponsiveUIValidator responsiveUIValidator;
    private final String name;
    private final Resolution resolution;
    private final Zoom zoom;

    public UISnapshot(ResponsiveUIValidator responsiveUIValidator, String name, Resolution resolution, Zoom zoom) {
        this.responsiveUIValidator = responsiveUIValidator;
        this.name = name;
        this.resolution = resolution.queryIfUnkown(responsiveUIValidator.driver);
        this.zoom = zoom.queryIfUnkown(responsiveUIValidator.driver);
        resolution.applyTo(responsiveUIValidator.driver);
        zoom.applyTo(responsiveUIValidator.driver);
    }

    public String getName() {
        return name;
    }

    public double getZoomFactor() {
        return zoom.getFactor(responsiveUIValidator.getDriver());
    }

    public String getDescription() {
        return String.format("%s - resolution: %s - zoom: %s", name, resolution, zoom);
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
