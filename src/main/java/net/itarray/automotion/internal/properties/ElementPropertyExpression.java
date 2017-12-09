package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.UIElement;
import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.ExtendGiving;
import net.itarray.automotion.internal.geometry.MetricSpace;
import net.itarray.automotion.validation.properties.Expression;

import java.util.function.Function;

public class ElementPropertyExpression<T> implements Expression<T> {

    private final ElementProperty<T> property;
    private final UIElement element;

    private ElementPropertyExpression(UIElement element, Function<UIElement, T> property, String descriptionFormat, String name) {
        this.element = element;
        this.property = new ElementProperty<>(property, descriptionFormat, name);
    }

    public static <V extends MetricSpace<V>> ElementPropertyExpression<V> extend(ExtendGiving<V> direction, UIElement element) {
        return new ElementPropertyExpression<>(element, e -> e.getExtend(direction), direction.extendName() +
                    " of element %s", direction.extendName());
    }

    public static <V extends MetricSpace<V>> ElementPropertyExpression<V> end(ExtendGiving<V> direction, UIElement element) {
        return new ElementPropertyExpression<>(element, e -> e.getEnd(direction), direction.endName() +
                    " of element %s", direction.extendName());
    }

    @Override
    public <V extends MetricSpace<V>> T evaluateIn(Context context, ExtendGiving<V> direction) {
        return property.evaluateOn(element);
    }

    @Override
    public <V extends MetricSpace<V>> String getDescription(Context context, ExtendGiving<V> direction) {
        return property.getDescriptionFor(element);
    }

    @Override
    public <V extends MetricSpace<V>> String getRepeatedDescription(Context context, ExtendGiving<V> direction) {
        return property.getName();
    }

}
