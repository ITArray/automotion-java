package net.itarray.automotion.internal;

import net.itarray.automotion.validation.ChunkUIElementValidator;
import net.itarray.automotion.validation.UISnapshot;
import net.itarray.automotion.validation.Units;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIValidator;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

import static net.itarray.automotion.internal.UIElement.*;

public class ResponsiveUIChunkValidatorBase extends ResponsiveUIValidatorBase implements ChunkUIElementValidator {

    private final List<UIElement> rootElements;

    public ResponsiveUIChunkValidatorBase(UISnapshot snapshot, List<WebElement> webElements) {
        super(snapshot);
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
        validateRightOffsetForChunk(asNumberedList(rootElements));
        return this;
    }

    /**
     * Verify that elements in the list have the same left offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase sameLeftOffset() {
        validateLeftOffsetForChunk(asNumberedList(rootElements));
        return this;
    }

    /**
     * Verify that elements in the list have the same top offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase sameTopOffset() {
        validateTopOffsetForChunk(asNumberedList(rootElements));
        return this;
    }

    /**
     * Verify that elements in the list have the same bottom offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase sameBottomOffset() {
        validateBottomOffsetForChunk(asNumberedList(rootElements));
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
        validateInsideOfContainer(asElement(containerElement, readableContainerName), rootElements);
        return this;
    }

    private void validateElementsAreNotOverlapped(List<UIElement> elements) {
        for (int firstIndex = 0; firstIndex < elements.size(); firstIndex++) {
            UIElement first = elements.get(firstIndex);
            for (int secondIndex = firstIndex+1; secondIndex < elements.size(); secondIndex++) {
                UIElement second = elements.get(secondIndex);
                if (first.overlaps(second)) {
                    errors.add("Elements are overlapped", first);
                    break;
                }
            }
        }
    }

    private void validateGridAlignment(List<UIElement> elements, int columns, int rows) {
        ConcurrentSkipListMap<Integer, AtomicLong> map = new ConcurrentSkipListMap<>();
        for (UIElement element : elements) {
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

    private void validateRightOffsetForChunk(List<UIElement> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            UIElement element = elements.get(i);
            UIElement elementToCompare = elements.get(i + 1);
            element.validateEqualRight(elementToCompare, errors);
        }
    }

    private void validateLeftOffsetForChunk(List<UIElement> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            UIElement element = elements.get(i);
            UIElement elementToCompare = elements.get(i + 1);
            element.validateEqualLeft(elementToCompare, errors);
        }
    }

    private void validateTopOffsetForChunk(List<UIElement> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            UIElement element = elements.get(i);
            UIElement elementToCompare = elements.get(i + 1);
            element.validateEqualTop(elementToCompare, errors);
        }
    }

    private void validateBottomOffsetForChunk(List<UIElement> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            UIElement element = elements.get(i);
            UIElement elementToCompare = elements.get(i + 1);
            element.validateEqualBottom(elementToCompare, this.errors);
        }
    }

    private void validateSameWidth(List<UIElement> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            UIElement element = elements.get(i);
            UIElement elementToCompare = elements.get(i + 1);
            if (!element.hasSameWidthAs(elementToCompare)) {
                errors.add(String.format("Element #%d has different width. Element width is: [%d, %d]", (i + 1), element.getWidth(), element.getHeight()), element);
                errors.add(String.format("Element #%d has different width. Element width is: [%d, %d]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }
        }
    }

    private void validateSameHeight(List<UIElement> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            UIElement element = elements.get(i);
            UIElement elementToCompare = elements.get(i + 1);
            if (!element.hasSameHeightAs(elementToCompare)) {
                errors.add(String.format("Element #%d has different height. Element height is: [%d, %d]", (i + 1), element.getWidth(), element.getHeight()), element);
                errors.add(String.format("Element #%d has different height. Element height is: [%d, %d]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }
        }
    }

    private void validateSameSize(List<UIElement> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            UIElement element = elements.get(i);
            UIElement elementToCompare = elements.get(i + 1);
            if (!element.hasSameSizeAs(elementToCompare)) {
                errors.add(String.format("Element #%d has different size. Element size is: [%d, %d]", (i + 1), element.getWidth(), element.getHeight()), element);
                errors.add(String.format("Element #%d has different size. Element size is: [%d, %d]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }

        }
    }

    private void validateNotSameSize(List<UIElement> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            UIElement element = elements.get(i);
            UIElement elementToCompare = elements.get(i + 1);
            if (element.hasSameSizeAs(elementToCompare)) {
                errors.add(String.format("Element #%d has same size. Element size is: [%d, %d]", (i + 1), element.getWidth(), element.getHeight()), element);
                errors.add(String.format("Element #%d has same size. Element size is: [%d, %d]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }

        }
    }

    private void validateNotSameWidth(List<UIElement> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            UIElement element = elements.get(i);
            UIElement elementToCompare = elements.get(i + 1);
            if (element.hasSameWidthAs(elementToCompare)) {
                errors.add(String.format("Element #%d has same width. Element width is: [%d, %d]", (i + 1), element.getWidth(), element.getHeight()), element);
                errors.add(String.format("Element #%d has same width. Element width is: [%d, %d]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }

        }
    }

    private void validateNotSameHeight(List<UIElement> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            UIElement element = elements.get(i);
            UIElement elementToCompare = elements.get(i + 1);
            if (element.hasSameHeightAs(elementToCompare)) {
                errors.add(String.format("Element #%d has same height. Element height is: [%d, %d]", (i + 1), element.getWidth(), element.getHeight()), element);
                errors.add(String.format("Element #%d has same height. Element height is: [%d, %d]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }
        }
    }

    private void validateEqualLeftRightOffset(List<UIElement> elements) {
        for (UIElement element : elements) { // todo make naming consistant with other methods
            element.validateEqualLeftRightOffset(page, errors);
        }
    }

    private void validateEqualTopBottomOffset(List<UIElement> elements) {
        for (UIElement element : elements) { // todo make naming consistant with other methods
            element.validateEqualTopBottomOffset(page, errors);
        }
    }

    private void validateInsideOfContainer(UIElement containerElement, List<UIElement> elements) {
        for (UIElement element : elements) {
            element.validateInsideOfContainer(containerElement, errors);
        }
    }

    @Override
    protected String getNameOfToBeValidated() {
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