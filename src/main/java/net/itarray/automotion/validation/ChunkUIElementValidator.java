package net.itarray.automotion.validation;

import org.openqa.selenium.WebElement;

@ChunkDefs({
        @ChunkDef(name = "empty", value={
        }),
        @ChunkDef(name = "one element", value={
                @Element({10, 20, 40, 50})
        }),
        @ChunkDef(name = "two overlapping elements", value={
                @Element({10, 20, 30, 35}),
                @Element({15, 25, 35, 50}),
        }),
        @ChunkDef(name="three elements with different sizes in a row with different gutters", value={
                @Element({100, 50,  300, 60}),
                @Element({400, 50,  700, 60}),
                @Element({900, 50, 1200, 60}),
        }),
        @ChunkDef(name="seven elements in three rows with different sizes and gutters", value={
                @Element({100,  50,  300,  60}),
                @Element({400,  50,  700,  70}),
                @Element({900,  50, 1200,  80}),
                @Element({100, 150,  300, 160}),
                @Element({400, 150,  700, 170}),
                @Element({900, 150, 1200, 180}),
                @Element({100, 250,  300, 160}),
        })

})
public interface ChunkUIElementValidator {

    boolean validate();

    // ? filled needs to be expressed somehow
    // areAlignedInColumns(numberOfColumns)
    @ValidChunks({
            @Chunk(name = "empty", params = {"3"}),
            @Chunk(name = "one element", params = {"1"}),
            @Chunk(name = "three elements with different sizes in a row with different gutters", params = {"3", "4"}),
            @Chunk(name = "seven elements in three rows with different sizes and gutters", params = {"3"}),
    })
    @InvalidChunks({
            @Chunk(name = "empty", params = {"3"}, oneOrMore = true),
            @Chunk(name = "three elements with different sizes in a row with different gutters", params = {"2"}),
            @Chunk(name = "seven elements in three rows with different sizes and gutters", params = {"2", "4"}),
    })
    ChunkUIElementValidator alignedAsGrid(int horizontalGridSize);

    // areAlignedInColumnsAndRows(numberOfColumns)
    /**
     * Validate that this chunks elements are aligned in a grid of cells (not areas).
     *
     * <img src="./doc-files/sample.svg" style="display: block"></img>
     *
     * @return this
     */
    @ValidChunks({
            @Chunk(name = "empty"),
            @Chunk(name = "single element"),
            @Chunk(name = "seven elements in three rows with different sizes and gutters"),
    })
    @InvalidChunks({
            @Chunk(name = "empty", oneOrMore = true),
            @Chunk(name = "two overlapping elements"),
    })
    ChunkUIElementValidator areAlignedAsGridCells();


    // area
    @ValidChunks({
            @Chunk(name = "one element", params = {"1, 1"}),
            @Chunk(name = "three elements with different sizes in a row with different gutters", params = {"3, 1", "4, 1"}),
            @Chunk(name = "seven elements in three rows with different sizes and gutters", params = {"3, 3"}),
    })
    @InvalidChunks({
            @Chunk(name = "empty", params = {"3, 3"}, oneOrMore = true),
            @Chunk(name = "empty", params = {"3, 3"}),
            @Chunk(name = "three elements with different sizes in a row with different gutters", params = {"3, 2", "4, 2"}),
            @Chunk(name = "seven elements in three rows with different sizes and gutters", params = {"3, 2", "3, 4", "4, 1"}),
    })
    ChunkUIElementValidator alignedAsGrid(int horizontalGridSize, int verticalGridSize);

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

    @ValidChunks({
            @Chunk(name = "one element"),
    })
    ChunkUIElementValidator areLeftAligned();
    @ValidChunks({
            @Chunk(name = "one element"),
    })
    ChunkUIElementValidator areRightAligned();
    @ValidChunks({
            @Chunk(name = "one element"),
    })
    ChunkUIElementValidator areTopAligned();
    @ValidChunks({
            @Chunk(name = "one element"),
    })
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
