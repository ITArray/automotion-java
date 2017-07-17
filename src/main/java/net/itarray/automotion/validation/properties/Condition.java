package net.itarray.automotion.validation.properties;

public interface Condition<T> {

    boolean isSatisfiedOn(T value);

    String shortName();

    String toStringWithUnits(String units);
}
