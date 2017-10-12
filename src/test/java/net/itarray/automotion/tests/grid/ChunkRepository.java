package net.itarray.automotion.tests.grid;

import net.itarray.automotion.validation.ChunkDef;
import net.itarray.automotion.validation.ChunkDefs;
import net.itarray.automotion.validation.Element;
import org.assertj.core.util.Maps;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static rectangles.DummyWebElement.createElement;

public class ChunkRepository {

    Map<String, List<WebElement>> chunksByName = Maps.newHashMap();

    public void addClass(Class<?> aClass) {
        ChunkDefs definitions = aClass.getAnnotation(ChunkDefs.class);
        if (definitions == null) {
            return;
        }
        for (ChunkDef definition : definitions.value()) {
            List<WebElement> chunk = newArrayList();
            Element[] elements = definition.value();
            for (Element element : elements) {
                chunk.add(createElement(
                        element.value()[0],
                        element.value()[1],
                        element.value()[2],
                        element.value()[3]
                ));
            }
            chunksByName.put(definition.name(), chunk);
        }
    }

    public Optional<List<WebElement>> getChunk(String name) {
        return Optional.ofNullable(chunksByName.get(name));
    }
}
