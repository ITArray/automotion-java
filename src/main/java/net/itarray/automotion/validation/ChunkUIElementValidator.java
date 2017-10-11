package net.itarray.automotion.validation;

import org.openqa.selenium.WebElement;

public interface ChunkUIElementValidator {

    boolean validate();

    // ? filled needs to be expressed somehow
    // areAlignedInColumns(numberOfColumns)
    ChunkUIElementValidator alignedAsGrid(int horizontalGridSize);

    // areAlignedInColumnsAndRows(numberOfColumns)
    ChunkUIElementValidator alignedAsGrid(int horizontalGridSize, int verticalGridSize);

    /**
     * Validate that this chunks elements are aligned in a grid of cells (not areas).
     *
     * <img src="./doc-files/sample.svg" style="display: block"></img>
     *
     * @return this
     */
    ChunkUIElementValidator areAlignedAsGridCells();

    // area

    ChunkUIElementValidator doNotOverlap();
    ChunkUIElementValidator areInsideOf(WebElement containerElement, String readableContainerName);

    // size

    ChunkUIElementValidator haveEqualSize();
    ChunkUIElementValidator haveEqualWidth();
    ChunkUIElementValidator haveEqualHeight();
    ChunkUIElementValidator haveDifferentSizes();
    ChunkUIElementValidator haveDifferentWidths();
    ChunkUIElementValidator haveDifferentHeights();

    // alignment

    ChunkUIElementValidator areLeftAligned();
    ChunkUIElementValidator areRightAligned();
    ChunkUIElementValidator areTopAligned();
    ChunkUIElementValidator areBottomAligned();

    //

    ChunkUIElementValidator areCenteredOnPageVertically();
    ChunkUIElementValidator areCenteredOnPageHorizontally();

    // equal distribution (horizontal, vertically, both)?

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

    /**
     * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.validation.properties.Expression#percent(int, net.itarray.automotion.internal.properties.PercentReference)}
     */
    @Deprecated
    ChunkUIElementValidator changeMetricsUnitsTo(util.validator.ResponsiveUIValidator.Units units);
    /**
     * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.validation.properties.Expression#percent(int, net.itarray.automotion.internal.properties.PercentReference)}
     */
    @Deprecated
    ChunkUIElementValidator changeMetricsUnitsTo(Units units);

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#doNotOverlap()}
     */
    @Deprecated
    default ChunkUIElementValidator areNotOverlappedWithEachOther() { return doNotOverlap(); }
    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#haveEqualSize()}
     */
    @Deprecated
    default ChunkUIElementValidator withSameSize() { return haveEqualSize(); }

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#haveEqualWidth()}
     */
    @Deprecated
    default ChunkUIElementValidator withSameWidth() { return haveEqualWidth(); }
    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#haveEqualHeight()}
     */
    @Deprecated
    default ChunkUIElementValidator withSameHeight() { return haveEqualHeight(); }

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#haveDifferentSizes()}
     */
    @Deprecated
    default ChunkUIElementValidator withNotSameSize() { return haveDifferentSizes(); }
    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#haveDifferentWidths()}
     */
    @Deprecated
    default ChunkUIElementValidator withNotSameWidth() { return haveDifferentWidths(); }

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#haveDifferentHeights()}
     */
    @Deprecated
    default ChunkUIElementValidator withNotSameHeight() { return haveDifferentHeights(); }
    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#areRightAligned()}
     */
    @Deprecated
    default ChunkUIElementValidator sameRightOffset() { return areRightAligned(); }

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#areLeftAligned()}
     */
    @Deprecated
    default ChunkUIElementValidator sameLeftOffset() { return areLeftAligned(); }

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#areTopAligned()}
     */
    @Deprecated
    default ChunkUIElementValidator sameTopOffset() { return areTopAligned(); }

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#areBottomAligned()}
     */
    @Deprecated
    default ChunkUIElementValidator sameBottomOffset() { return areBottomAligned(); }
    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#areCenteredOnPageVertically()}
     */
    @Deprecated
    default ChunkUIElementValidator equalLeftRightOffset() { return areCenteredOnPageVertically(); }

    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#areCenteredOnPageHorizontally()}
     */
    @Deprecated
    default ChunkUIElementValidator equalTopBottomOffset() { return areCenteredOnPageHorizontally(); }
    /**
     * @deprecated As of release 2.0, replaced by {@link ChunkUIElementValidator#areInsideOf(org.openqa.selenium.WebElement, String)}
     */
    @Deprecated
    default ChunkUIElementValidator insideOf(WebElement containerElement, String readableContainerName) { return areInsideOf(containerElement, readableContainerName); }
}
