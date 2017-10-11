package net.itarray.automotion.tests.grid;

import net.itarray.automotion.validation.Chunk;
import net.itarray.automotion.validation.Element;
import org.junit.Test;

@Chunk({
        @Element({100, 50,  300, 60}),
        @Element({400, 50,  700, 60}),
        @Element({900, 50, 1200, 60}),
})
public class ThreeElementsInARowWithDifferentSizesAndGuttersTest extends ChunkTest {

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
    public void areAlignedInThreeColumnsAndOneRow() {
        chunkValidator.alignedAsGrid(3, 1);
        assertValid();
    }

    @Test
    public void areNotAlignedInThreeColumnsAndTwoRows() {
        chunkValidator.alignedAsGrid(3, 2);
        assertInvalid();
    }

    @Test
    public void areAlignedInFourColumns() {
        chunkValidator.alignedAsGrid(4);
        assertValid();
    }

    @Test
    public void areAlignedInFourColumnsAndOneRow() {
        chunkValidator.alignedAsGrid(4, 1);
        assertValid();
    }

    @Test
    public void areNotAlignedInFourColumnsAndTwoRows() {
        chunkValidator.alignedAsGrid(4, 2);
        assertInvalid();
    }
}
