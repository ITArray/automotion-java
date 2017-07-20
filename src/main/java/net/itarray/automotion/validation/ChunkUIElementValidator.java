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
     * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.validation.properties.Expression#percent(int, net.itarray.automotion.validation.properties.PercentReference)}
     */
    @Deprecated
    ChunkUIElementValidator changeMetricsUnitsTo(util.validator.ResponsiveUIValidator.Units units);

    /**
     * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.validation.properties.Expression#percent(int, net.itarray.automotion.validation.properties.PercentReference)}
     */
    @Deprecated
    ChunkUIElementValidator changeMetricsUnitsTo(Units units);

    // ? filled needs to be expressed somehow
    // areAlignedInColumns(numberOfColumns)
    ChunkUIElementValidator alignedAsGrid(int horizontalGridSize);

    // areAlignedInColumnsAndRows(numberOfColumns)
    ChunkUIElementValidator alignedAsGrid(int horizontalGridSize, int verticalGridSize);

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#doNotOverlap()}
     */
    @Deprecated
    default ChunkUIElementValidator areNotOverlappedWithEachOther() { return doNotOverlap(); }
    ChunkUIElementValidator doNotOverlap();


    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#haveEqualSize()}
     */
    @Deprecated
    default ChunkUIElementValidator withSameSize() { return haveEqualSize(); }
    ChunkUIElementValidator haveEqualSize();

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#haveEqualWidth()}
     */
    @Deprecated
    default ChunkUIElementValidator withSameWidth() { return haveEqualWidth(); }
    ChunkUIElementValidator haveEqualWidth();

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#haveEqualHeight()}
     */
    @Deprecated
    default ChunkUIElementValidator withSameHeight() { return haveEqualHeight(); }
    ChunkUIElementValidator haveEqualHeight();

    // WE NEED TO FIX BUGS for the next 3 methods

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#haveDifferentSizes()}
     */
    @Deprecated
    default ChunkUIElementValidator withNotSameSize() { return haveDifferentSizes(); }
    ChunkUIElementValidator haveDifferentSizes();

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#haveDifferentWidths()}
     */
    @Deprecated
    default ChunkUIElementValidator withNotSameWidth() { return haveDifferentWidths(); }
    ChunkUIElementValidator haveDifferentWidths();

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#haveDifferentHeights()}
     */
    @Deprecated
    default ChunkUIElementValidator withNotSameHeight() { return haveDifferentHeights(); }
    ChunkUIElementValidator haveDifferentHeights();

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


    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#areCenteredOnPageVertically()}
     */
    @Deprecated
    default ChunkUIElementValidator equalLeftRightOffset() { return areCenteredOnPageVertically(); }
    ChunkUIElementValidator areCenteredOnPageVertically();

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#areCenteredOnPageHorizontallyNew()}
     */
    @Deprecated
    default ChunkUIElementValidator equalTopBottomOffset() { return areCenteredOnPageHorizontallyNew(); }
    ChunkUIElementValidator areCenteredOnPageHorizontallyNew();

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#areInsideOf(org.openqa.selenium.WebElement, String)}
     */
    @Deprecated
    default ChunkUIElementValidator insideOf(WebElement containerElement, String readableContainerName) { return areInsideOf(containerElement, readableContainerName); }
    ChunkUIElementValidator areInsideOf(WebElement containerElement, String readableContainerName);

    // equal distribution (horizontal, vertically, both)
    // alignments other the centered don't need horizontally/vertically - discuss

}
