package util.validator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static net.itarry.automotion.Element.asElements;

public class ResponsiveUIChunkValidator extends ResponsiveUIValidator implements ChunkValidator {

    ResponsiveUIChunkValidator(WebDriver driver, List<WebElement> elements) {
        super(driver);
        rootElements = elements;
        setRootElement(rootElements.get(0));
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
        validateGridAlignment(asElements(rootElements), horizontalGridSize, 0);
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
        validateGridAlignment(asElements(rootElements), horizontalGridSize, verticalGridSize);
        return this;
    }

    /**
     * Verify that every element in the list is not overlapped with another element from this list
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator areNotOverlappedWithEachOther() {
        validateElementsAreNotOverlapped(asElements(rootElements));
        return this;
    }

    /**
     * Verify that elements in the list have the same size
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator withSameSize() {
        validateSameSize(asElements(rootElements));
        return this;
    }

    /**
     * Verify that elements in the list have the same width
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator withSameWidth() {
        validateSameWidth(asElements(rootElements));
        return this;
    }

    /**
     * Verify that elements in the list have the same height
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator withSameHeight() {
        validateSameHeight(asElements(rootElements));
        return this;
    }

    /**
     * Verify that elements in the list have not the same size
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator withNotSameSize() {
        validateNotSameSize(asElements(rootElements));
        return this;
    }

    /**
     * Verify that elements in the list have not the same width
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator withNotSameWidth() {
        validateNotSameWidth(asElements(rootElements));
        return this;
    }

    /**
     * Verify that elements in the list have not the same height
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator withNotSameHeight() {
        validateNotSameHeight(asElements(rootElements));
        return this;
    }

    /**
     * Verify that elements in the list have the right offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator sameRightOffset() {
        validateRightOffsetForChunk(asElements(rootElements));
        return this;
    }

    /**
     * Verify that elements in the list have the same left offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator sameLeftOffset() {
        validateLeftOffsetForChunk(asElements(rootElements));
        return this;
    }

    /**
     * Verify that elements in the list have the same top offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator sameTopOffset() {
        validateTopOffsetForChunk(asElements(rootElements));
        return this;
    }

    /**
     * Verify that elements in the list have the same bottom offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator sameBottomOffset() {
        validateBottomOffsetForChunk(asElements(rootElements));
        return this;
    }

    /**
     * Verify that every element in the list have equal right and left offset (aligned horizontally in center)
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator equalLeftRightOffset() {
        validateEqualLeftRightOffset(asElements(rootElements));
        return this;
    }

    /**
     * Verify that every element in the list have equal top and bottom offset (aligned vertically in center)
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidator equalTopBottomOffset() {
        validateEqualTopBottomOffset(asElements(rootElements));
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
        validateInsideOfContainer(containerElement, readableContainerName);
        return this;
    }
}