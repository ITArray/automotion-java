package net.itarray.automotion.validation;

import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIValidator;

public interface NewChunkValidator {

    NewChunkValidator changeMetricsUnitsTo(ResponsiveUIValidator.Units units);

    NewChunkValidator alignedAsGrid(int horizontalGridSize);

    NewChunkValidator alignedAsGrid(int horizontalGridSize, int verticalGridSize);

    NewChunkValidator areNotOverlappedWithEachOther();

    NewChunkValidator withSameSize();

    NewChunkValidator withSameWidth();

    NewChunkValidator withSameHeight();

    NewChunkValidator withNotSameSize();

    NewChunkValidator withNotSameWidth();

    NewChunkValidator withNotSameHeight();

    NewChunkValidator sameRightOffset();

    NewChunkValidator sameLeftOffset();

    NewChunkValidator sameTopOffset();

    NewChunkValidator sameBottomOffset();

    NewChunkValidator equalLeftRightOffset();

    NewChunkValidator equalTopBottomOffset();

    NewChunkValidator insideOf(WebElement containerElement, String readableContainerName);

}
