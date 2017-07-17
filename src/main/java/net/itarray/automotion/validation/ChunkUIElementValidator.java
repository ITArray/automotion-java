package net.itarray.automotion.validation;

import org.openqa.selenium.WebElement;

public interface ChunkUIElementValidator {

    /**
     * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.validation.ResponsiveUIValidator#drawMap()}
     */
    @Deprecated
    ChunkUIElementValidator drawMap();

    /**
     * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.validation.ResponsiveUIValidator#dontDrawMap()}
     */
    @Deprecated
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

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#areRightAligned()}
     */
    @Deprecated
    default ChunkUIElementValidator sameRightOffset() { return areRightAligned(); }
    ChunkUIElementValidator areRightAligned();

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#areLeftAligned()}
     */
    @Deprecated
    default ChunkUIElementValidator sameLeftOffset() { return areLeftAligned(); }
    ChunkUIElementValidator areLeftAligned();

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#areTopAligned()}
     */
    @Deprecated
    default ChunkUIElementValidator sameTopOffset() { return areTopAligned(); }
    ChunkUIElementValidator areTopAligned();

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#areBottomAligned()}
     */
    @Deprecated
    default ChunkUIElementValidator sameBottomOffset() { return areBottomAligned(); }
    ChunkUIElementValidator areBottomAligned();

    // areVerticallyCenteredOnPage
    ChunkUIElementValidator equalLeftRightOffset();

    // areHorizontallyCenteredOnPage
    ChunkUIElementValidator equalTopBottomOffset();

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#areInsideOf(org.openqa.selenium.WebElement, String)}
     */
    @Deprecated
    default ChunkUIElementValidator insideOf(WebElement containerElement, String readableContainerName) { return areInsideOf(containerElement, readableContainerName); }
    ChunkUIElementValidator areInsideOf(WebElement containerElement, String readableContainerName);

    // equal distribution (horizontal, vertically, both)
    // alignments other the centered don't need horizontally/vertically - discuss

}
