package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Scalar;

public class PagePercentageOrPixels implements Expression<Scalar> {

    private final ScalarConstant constant;
    private final PagePercentage pagePercentage;

    PagePercentageOrPixels(Scalar constant) {
        this.constant = new ScalarConstant(constant);
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
    public Scalar evaluateIn(Context context, Direction direction) {
        if (context.isPixels()) {
            return constant.evaluateIn(context, direction);
        }
        return pagePercentage.evaluateIn(context, direction);
    }

    @Override
    public String toStringWithUnits(String units) {
        return constant.toStringWithUnits(units);
    }
}
