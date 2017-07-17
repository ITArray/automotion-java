package net.itarray.automotion.validation;

import net.itarray.automotion.internal.ZoomImpl;
import net.itarray.automotion.internal.geometry.Scalar;
import net.itarray.automotion.validation.properties.Condition;
import net.itarray.automotion.internal.properties.Conditions;
import net.itarray.automotion.validation.properties.Resolution;
import net.itarray.automotion.internal.ResolutionImpl;
import net.itarray.automotion.validation.properties.Zoom;

public class Literals {

    public static Resolution resolution(String widthXheight) {
        return ResolutionImpl.of(widthXheight);
    }

    public static Resolution resolution(int width, int height) {
        return ResolutionImpl.of(width, height);
    }

    public static Zoom zoom(int percentage) {
        return ZoomImpl.of(percentage);
    }

    public static Condition<Scalar> lessOrEqualTo(int limit) {
        return Conditions.lessOrEqualTo(new Scalar(limit));
    }

    public static Condition<Scalar> greaterOrEqualTo(int limit) {
        return Conditions.greaterOrEqualTo(new Scalar(limit));
    }

    public static B between(int lowerLimit) {
        return new B(lowerLimit);
    }

    public static class B {

        private final int lowerLimit;

        public B(int lowerLimit) {

            this.lowerLimit = lowerLimit;
        }

        public Condition<Scalar> and(int upperLimit) {
            return Conditions.between(new Scalar(lowerLimit), new Scalar(upperLimit));
        }
    }
}
