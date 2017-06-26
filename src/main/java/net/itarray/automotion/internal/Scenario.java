package net.itarray.automotion.internal;

import net.itarray.automotion.validation.Scene;
import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIChunkValidator;
import util.validator.ResponsiveUIValidator;
import util.validator.UIValidator;

public class Scenario extends ResponsiveUIValidator {

    private final Scene scene;

    public Scenario(Scene scene) {
        super(scene.getReport());
        this.scene = scene;
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
        return new UIValidator(scene, getDriver(), element, readableNameOfElement);
    }

    public ResponsiveUIChunkValidator findElements(java.util.List<WebElement> elements) {
        return new ResponsiveUIChunkValidator(scene, getDriver(), elements);
    }

    public String getName() {
        return scene.getName();
    }
}
