package net.itarray.automotion.validation;

import net.itarray.automotion.internal.ResponsiveUIChunkValidatorBase;
import net.itarray.automotion.internal.UIValidatorBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UISnapshot {

    private final ResponsiveUIValidator responsiveUIValidator;
    private final String name;

    public UISnapshot(ResponsiveUIValidator responsiveUIValidator, String name) {
        this.responsiveUIValidator = responsiveUIValidator;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ResponsiveUIValidator getResponsiveUIValidator() {
        return responsiveUIValidator;
    }

    public Validator findElement(WebElement webElement, String readableNameOfElement) {
        return new UIValidatorBase(this, webElement, readableNameOfElement);
    }

    public ChunkValidator findElements(List<WebElement> webElements) {
        return new ResponsiveUIChunkValidatorBase(this, webElements);
    }
}
