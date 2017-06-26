package net.itarray.automotion.internal;

import util.validator.ResponsiveUIValidator;

import java.awt.*;

public abstract class AbstractValidator extends ResponsiveUIValidator{

    private final ResponsiveUIValidatorBase base;

    protected AbstractValidator(DriverFacade driver, ResponsiveUIValidatorBase base) {
        super(driver);
        this.base = base;
    }

    protected ResponsiveUIValidatorBase getBase() {
        return base;
    }

    @Override
    public boolean isWithReport() {
        return getBase().isWithReport();
    }

    public AbstractValidator drawMap() {
        getBase().drawMap();
        return this;
    }

    public AbstractValidator dontDrawMap() {
        getBase().dontDrawMap();
        return this;
    }


    protected void addError(String message) {
        getBase().addError(message);
    }

    @Override
    public boolean validate() {
        return getBase().validate();
    }

    public void addJsonFile(String jsonFileName) {
        getBase().addJsonFile(jsonFileName);
    }

    /**
     * @deprecated As of release 2.0, replaced by{@link util.validator.ResponsiveUIValidator#setTopBarMobileOffset(boolean)}
     */
    @Deprecated()
    public void setTopBarMobileOffset(boolean state) {
        getBase().setTopBarMobileOffset(state);
    }

    /**
     * @deprecated As of release 2.0, replaced by{@link util.validator.ResponsiveUIValidator#setColorForRootElement(java.awt.Color)}
     */
    @Deprecated()
    public void setColorForRootElement(Color color) {
        getBase().setColorForRootElement(color);
    }

    /**
     * @deprecated As of release 2.0, replaced by{@link util.validator.ResponsiveUIValidator#setColorForHighlightedElements(java.awt.Color)}
     */
    @Deprecated()
    public void setColorForHighlightedElements(Color color) {
        getBase().setColorForHighlightedElements(color);
    }

    /**
     * @deprecated As of release 2.0, replaced by{@link util.validator.ResponsiveUIValidator#setLinesColor(java.awt.Color)}
     */
    @Deprecated()
    public void setLinesColor(Color color) {
        getBase().setLinesColor(color);
    }

    @Override
    public DrawingConfiguration getDrawingConfiguration() {
        return getBase().getDrawingConfiguration();
    }
}
