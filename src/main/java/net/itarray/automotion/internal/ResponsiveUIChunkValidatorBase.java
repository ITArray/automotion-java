package net.itarray.automotion.internal;

import net.itarray.automotion.validation.ChunkValidator;
import net.itarray.automotion.validation.Scene;
import net.itarray.automotion.validation.Units;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIValidator;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

import static net.itarray.automotion.internal.Element.*;

public class ResponsiveUIChunkValidatorBase extends ResponsiveUIValidatorBase implements ChunkValidator {

    private final List<Element> rootElements;

    public ResponsiveUIChunkValidatorBase(Scene scene, List<WebElement> webElements) {
        super(scene);
        rootElements = asElements(webElements);
        if (webElements.isEmpty()) {
            String message = "Set root web element";
            addError(message);
        }
    }

    @Override
    public ResponsiveUIChunkValidatorBase drawMap() {
        super.drawMap();
        return this;
    }

    @Override
    public ResponsiveUIChunkValidatorBase dontDrawMap() {
        super.dontDrawMap();
        return this;
    }

    /**
     * Change units to Pixels or % (Units.PX, Units.PERCENT)
     *
     * @param units
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase changeMetricsUnitsTo(ResponsiveUIValidator.Units units) {
        return changeMetricsUnitsTo(units.asNewUnits());
    }

    @Override
    public ResponsiveUIChunkValidatorBase changeMetricsUnitsTo(Units units) {
        getReport().changeMetricsUnitsTo(units);
        return this;
    }

    /**
     * Verify that elements are aligned in a grid view width specified amount of columns
     *
     * @param horizontalGridSize
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase alignedAsGrid(int horizontalGridSize) {
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
    public ResponsiveUIChunkValidatorBase alignedAsGrid(int horizontalGridSize, int verticalGridSize) {
        validateGridAlignment(rootElements, horizontalGridSize, verticalGridSize);
        return this;
    }

    /**
     * Verify that every element in the list is not overlapped with another element from this list
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase areNotOverlappedWithEachOther() {
        validateElementsAreNotOverlapped(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have the same size
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase withSameSize() {
        validateSameSize(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have the same width
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase withSameWidth() {
        validateSameWidth(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have the same height
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase withSameHeight() {
        validateSameHeight(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have not the same size
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase withNotSameSize() {
        validateNotSameSize(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have not the same width
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase withNotSameWidth() {
        validateNotSameWidth(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have not the same height
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase withNotSameHeight() {
        validateNotSameHeight(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have the right offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase sameRightOffset() {
        validateRightOffsetForChunk(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have the same left offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase sameLeftOffset() {
        validateLeftOffsetForChunk(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have the same top offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase sameTopOffset() {
        validateTopOffsetForChunk(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have the same bottom offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase sameBottomOffset() {
        validateBottomOffsetForChunk(rootElements);
        return this;
    }

    /**
     * Verify that every element in the list have equal right and left offset (aligned horizontally in center)
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase equalLeftRightOffset() {
        validateEqualLeftRightOffset(rootElements);
        return this;
    }

    /**
     * Verify that every element in the list have equal top and bottom offset (aligned vertically in center)
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase equalTopBottomOffset() {
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
    public ResponsiveUIChunkValidatorBase insideOf(WebElement containerElement, String readableContainerName) {
        validateInsideOfContainer(asElement(containerElement), readableContainerName, rootElements);
        return this;
    }

    private void validateElementsAreNotOverlapped(List<Element> elements) {
        for (int firstIndex = 0; firstIndex < elements.size(); firstIndex++) {
            Element first = elements.get(firstIndex);
            for (int secondIndex = firstIndex+1; secondIndex < elements.size(); secondIndex++) {
                Element second = elements.get(secondIndex);
                if (first.overlaps(second)) {
                    addError("Elements are overlapped", first);
                    break;
                }
            }
        }
    }

    private void validateGridAlignment(List<Element> elements, int columns, int rows) {
        ConcurrentSkipListMap<Integer, AtomicLong> map = new ConcurrentSkipListMap<>();
        for (Element element : elements) {
            Integer y = element.getY();

            map.putIfAbsent(y, new AtomicLong(0));
            map.get(y).incrementAndGet();
        }

        int mapSize = map.size();
        if (rows > 0) {
            if (mapSize != rows) {
                addError(String.format("Elements in a grid are not aligned properly. Looks like grid has wrong amount of rows. Expected is %d. Actual is %d", rows, mapSize));
            }
        }

        if (columns > 0) {
            int errorLastLine = 0;
            int rowCount = 1;
            for (Map.Entry<Integer, AtomicLong> entry : map.entrySet()) {
                if (rowCount <= mapSize) {
                    int actualInARow = entry.getValue().intValue();
                    if (actualInARow != columns) {
                        errorLastLine++;
                        if (errorLastLine > 1) {
                            addError(String.format("Elements in a grid are not aligned properly in row #%d. Expected %d elements in a row. Actually it's %d", rowCount, columns, actualInARow));
                        }
                    }
                    rowCount++;
                }
            }
        }
    }

    private void validateRightOffsetForChunk(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (!element.hasEqualRightOffsetAs(elementToCompare)) {
                addError(String.format("Element #%d has not the same right offset as element #%d", i + 1, i + 2), elementToCompare);
            }
        }
    }

    private void validateLeftOffsetForChunk(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (!element.hasEqualLeftOffsetAs(elementToCompare)) {
                addError(String.format("Element #%d has not the same left offset as element #%d", i + 1, i + 2), elementToCompare);
            }
        }
    }

    private void validateTopOffsetForChunk(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (!element.hasEqualTopOffsetAs(elementToCompare)) {
                addError(String.format("Element #%d has not the same top offset as element #%d", i + 1, i + 2), elementToCompare);
            }
        }
    }

    private void validateBottomOffsetForChunk(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (!element.hasEqualBottomOffsetAs(elementToCompare)) {
                addError(String.format("Element #%d has not the same bottom offset as element #%d", i + 1, i + 2), elementToCompare);
            }
        }
    }

    private void validateSameWidth(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (!element.hasSameWidthAs(elementToCompare)) {
                addError(String.format("Element #%d has different width. Element width is: [%d, %d]", (i + 1), element.getWidth(), element.getHeight()), element);
                addError(String.format("Element #%d has different width. Element width is: [%d, %d]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }
        }
    }

    private void validateSameHeight(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (!element.hasSameHeightAs(elementToCompare)) {
                addError(String.format("Element #%d has different height. Element height is: [%d, %d]", (i + 1), element.getWidth(), element.getHeight()), element);
                addError(String.format("Element #%d has different height. Element height is: [%d, %d]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }
        }
    }

    private void validateSameSize(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (!element.hasSameSizeAs(elementToCompare)) {
                addError(String.format("Element #%d has different size. Element size is: [%d, %d]", (i + 1), element.getWidth(), element.getHeight()), element);
                addError(String.format("Element #%d has different size. Element size is: [%d, %d]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }

        }
    }

    private void validateNotSameSize(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (element.hasSameSizeAs(elementToCompare)) {
                addError(String.format("Element #%d has same size. Element size is: [%d, %d]", (i + 1), element.getWidth(), element.getHeight()), element);
                addError(String.format("Element #%d has same size. Element size is: [%d, %d]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }

        }
    }

    private void validateNotSameWidth(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (element.hasSameWidthAs(elementToCompare)) {
                addError(String.format("Element #%d has same width. Element width is: [%d, %d]", (i + 1), element.getWidth(), element.getHeight()), element);
                addError(String.format("Element #%d has same width. Element width is: [%d, %d]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }

        }
    }

    private void validateNotSameHeight(List<Element> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            Element element = elements.get(i);
            Element elementToCompare = elements.get(i + 1);
            if (element.hasSameHeightAs(elementToCompare)) {
                addError(String.format("Element #%d has same height. Element height is: [%d, %d]", (i + 1), element.getWidth(), element.getHeight()), element);
                addError(String.format("Element #%d has same height. Element height is: [%d, %d]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }
        }
    }

    private void validateEqualLeftRightOffset(List<Element> elements) {
        for (Element element : elements) {
            if (!element.hasEqualLeftRightOffset(pageSize)) {
                addError(String.format("Element '%s' has not equal left and right offset. Left offset is %dpx, right is %dpx", element.getFormattedMessage(), element.getX(), element.getRightOffset(pageSize)), element);
            }
        }
    }

    private void validateEqualTopBottomOffset(List<Element> elements) {
        for (Element element : elements) {
            if (!element.hasEqualTopBottomOffset(pageSize)) {
                addError(String.format("Element '%s' has not equal top and bottom offset. Top offset is %dpx, bottom is %dpx", element.getFormattedMessage(), element.getY(), element.getBottomOffset(pageSize)), element);
            }
        }
    }

    private void validateInsideOfContainer(Element containerElement, String readableContainerName, List<Element> elements) {
        Rectangle2D.Double elementRectangle = containerElement.rectangle();
        for (Element element : elements) {
            if (!elementRectangle.contains(element.rectangle())) {
                addError(String.format("Element is not inside of '%s'", readableContainerName), containerElement);
            }
        }
    }

    @Override
    protected String getRootElementReadableName() {
        return "Root Element";
    }

    @Override
    protected void storeRootDetails(JSONObject rootDetails) {
    }

    @Override
    protected void drawRootElement(DrawableScreenshot screenshot) {
        if (!rootElements.isEmpty()) {
            screenshot.drawRootElement(rootElements.get(0));
        }
    }

    @Override
    protected void drawOffsets(DrawableScreenshot screenshot) {
    }



}