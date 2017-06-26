package net.itarray.automotion.internal;

import net.itarray.automotion.validation.UISnapshot;
import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIChunkValidator;
import util.validator.ResponsiveUIValidator;
import util.validator.UIValidator;

public class Scenario extends ResponsiveUIValidator {

    private final UISnapshot snapshot;

    public Scenario(UISnapshot snapshot) {
        super(snapshot.getResponsiveUIValidator());
        this.snapshot = snapshot;
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
        return new UIValidator(snapshot, getDriver(), element, readableNameOfElement);
    }

    public ResponsiveUIChunkValidator findElements(java.util.List<WebElement> elements) {
        return new ResponsiveUIChunkValidator(snapshot, getDriver(), elements);
    }

    public String getName() {
        return snapshot.getName();
    }
}
