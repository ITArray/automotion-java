package net.itarray.automotion.tests.grid;

import net.itarray.automotion.validation.Chunk;
import net.itarray.automotion.validation.Element;
import org.junit.Test;

@Chunk({
        @Element({10, 20, 40, 50})
})
public class OneElementTest extends ChunkTest {

    @Test
    public void areAlignedAsGridCells() {
        chunkValidator.areAlignedAsGridCells();
        assertValid();
    }

    @Test
    public void areAlignedInTOneColumn() {
        chunkValidator.alignedAsGrid(1);
        assertValid();
    }

    @Test
    public void areAlignedInOneColumnAndOneRow() {
        chunkValidator.alignedAsGrid(1, 1);
        assertValid();
    }

    @Test
    public void areTopAligned() {
        chunkValidator.areTopAligned();
        assertValid();
    }

    @Test
    public void areBottomAligned() {
        chunkValidator.areBottomAligned();
        assertValid();
    }

    @Test
    public void areLeftAligned() {
        chunkValidator.areLeftAligned();
        assertValid();
    }

    @Test
    public void areRightAligned() {
        chunkValidator.areRightAligned();
        assertValid();
    }
}
