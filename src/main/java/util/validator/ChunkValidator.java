package util.validator;

import net.itarray.automotion.ChunkValidatorBase;
import org.openqa.selenium.WebElement;

public interface ChunkValidator extends ChunkValidatorBase {

    ResponsiveUIChunkValidator changeMetricsUnitsTo(ResponsiveUIValidator.Units units);

    ResponsiveUIChunkValidator alignedAsGrid(int horizontalGridSize);

    ResponsiveUIChunkValidator alignedAsGrid(int horizontalGridSize, int verticalGridSize);

    ResponsiveUIChunkValidator areNotOverlappedWithEachOther();

    ResponsiveUIChunkValidator withSameSize();

    ResponsiveUIChunkValidator withSameWidth();

    ResponsiveUIChunkValidator withSameHeight();

    ResponsiveUIChunkValidator withNotSameSize();

    ResponsiveUIChunkValidator withNotSameWidth();

    ResponsiveUIChunkValidator withNotSameHeight();

    ResponsiveUIChunkValidator sameRightOffset();

    ResponsiveUIChunkValidator sameLeftOffset();

    ResponsiveUIChunkValidator sameTopOffset();

    ResponsiveUIChunkValidator sameBottomOffset();

    ResponsiveUIChunkValidator equalLeftRightOffset();

    ResponsiveUIChunkValidator equalTopBottomOffset();

    ResponsiveUIChunkValidator insideOf(WebElement containerElement, String readableContainerName);

}
