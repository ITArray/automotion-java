package util.validator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResponsiveUIChunkValidator extends ResponsiveUIValidator implements ChunkValidator {

    ResponsiveUIChunkValidator(WebDriver driver, List<WebElement> elements) {
        super(driver);
        rootElements = elements;
        pageWidth = driver.manage().window().getSize().getWidth();
        pageHeight = driver.manage().window().getSize().getHeight();
        rootElement = rootElements.get(0);
        startTime = System.currentTimeMillis();
    }

    @Override
    public ResponsiveUIChunkValidator changeMetricsUnitsTo(Units units) {
        this.units = units;
        return this;
    }

    @Override
    public ResponsiveUIChunkValidator alignedAsGrid(int horizontalGridSize) {
        validateGridAlignment(horizontalGridSize, 0);
        return this;
    }

    @Override
    public ResponsiveUIChunkValidator alignedAsGrid(int horizontalGridSize, int verticalGridSize) {
        validateGridAlignment(horizontalGridSize, verticalGridSize);
        return this;
    }

    @Override
    public ResponsiveUIChunkValidator areNotOverlappedWithEachOther() {
        validateElementsAreNotOverlapped(rootElements);
        return this;
    }

    @Override
    public ResponsiveUIChunkValidator withSameSize() {
        validateSameSize(rootElements, 0);
        return this;
    }

    @Override
    public ResponsiveUIChunkValidator withSameWidth() {
        validateSameSize(rootElements, 1);
        return this;
    }

    @Override
    public ResponsiveUIChunkValidator withSameHeight() {
        validateSameSize(rootElements, 2);
        return this;
    }

    @Override
    public ResponsiveUIChunkValidator sameRightOffset() {
        validateRightOffsetForChunk(rootElements);
        return this;
    }

    @Override
    public ResponsiveUIChunkValidator sameLeftOffset() {
        validateLeftOffsetForChunk(rootElements);
        return this;
    }

    @Override
    public ResponsiveUIChunkValidator sameTopOffset() {
        validateTopOffsetForChunk(rootElements);
        return this;
    }

    @Override
    public ResponsiveUIChunkValidator sameBottomOffset() {
        validateBottomOffsetForChunk(rootElements);
        return this;
    }
}
