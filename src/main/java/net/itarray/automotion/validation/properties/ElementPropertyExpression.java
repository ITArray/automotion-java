package net.itarray.automotion.validation.properties;

import net.itarray.automotion.internal.UIElement;
import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.ExtendGiving;
import net.itarray.automotion.internal.geometry.Group;
import net.itarray.automotion.internal.properties.Context;

import java.util.function.Function;

public class ElementPropertyExpression<T> implements Expression<T> {

    private final ElementProperty<T> property;
    private final UIElement element;

    private ElementPropertyExpression(UIElement element, Function<UIElement, T> property, String descriptionFormat, String name) {
        this.element = element;
        this.property = new ElementProperty<>(property, descriptionFormat, name);
    }

    public static <V extends Group<V>> ElementPropertyExpression<V> extend(ExtendGiving<V> direction, UIElement element) {
        return new ElementPropertyExpression<>(element, e -> e.getExtend(direction), direction.extendName() +
                    " of element %s", direction.extendName());
    }

    @Override
    public T evaluateIn(Context context, Direction direction) {
        return property.evaluateOn(element);
    }

    @Override
    public String getDescription(Context context, Direction direction) {
        return property.getDescriptionFor(element);
    }

    public String getName() {
        return property.getName();
    }
}