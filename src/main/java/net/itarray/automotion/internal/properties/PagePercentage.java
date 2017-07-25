package net.itarray.automotion.internal.properties;

import net.itarray.automotion.internal.geometry.Direction;
import net.itarray.automotion.internal.geometry.Rectangle;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Expression;

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
    public String getDescription(Context context, Direction direction) {
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
