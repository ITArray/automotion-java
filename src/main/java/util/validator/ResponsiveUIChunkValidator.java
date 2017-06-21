package util.validator;

import net.itarray.automotion.Element;
import net.itarray.automotion.internal.AbstractValidator;
import net.itarray.automotion.internal.DrawableScreenshot;
import net.itarray.automotion.internal.DriverFacade;
import net.itarray.automotion.internal.ResponsiveUIChunkValidatorBase;
import net.itarray.automotion.internal.Scenario;
import net.itarray.automotion.internal.UIValidatorBase;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

import static net.itarray.automotion.Element.asElement;
import static net.itarray.automotion.Element.asElements;

public class ResponsiveUIChunkValidator extends AbstractValidator implements ChunkValidator {

    public ResponsiveUIChunkValidator(Scenario scenario, DriverFacade driver, List<WebElement> webElements) {
        super(scenario, driver, new ResponsiveUIChunkValidatorBase(scenario, driver, webElements));
        if (webElements.isEmpty()) {
            String message = "Set root web element";
            addError(message);
        }
    }

    protected ResponsiveUIChunkValidatorBase getBase() {
        return (ResponsiveUIChunkValidatorBase) super.getBase();
    }

    /**
     * Change units to Pixels or % (Units.PX, Units.PERCENT)
     *
     * @param units
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator changeMetricsUnitsTo(Units units) {
        getBase().changeMetricsUnitsTo(units);
        return this;
    }

    /**
     * Verify that elements are aligned in a grid view width specified amount of columns
     *
     * @param horizontalGridSize
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator alignedAsGrid(int horizontalGridSize) {
        getBase().alignedAsGrid(horizontalGridSize);
        return this;
    }

    /**
     * Verify that elements are aligned in a grid view width specified amount of columns and rows
     *
     * @param horizontalGridSize
     * @param verticalGridSize
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator alignedAsGrid(int horizontalGridSize, int verticalGridSize) {
        getBase().alignedAsGrid(horizontalGridSize, verticalGridSize);
        return this;
    }

    /**
     * Verify that every element in the list is not overlapped with another element from this list
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator areNotOverlappedWithEachOther() {
        getBase().areNotOverlappedWithEachOther();
        return this;
    }

    /**
     * Verify that elements in the list have the same size
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator withSameSize() {
        getBase().withSameSize();
        return this;
    }

    /**
     * Verify that elements in the list have the same width
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator withSameWidth() {
        getBase().withSameWidth();
        return this;
    }

    /**
     * Verify that elements in the list have the same height
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator withSameHeight() {
        getBase().withSameHeight();
        return this;
    }

    /**
     * Verify that elements in the list have not the same size
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator withNotSameSize() {
        getBase().withNotSameSize();
        return this;
    }

    /**
     * Verify that elements in the list have not the same width
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator withNotSameWidth() {
        getBase().withNotSameWidth();
        return this;
    }

    /**
     * Verify that elements in the list have not the same height
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator withNotSameHeight() {
        getBase().withNotSameHeight();
        return this;
    }

    /**
     * Verify that elements in the list have the right offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator sameRightOffset() {
        getBase().sameRightOffset();
        return this;
    }

    /**
     * Verify that elements in the list have the same left offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator sameLeftOffset() {
        getBase().sameLeftOffset();
        return this;
    }

    /**
     * Verify that elements in the list have the same top offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator sameTopOffset() {
        getBase().sameTopOffset();
        return this;
    }

    /**
     * Verify that elements in the list have the same bottom offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator sameBottomOffset() {
        getBase().sameBottomOffset();
        return this;
    }

    /**
     * Verify that every element in the list have equal right and left offset (aligned horizontally in center)
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator equalLeftRightOffset() {
        getBase().equalLeftRightOffset();
        return this;
    }

    /**
     * Verify that every element in the list have equal top and bottom offset (aligned vertically in center)
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator equalTopBottomOffset() {
        getBase().equalTopBottomOffset();
        return this;
    }

    /**
     * Verify that element(s) is(are) located inside of specified element
     *
     * @param containerElement
     * @param readableContainerName
     * @return ResponsiveUIValidator
     */
    @Override
    public ResponsiveUIChunkValidator insideOf(WebElement containerElement, String readableContainerName) {
        getBase().insideOf(containerElement, readableContainerName);
        return this;
    }
}