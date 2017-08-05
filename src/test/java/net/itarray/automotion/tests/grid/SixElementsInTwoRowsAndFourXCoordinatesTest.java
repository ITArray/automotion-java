package net.itarray.automotion.tests.grid;

import net.itarray.automotion.internal.ResponsiveUIValidatorBase;
import net.itarray.automotion.validation.ChunkUIElementValidator;
import net.itarray.automotion.validation.ResponsiveUIValidator;
import net.itarray.automotion.validation.UISnapshot;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import rectangles.DummyDriverFacade;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.DummyWebElement.createElement;

public class SixElementsInTwoRowsAndFourXCoordinatesTest extends GridTest {

    public List<WebElement> createElemements() {
        return newArrayList(
                    createElement(100, 50, 300, 60),
                    createElement(400, 50, 700, 70),
                    createElement(900, 50, 1200, 80),
                    createElement(100, 150, 300, 160),
                    createElement(400, 150, 700, 170),
                    createElement(900, 150, 1200, 180),
                    createElement(100, 250, 300, 160)
                    );
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
