package net.itarray.automotion.tests;

import net.itarray.automotion.internal.ResolutionImpl;
import org.junit.Test;

public class ResolutionParseTest {

    @Test(expected = RuntimeException.class)
    public void doesNotAllowForHexNumbers() {
        ResolutionImpl.of("0x12x34");
    }
}
