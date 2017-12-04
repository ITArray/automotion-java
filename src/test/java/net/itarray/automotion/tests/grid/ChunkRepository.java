package net.itarray.automotion.tests.grid;

import net.itarray.automotion.validation.Chunk;
import net.itarray.automotion.validation.Chunks;
import net.itarray.automotion.validation.Element;
import org.assertj.core.util.Maps;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static rectangles.DummyWebElement.createElement;

public class ChunkRepository {

    Map<String, List<WebElement>> chunksById = Maps.newHashMap();

    public void addClass(Class<?> aClass) {
        Chunks definitions = aClass.getAnnotation(Chunks.class);
        if (definitions == null) {
            return;
        }
        for (Chunk definition : definitions.value()) {
            List<WebElement> chunk = newArrayList();
            Element[] elements = definition.elements();
            for (Element element : elements) {
                chunk.add(createElement(
                        element.value()[0],
                        element.value()[1],
                        element.value()[2],
                        element.value()[3]
                ));
            }
            chunksById.put(definition.id(), chunk);
        }
    }

    public List<WebElement> get(String name) {
        List<WebElement> webElements = chunksById.get(name);
        if (webElements == null) {
            throw new RuntimeException("no such chunk " + name);
        }
        return webElements;
    }
}
