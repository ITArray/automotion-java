package net.itarray.automotion.tests.grid;

import org.junit.Test;

@Chunk
public class NoElementsTest extends ChunkTest {

    @Test
    public void areAlignedAsGridCells() {
        chunkValidator.areAlignedAsGridCells();
        assertInvalid();
    }

    @Test
    public void areNotAlignedInThreeColumns() {
        chunkValidator.alignedAsGrid(3);
        assertInvalid();
    }

    @Test
    public void areNotAlignedInThreeColumnsAndThreeRows() {
        chunkValidator.alignedAsGrid(3, 3);
        assertInvalid();
    }


}
