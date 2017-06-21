package net.itarray.automotion;

import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIChunkValidator;
import util.validator.ResponsiveUIValidator;

public interface ChunkValidatorBase {

    ChunkValidatorBase changeMetricsUnitsTo(ResponsiveUIValidator.Units units);

    ChunkValidatorBase alignedAsGrid(int horizontalGridSize);

    ChunkValidatorBase alignedAsGrid(int horizontalGridSize, int verticalGridSize);

    ChunkValidatorBase areNotOverlappedWithEachOther();

    ChunkValidatorBase withSameSize();

    ChunkValidatorBase withSameWidth();

    ChunkValidatorBase withSameHeight();

    ChunkValidatorBase withNotSameSize();

    ChunkValidatorBase withNotSameWidth();

    ChunkValidatorBase withNotSameHeight();

    ChunkValidatorBase sameRightOffset();

    ChunkValidatorBase sameLeftOffset();

    ChunkValidatorBase sameTopOffset();

    ChunkValidatorBase sameBottomOffset();

    ChunkValidatorBase equalLeftRightOffset();

    ChunkValidatorBase equalTopBottomOffset();

    ChunkValidatorBase insideOf(WebElement containerElement, String readableContainerName);

}
