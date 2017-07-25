package net.itarray.automotion.validation.properties;

import net.itarray.automotion.internal.UIElement;

import java.util.function.Function;

public class ElementProperty<T> {
    private final Function<UIElement, T> property;
    private final String descriptionFormat;
    private final String name;

    public ElementProperty(Function<UIElement, T> property, String descriptionFormat, String name) {
        this.property = property;
        this.descriptionFormat = descriptionFormat;
        this.name = name;
    }

    public T evaluateOn(UIElement element) {
        return property.apply(element);
    }

    public String getDescriptionFor(UIElement element) {
        return String.format(descriptionFormat, element.getQuotedName());
    }

    public String getName() {
        return name;
    }
}
