package net.itarray.automotion.validation;

import net.itarray.automotion.internal.ResponsiveUIChunkValidatorBase;
import net.itarray.automotion.internal.UIValidatorBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Scene {

    private final Report report;
    private final String name;

    public Scene(Report report, String name) {
        this.report = report;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Report getReport() {
        return report;
    }

    public NewValidator findElement(WebElement webElement, String readableNameOfElement) {
        return new UIValidatorBase(this, webElement, readableNameOfElement);
    }

    public NewChunkValidator findElements(List<WebElement> webElements) {
        return new ResponsiveUIChunkValidatorBase(this, webElements);
    }
}
