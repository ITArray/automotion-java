package net.itarray.automotion.tests.grid;

import com.google.common.collect.Lists;
import net.itarray.automotion.validation.Chunk;
import net.itarray.automotion.validation.ChunkUIElementValidator;
import net.itarray.automotion.validation.InvalidChunks;
import net.itarray.automotion.validation.ValidChunks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(Parameterized.class)
public class AnnotatedSpecificationTest {

    @Parameters(name = "{2}")
    public static Collection<Object[]> data() {
        Collection<Object[]> result = Lists.newArrayList();
        for (Method method : ChunkUIElementValidator.class.getDeclaredMethods()) {
            ValidChunks validChunks = method.getAnnotation(ValidChunks.class);
            if (validChunks != null) {
                for (Chunk chunk : validChunks.value()) {
                    String name = String.format("%s is valid on %s", method.getName(), chunk.name());
                    result.add(new Object[]{method, chunk, name});
                }
            }
            InvalidChunks invalidChunks = method.getAnnotation(InvalidChunks.class);
            if (validChunks != null) {
                for (Chunk chunk : invalidChunks.value()) {
                    String name = String.format("%s is invalid on %s", method.getName(), chunk.name());
                    result.add(new Object[]{method, chunk, name});
                }
            }
        }
        return result;
    }

    @Parameter
    public Method method;

    @Parameter(1)
    public Chunk chunk;

    @Parameter(2)
    public String name;

    @Test
    public void valid() {

        System.out.println("method = " + method);
    }
}
