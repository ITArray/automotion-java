package net.itarray.automotion.tests.grid;

import net.itarray.automotion.validation.Chunk;
import net.itarray.automotion.validation.Element;
import org.junit.Test;

@Chunk({
        @Element({100,  50,  300,  60}),
        @Element({400,  50,  700,  70}),
        @Element({900,  50, 1200,  80}),
        @Element({100, 150,  300, 160}),
        @Element({400, 150,  700, 170}),
        @Element({900, 150, 1200, 180}),
        @Element({100, 250,  300, 160}),
})
public class SevenElementsInThreeRowsWithDifferentSizesAndGuttersTest extends ChunkTest{

    @Test
    public void areAlignedAsGridCells() {
        chunkValidator.areAlignedAsGridCells();
        assertValid();
    }

    @Test
    public void areNotAlignedInTwoColumns() {
        chunkValidator.alignedAsGrid(2);
        assertInvalid();
    }

    @Test
    public void areAlignedInThreeColumns() {
        chunkValidator.alignedAsGrid(3);
        assertValid();
    }

    @Test
    public void areAlignedInThreeColumnsAndThreeRows() {
        chunkValidator.alignedAsGrid(3, 3);
        assertValid();
    }

    @Test
    public void areNotAlignedInThreeColumnsAndTwoRows() {
        chunkValidator.alignedAsGrid(3, 2);
        assertInvalid();
    }

    @Test
    public void areNotAlignedInThreeColumnsAndTwoFour() {
        chunkValidator.alignedAsGrid(3, 4);
        assertInvalid();
    }

    @Test
    public void areNotAlignedInFourColumns() {
        chunkValidator.alignedAsGrid(4);
        assertInvalid();
    }

    @Test
    public void areNotAlignedInFourColumnsAndOneRow() {
        chunkValidator.alignedAsGrid(4, 1);
        assertInvalid();
    }
}
