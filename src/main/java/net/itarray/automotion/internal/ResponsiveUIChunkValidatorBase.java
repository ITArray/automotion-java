package net.itarray.automotion.internal;

import net.itarray.automotion.internal.geometry.ConnectedIntervals;
import net.itarray.automotion.internal.geometry.Interval;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.ChunkUIElementValidator;
import net.itarray.automotion.validation.UISnapshot;
import net.itarray.automotion.validation.Units;
import org.json.simple.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIValidator;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static net.itarray.automotion.internal.UIElement.*;
import static net.itarray.automotion.internal.geometry.Interval.interval;

public class ResponsiveUIChunkValidatorBase extends ResponsiveUIValidatorBase implements ChunkUIElementValidator {

    private final List<UIElement> rootElements;
    //private final OffsetLineCommands offsetLineCommands = new OffsetLineCommands();

    public ResponsiveUIChunkValidatorBase(UISnapshot snapshot, List<WebElement> webElements, boolean allowEmpty) {
        super(snapshot);
        if (!allowEmpty && webElements.isEmpty()) {
            String message = "Set root web element";
            addError(message);
        } else {
            if (!getDriver().isAppiumContext()) {
                try {
                    //((JavascriptExecutor) getDriver().getDriver()).executeScript("arguments[0].scrollIntoView();", webElements.get(0));
                    //((JavascriptExecutor) getDriver().getDriver()).executeScript("javascript:window.scrollBy(0,250);");
                    ((JavascriptExecutor) getDriver().getDriver()).executeScript("document.documentElement.style.overflow = 'hidden'");
                } catch (Exception e) {}
            }
        }
        rootElements = asElements(webElements);
        doSnapshot();
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

    @Override
    public ChunkUIElementValidator areAlignedAsGridCells() {
        validateAlignedAsGridCells(rootElements);
        return this;
    }

    public void validateAlignedAsGridCells(List<UIElement> rootElements) {
        ConnectedIntervals columns = new ConnectedIntervals(rootElements.stream().map(e -> e.getXInterval()).collect(Collectors.toList()));
        ConnectedIntervals rows = new ConnectedIntervals(rootElements.stream().map(e -> e.getYInterval()).collect(Collectors.toList()));
        for (UIElement element : rootElements) {
            Interval xInterval = element.getXInterval();
            Interval xCell = columns.get(columns.indexOf(xInterval));
            Interval yInterval = element.getYInterval();
            Interval yCell = rows.get(rows.indexOf(yInterval));
            if (!(xInterval.equals(xCell) && yInterval.equals(yCell))) {
                errors.add(String.format("banane"));
            }
        }
    }

    /**
     * Verify that every element in the list is not overlapped with another element from this list
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase doNotOverlap() {
        validateElementsAreNotOverlapped(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have the same size
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase haveEqualSize() {
        validateSameSize(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have the same width
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase haveEqualWidth() {
        validateSameWidth(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have the same height
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase haveEqualHeight() {
        validateSameHeight(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have not the same size
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase haveDifferentSizes() {
        validateHaveDifferentSizes(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have not the same width
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase haveDifferentWidths() {
        validateHaveDifferentWidths(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have not the same height
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase haveDifferentHeights() {
        validateNotSameHeight(rootElements);
        return this;
    }

    /**
     * Verify that elements in the list have the right offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase areRightAligned() {
        validateRightAlignedWithChunk(asNumberedList(rootElements));
        //offsetLineCommands.drawRightOffsetLine();
        return this;
    }

    /**
     * Verify that elements in the list have the same left offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase areLeftAligned() {
        validateLeftAlignedWithChunk(asNumberedList(rootElements));
        //offsetLineCommands.drawLeftOffsetLine();
        return this;
    }

    /**
     * Verify that elements in the list have the same top offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase areTopAligned() {
        validateTopAlignedWithChunk(asNumberedList(rootElements));
        //offsetLineCommands.drawTopOffsetLine();
        return this;
    }

    /**
     * Verify that elements in the list have the same bottom offset
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase areBottomAligned() {
        validateBottomAlignedWithChunk(asNumberedList(rootElements));
        //offsetLineCommands.drawBottomOffsetLine();
        return this;
    }

    /**
     * Verify that every element in the list have equal right and left offset (aligned horizontally in center)
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase areCenteredOnPageVertically() {
        validateCenteredOnPageVertically(rootElements);
        return this;
    }

    /**
     * Verify that every element in the list have equal top and bottom offset (aligned vertically in center)
     *
     * @return ResponsiveUIChunkValidator
     */
    @Override
    public ResponsiveUIChunkValidatorBase areCenteredOnPageHorizontally() {
        validateCenteredOnPageHorizontally(rootElements);
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
    public ResponsiveUIChunkValidatorBase areInsideOf(WebElement containerElement, String readableContainerName) {
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
        SortedMap<Scalar, Integer> map = new TreeMap<>();
        for (UIElement element : elements) {
            int oldCount = map.getOrDefault(element.getY(), 0);
            map.put(element.getY(), oldCount + 1);
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
            for (Map.Entry<Scalar, Integer> entry : map.entrySet()) {
                if (rowCount <= mapSize) {
                    int actualInARow = entry.getValue();
                    if (actualInARow != columns) {
                        errorLastLine++;
                        if (errorLastLine > 1 || actualInARow > columns) {
                            addError(String.format("Elements in a grid are not aligned properly in row #%d. Expected %d elements in a row. Actually it's %d", rowCount, columns, actualInARow));
                        }
                    }
                    rowCount++;
                }
            }
        }
    }

    private void validateRightAlignedWithChunk(List<UIElement> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            UIElement element = elements.get(i);
            UIElement elementToCompare = elements.get(i + 1);
            element.validateRightAlignedWith(elementToCompare, errors);
        }
    }

    private void validateLeftAlignedWithChunk(List<UIElement> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            UIElement element = elements.get(i);
            UIElement elementToCompare = elements.get(i + 1);
            element.validateLeftAlignedWith(elementToCompare, errors);
        }
    }

    private void validateTopAlignedWithChunk(List<UIElement> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            UIElement element = elements.get(i);
            UIElement elementToCompare = elements.get(i + 1);
            element.validateTopAlignedWith(elementToCompare, errors);
        }
    }

    private void validateBottomAlignedWithChunk(List<UIElement> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            UIElement element = elements.get(i);
            UIElement elementToCompare = elements.get(i + 1);
            element.validateBottomAlignedWith(elementToCompare, this.errors);
        }
    }

    private void validateSameWidth(List<UIElement> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            UIElement element = elements.get(i);
            UIElement elementToCompare = elements.get(i + 1);
            if (!element.hasSameWidthAs(elementToCompare)) {
                errors.add(String.format("Element #%d has different width. Element width is: [%s, %s]", (i + 1), element.getWidth(), element.getHeight()), element);
                errors.add(String.format("Element #%d has different width. Element width is: [%s, %s]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }
        }
    }

    private void validateSameHeight(List<UIElement> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            UIElement element = elements.get(i);
            UIElement elementToCompare = elements.get(i + 1);
            if (!element.hasSameHeightAs(elementToCompare)) {
                errors.add(String.format("Element #%d has different height. Element height is: [%s, %s]", (i + 1), element.getWidth(), element.getHeight()), element);
                errors.add(String.format("Element #%d has different height. Element height is: [%s, %s]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }
        }
    }

    private void validateSameSize(List<UIElement> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            UIElement element = elements.get(i);
            UIElement elementToCompare = elements.get(i + 1);
            if (!element.hasSameSizeAs(elementToCompare)) {
                errors.add(String.format("Element #%d has different size. Element size is: [%s, %s]", (i + 1), element.getWidth(), element.getHeight()), element);
                errors.add(String.format("Element #%d has different size. Element size is: [%s, %s]", (i + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
            }

        }
    }

    private void validateHaveDifferentSizes(List<UIElement> elements) {
        for (int firstIndex = 0; firstIndex < elements.size(); firstIndex++) {
            UIElement element = elements.get(firstIndex);
            for (int secondIndex = firstIndex+1; secondIndex < elements.size(); secondIndex++) {
                UIElement elementToCompare = elements.get(secondIndex);
                if (element.hasSameSizeAs(elementToCompare)) {
                    errors.add(String.format("Element #%d has same size. Element size is: [%s, %s]", (firstIndex + 1), element.getWidth(), element.getHeight()), element);
                    errors.add(String.format("Element #%d has same size. Element size is: [%s, %s]", (secondIndex + 1), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
                }
            }
        }
    }

    private void validateHaveDifferentWidths(List<UIElement> elements) {
        for (int firstIndex = 0; firstIndex < elements.size(); firstIndex++) {
            UIElement element = elements.get(firstIndex);
            for (int secondIndex = firstIndex+1; secondIndex < elements.size(); secondIndex++) {
                UIElement elementToCompare = elements.get(secondIndex);
                if (element.hasSameWidthAs(elementToCompare)) {
                    errors.add(String.format("Element #%d has same width. Element width is: [%s, %s]", (firstIndex + 1), element.getWidth(), element.getHeight()), element);
                    errors.add(String.format("Element #%d has same width. Element width is: [%s, %s]", (secondIndex + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
                }
            }
        }
    }

    private void validateNotSameHeight(List<UIElement> elements) {
        for (int firstIndex = 0; firstIndex < elements.size(); firstIndex++) {
            UIElement element = elements.get(firstIndex);
            for (int secondIndex = firstIndex+1; secondIndex < elements.size(); secondIndex++) {
                UIElement elementToCompare = elements.get(secondIndex);
                if (element.hasSameHeightAs(elementToCompare)) {
                    errors.add(String.format("Element #%d has same height. Element height is: [%s, %s]", (firstIndex + 1), element.getWidth(), element.getHeight()), element);
                    errors.add(String.format("Element #%d has same height. Element height is: [%s, %s]", (secondIndex + 2), elementToCompare.getWidth(), elementToCompare.getHeight()), elementToCompare);
                }
            }
        }
    }

    private void validateCenteredOnPageVertically(List<UIElement> elements) {
        for (UIElement element : elements) {
            element.validateCenteredOnVertically(page, errors);
        }
    }

    private void validateCenteredOnPageHorizontally(List<UIElement> elements) {
        for (UIElement element : elements) {
            element.validateCenteredOnHorizontally(page, errors);
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