package net.itarray.automotion.tests.grid;

import net.itarray.automotion.validation.Chunk;
import org.junit.Test;

@Chunk
public class NoElementsWithFindOneOrMoreTest extends ChunkTest {

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
