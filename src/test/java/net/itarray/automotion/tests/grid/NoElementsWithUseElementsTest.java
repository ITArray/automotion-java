package net.itarray.automotion.tests.grid;

import org.junit.Test;

@Chunk
@AllowEmpty
public class NoElementsWithUseElementsTest extends ChunkTest {

    @Test
    public void areAlignedAsGridCells() {
        chunkValidator.areAlignedAsGridCells();
        assertValid();
    }

    @Test
    public void areNotAlignedInThreeColumns() {
        chunkValidator.alignedAsGrid(3);
        assertValid();
    }

    @Test
    public void areNotAlignedInThreeColumnsAndThreeRows() {
        chunkValidator.alignedAsGrid(3, 3);
        assertInvalid();
    }


}
