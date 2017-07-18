package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Rectangle;
import net.itarray.automotion.internal.geometry.Scalar;

public class PagePercentage implements Expression<Scalar> {
    private final Scalar percentage;

    public PagePercentage(Scalar percentage) {
        this.percentage = percentage;
    }

    @Override
    public Scalar evaluateIn(Context context, Direction direction) {
        Rectangle page = context.getPageRectangle();
        int screenExtend = direction.extend(page).abs().getValue();
        return new Scalar((percentage.getValue() * screenExtend) / 100);
    }

    @Override
    public String toStringWithUnits(String units) {
        return null;
    }
}
