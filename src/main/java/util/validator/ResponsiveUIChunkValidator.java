package util.validator;

import net.itarray.automotion.internal.DriverFacade;
import org.openqa.selenium.WebElement;

import java.util.List;

import static net.itarray.automotion.Element.asElement;
import static net.itarray.automotion.Element.asElements;

public class ResponsiveUIChunkValidator extends ResponsiveUIValidator implements ChunkValidator {

    ResponsiveUIChunkValidator(DriverFacade driver, List<WebElement> webElements) {
        super(driver);
        rootElements = asElements(webElements);
        if (!webElements.isEmpty()) {
            setRootElement(rootElements.get(0));
        } else {
            errors.add("Set root web element");
        }
        startTime = System.currentTimeMillis();
    }

    /**
     * Change units to Pixels or % (Units.PX, Units.PERCENT)
     *
     * @param units
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator changeMetricsUnitsTo(Units units) {
        this.units = units;
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
        validateGridAlignment(rootElements, horizontalGridSize, 0);
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
        validateGridAlignment(rootElements, horizontalGridSize, verticalGridSize);
        return this;
    }

    /**
     * Verify that every element in the list is not overlapped with another element from this list
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator areNotOverlappedWithEachOther() {
        validateElementsAreNotOverlapped(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have the same size
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator withSameSize() {
        validateSameSize(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have the same width
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator withSameWidth() {
        validateSameWidth(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have the same height
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator withSameHeight() {
        validateSameHeight(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have not the same size
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator withNotSameSize() {
        validateNotSameSize(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have not the same width
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator withNotSameWidth() {
        validateNotSameWidth(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have not the same height
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator withNotSameHeight() {
        validateNotSameHeight(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have the right offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator sameRightOffset() {
        validateRightOffsetForChunk(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have the same left offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator sameLeftOffset() {
        validateLeftOffsetForChunk(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have the same top offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator sameTopOffset() {
        validateTopOffsetForChunk(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have the same bottom offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator sameBottomOffset() {
        validateBottomOffsetForChunk(rootElements);
        return this;
    }

    /**
     * Verify that every element in the list have equal right and left offset (aligned horizontally in center)
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator equalLeftRightOffset() {
        validateEqualLeftRightOffset(rootElements);
        return this;
    }

    /**
     * Verify that every element in the list have equal top and bottom offset (aligned vertically in center)
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator equalTopBottomOffset() {
        validateEqualTopBottomOffset(rootElements);
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
        validateInsideOfContainer(asElement(containerElement), readableContainerName);
        return this;
    }
}