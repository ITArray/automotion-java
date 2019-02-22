package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.ExtendGiving;
import net.itarray.automotion.internal.geometry.MetricSpace;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Expression;

public class PagePercentageOrPixels implements Expression<Scalar> {

    private final PixelConstant constant;
    private final PagePercentage pagePercentage;

    public PagePercentageOrPixels(Scalar constant) {
        this.constant = new PixelConstant(constant);
        this.pagePercentage = new PagePercentage(constant);
    }


    @Override
    public String toString() {
        return String.format("percentOrPixel(%s)", constant);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof  PagePercentageOrPixels)) {
            return false;
        }
        PagePercentageOrPixels other = (PagePercentageOrPixels) object;
        return constant.equals(other.constant);
    }

    @Override
    public int hashCode() {
        return constant.hashCode();
    }

    @Override
    public <V extends MetricSpace<V>> Scalar evaluateIn(Context context, ExtendGiving<V> direction) {
        if (context.isPixels()) {
            return constant.evaluateIn(context, direction);
        }
        return pagePercentage.evaluateIn(context, direction);
    }

    @Override
    public <V extends MetricSpace<V>> String getDescription(Context context, ExtendGiving<V> direction) {
        return constant.getDescription(context, direction);
    }
}
