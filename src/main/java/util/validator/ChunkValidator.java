package util.validator;

public interface ChunkValidator {

    ResponsiveUIChunkValidator alignedAsGrid(int horizontalGridSize);

    ResponsiveUIChunkValidator alignedAsGrid(int horizontalGridSize, int verticalGridSize);

    ResponsiveUIChunkValidator areNotOverlappedWithEachOther();

    ResponsiveUIChunkValidator withSameSize();

}
