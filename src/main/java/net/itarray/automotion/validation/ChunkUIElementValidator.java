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

    // ? filled needs to be expressed somehow
    // areAlignedInColumns(numberOfColumns)
    ChunkUIElementValidator alignedAsGrid(int horizontalGridSize);

    // areAlignedInColumnsAndRows(numberOfColumns)
    ChunkUIElementValidator alignedAsGrid(int horizontalGridSize, int verticalGridSize);

    // doNotOverlap
    ChunkUIElementValidator areNotOverlappedWithEachOther();

    // haveEqualSize
    ChunkUIElementValidator withSameSize();

    // haveEqualWidth
    ChunkUIElementValidator withSameWidth();

    // haveEqualHeight
    ChunkUIElementValidator withSameHeight();

    // really needed? -> keep
    // all have different size
    // we need to fix bug
    // haveDifferentSizes
    ChunkUIElementValidator withNotSameSize();

    ChunkUIElementValidator withNotSameWidth();

    ChunkUIElementValidator withNotSameHeight();

    // areVerticallyRightAligned
    ChunkUIElementValidator sameRightOffset();

    // areVerticallyLeftAligned
    ChunkUIElementValidator sameLeftOffset();

    // areHorizontallyTopAligned
    ChunkUIElementValidator sameTopOffset();

    // areHorizontallyBottomAligned
    ChunkUIElementValidator sameBottomOffset();

    // areVerticallyCenteredOnPage
    ChunkUIElementValidator equalLeftRightOffset();

    // areHorizontallyCenteredOnPage
    ChunkUIElementValidator equalTopBottomOffset();

    // areInsideOf
    ChunkUIElementValidator insideOf(WebElement containerElement, String readableContainerName);

    // equal distribution (horizontal, vertically, both)

}
