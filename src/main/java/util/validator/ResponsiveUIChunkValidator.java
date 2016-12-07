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
        validateSameSize(rootElements);
        return this;
    }
}
