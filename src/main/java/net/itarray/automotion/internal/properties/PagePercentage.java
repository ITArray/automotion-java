package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.ExtendGiving;
import net.itarray.automotion.internal.geometry.MetricSpace;
import net.itarray.automotion.internal.geometry.Rectangle;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Expression;

import static net.itarray.automotion.internal.geometry.Scalar.scalar;

public class PagePercentage implements Expression<Scalar> {
    private final Scalar percentage;

    public PagePercentage(Scalar percentage) {
        this.percentage = percentage;
    }

    @Override
    public <V extends MetricSpace<V>> Scalar evaluateIn(Context context, ExtendGiving<V> direction) {
        Rectangle page = context.getPageRectangle();
        Scalar screenExtend = direction.extend(page).norm();
        return percentage.times(screenExtend).by(scalar(100));
    }

    @Override
    public <V extends MetricSpace<V>> String getDescription(Context context, ExtendGiving<V> direction) {
        return String.format("%s%% of page (%spx)", percentage, evaluateIn(context, direction));
    }

    @Override
    public String toString() {
        return String.format("%s%% of page", percentage);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PagePercentage)) {
            return false;
        }
        PagePercentage other = (PagePercentage) object;
        return percentage.equals(other.percentage);
    }

    @Override
    public int hashCode() {
        return percentage.hashCode();
    }
}
