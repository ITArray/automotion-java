package net.itarray.automotion.internal;

import net.itarray.automotion.Report;
import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIChunkValidator;
import util.validator.ResponsiveUIValidator;
import util.validator.UIValidator;

import java.awt.*;

public class Scenario extends ResponsiveUIValidator {

    private final String name;

    public Scenario(Report report, String scenarioName) {
        super(report);
        this.name = scenarioName;
    }

    /**
     * @deprecated As of release 2.0, replaced by{@link util.validator.ResponsiveUIValidator#drawMap()}
     */
    @Deprecated()
    public Scenario drawMap() {
        super.drawMap();
        return this;
    }

    public UIValidator findElement(WebElement element, String readableNameOfElement) {
        return new UIValidator(this, getDriver(), element, readableNameOfElement);
    }

    public ResponsiveUIChunkValidator findElements(java.util.List<WebElement> elements) {
        return new ResponsiveUIChunkValidator(this, getDriver(), elements);
    }

    public String getName() {
        return name;
    }
}
