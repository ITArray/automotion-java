package net.itarray.automotion.validation;

import org.openqa.selenium.WebElement;

public interface ChunkUIElementValidator {

    ChunkUIElementValidator drawMap();

    ChunkUIElementValidator dontDrawMap();

    boolean validate();

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#changeMetricsUnitsTo(Units)}
     */
    @Deprecated
    ChunkUIElementValidator changeMetricsUnitsTo(util.validator.ResponsiveUIValidator.Units units);

    ChunkUIElementValidator changeMetricsUnitsTo(Units units);

    ChunkUIElementValidator alignedAsGrid(int horizontalGridSize);

    ChunkUIElementValidator alignedAsGrid(int horizontalGridSize, int verticalGridSize);

    ChunkUIElementValidator areNotOverlappedWithEachOther();

    ChunkUIElementValidator withSameSize();

    ChunkUIElementValidator withSameWidth();

    ChunkUIElementValidator withSameHeight();

    ChunkUIElementValidator withNotSameSize();

    ChunkUIElementValidator withNotSameWidth();

    ChunkUIElementValidator withNotSameHeight();

    ChunkUIElementValidator sameRightOffset();

    ChunkUIElementValidator sameLeftOffset();

    ChunkUIElementValidator sameTopOffset();

    ChunkUIElementValidator sameBottomOffset();

    ChunkUIElementValidator equalLeftRightOffset();

    ChunkUIElementValidator equalTopBottomOffset();

    ChunkUIElementValidator insideOf(WebElement containerElement, String readableContainerName);

}
