package util.validator;

import net.itarray.automotion.validation.ChunkUIElementValidator;
import org.openqa.selenium.WebElement;

/**
 * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.validation.ChunkUIElementValidator}
 */
@Deprecated
public interface ChunkValidator extends ChunkUIElementValidator {

    ResponsiveUIChunkValidator changeMetricsUnitsTo(ResponsiveUIValidator.Units units);

    ResponsiveUIChunkValidator changeMetricsUnitsTo(net.itarray.automotion.validation.Units units);

    ResponsiveUIChunkValidator alignedAsGrid(int horizontalGridSize);

    ResponsiveUIChunkValidator alignedAsGrid(int horizontalGridSize, int verticalGridSize);

    ResponsiveUIChunkValidator doNotOverlap();

    ResponsiveUIChunkValidator haveEqualSize();

    ResponsiveUIChunkValidator haveEqualWidth();

    ResponsiveUIChunkValidator haveEqualHeight();

    ResponsiveUIChunkValidator haveDifferentSizes();

    ResponsiveUIChunkValidator haveDifferentWidths();

    ResponsiveUIChunkValidator haveDifferentHeights();

    ResponsiveUIChunkValidator areRightAligned();

    ResponsiveUIChunkValidator areLeftAligned();

    ResponsiveUIChunkValidator areTopAligned();

    ResponsiveUIChunkValidator areBottomAligned();

    ResponsiveUIChunkValidator areCenteredOnPageVertically();

    ResponsiveUIChunkValidator areCenteredOnPageHorizontally();

    ResponsiveUIChunkValidator areInsideOf(WebElement containerElement, String readableContainerName);

}
