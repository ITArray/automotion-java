package net.itarray.automotion.validation;

import org.openqa.selenium.WebElement;

@Chunks({
        @Chunk(id = "empty",
                description = "empty", elements ={
        }),
        @Chunk(id = "one",
                description = "one element", elements ={
                @Element({10, 20, 40, 50})
        }),
        @Chunk(id = "two_overlapping",
                description = "two overlapping elements", elements ={
                @Element({10, 20, 30, 35}),
                @Element({15, 25, 35, 50}),
        }),
        @Chunk(id = "two_horizontally_overlapping",
                description = "two elements which horizontally projections overlap", elements ={
                @Element({10, 20, 30, 35}),
                @Element({15, 40, 35, 50}),
        }),
        @Chunk(id = "two_vertically_overlapping",
                description = "two elements which vertically projections overlap", elements ={
                @Element({10, 20, 30, 35}),
                @Element({40, 25, 60, 50}),
        }),
        @Chunk(id = "two_not_overlapping_in_any_direction",
                description = "two elements which horizontal and vertical projections don not overlap", elements ={
                @Element({10, 20, 30, 35}),
                @Element({40, 40, 60, 60}),
        }),
        @Chunk(id = "three",
                description ="three elements with different sizes in a row with different gutters", elements ={
                @Element({100, 50,  300, 60}),
                @Element({400, 50,  700, 60}),
                @Element({900, 50, 1200, 60}),
        }),
        @Chunk(id = "seven",
                description ="seven elements in three rows with different sizes and gutters", elements ={
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
    @Valid({
            @Scenario(chunk = "empty", params = {"3"}),
            @Scenario(chunk = "one", params = {"1"}),
            @Scenario(chunk = "three", params = {"3", "4"}),
            @Scenario(chunk = "seven", params = {"3"}),
    })
    @NotValid({
            @Scenario(chunk = "empty", params = {"3"}, oneOrMore = true),
            @Scenario(chunk = "three", params = {"2"}),
            @Scenario(chunk = "seven", params = {"2", "4"}),
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
    @Valid({
            @Scenario(chunk = "empty"),
            @Scenario(chunk = "one"),
            @Scenario(chunk = "seven"),
            @Scenario(chunk = "two_not_overlapping_in_any_direction"),
    })
    @NotValid({
            @Scenario(chunk = "empty", oneOrMore = true),
            @Scenario(chunk = "two_overlapping"),
            @Scenario(chunk = "two_horizontally_overlapping"),
            @Scenario(chunk = "two_vertically_overlapping"),
    })
    ChunkUIElementValidator areAlignedAsGridCells();


    // area
    @Valid({
            @Scenario(chunk = "one", params = {"1, 1"}),
            @Scenario(chunk = "three", params = {"3, 1", "4, 1"}),
            @Scenario(chunk = "seven", params = {"3, 3"}),
    })
    @NotValid({
            @Scenario(chunk = "empty", params = {"3, 3"}, oneOrMore = true),
            @Scenario(chunk = "empty", params = {"3, 3"}),
            @Scenario(chunk = "three", params = {"3, 2", "4, 2"}),
            @Scenario(chunk = "seven", params = {"3, 2", "3, 4", "4, 1"}),
    })
    ChunkUIElementValidator alignedAsGrid(int horizontalGridSize, int verticalGridSize);

    ChunkUIElementValidator doNotOverlap();
    ChunkUIElementValidator areInsideOf(WebElement containerElement, String readableContainerName);

    // size

    @Valid({
            @Scenario(chunk = "empty"),
            @Scenario(chunk = "one"),
    })
    @NotValid({
            @Scenario(chunk = "empty", oneOrMore = true),
    })
    ChunkUIElementValidator haveEqualSize();
    @Valid({
            @Scenario(chunk = "empty"),
            @Scenario(chunk = "one"),
    })
    @NotValid({
            @Scenario(chunk = "empty", oneOrMore = true),
    })
    ChunkUIElementValidator haveEqualWidth();
    @Valid({
            @Scenario(chunk = "empty"),
            @Scenario(chunk = "one"),
    })
    @NotValid({
            @Scenario(chunk = "empty", oneOrMore = true),
    })
    ChunkUIElementValidator haveEqualHeight();
    @Valid({
            @Scenario(chunk = "empty"),
            @Scenario(chunk = "one"),
    })
    @NotValid({
            @Scenario(chunk = "empty", oneOrMore = true),
    })
    ChunkUIElementValidator haveDifferentSizes();
    @Valid({
            @Scenario(chunk = "empty"),
            @Scenario(chunk = "one"),
    })
    @NotValid({
            @Scenario(chunk = "empty", oneOrMore = true),
    })
    ChunkUIElementValidator haveDifferentWidths();
    @Valid({
            @Scenario(chunk = "empty"),
            @Scenario(chunk = "one"),
    })
    @NotValid({
            @Scenario(chunk = "empty", oneOrMore = true),
    })
    ChunkUIElementValidator haveDifferentHeights();

    // alignment

    @Valid({
            @Scenario(chunk = "empty"),
            @Scenario(chunk = "one"),
    })
    @NotValid({
            @Scenario(chunk = "empty", oneOrMore = true),
    })
    ChunkUIElementValidator areLeftAligned();
    @Valid({
            @Scenario(chunk = "empty"),
            @Scenario(chunk = "one"),
    })
    @NotValid({
            @Scenario(chunk = "empty", oneOrMore = true),
    })
    ChunkUIElementValidator areRightAligned();
    @Valid({
            @Scenario(chunk = "empty"),
            @Scenario(chunk = "one"),
    })
    @NotValid({
            @Scenario(chunk = "empty", oneOrMore = true),
    })
    ChunkUIElementValidator areTopAligned();
    @Valid({
            @Scenario(chunk = "empty"),
            @Scenario(chunk = "one"),
    })
    @NotValid({
            @Scenario(chunk = "empty", oneOrMore = true),
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
