package net.itarray.automotion.internal.properties;

public interface Expression<T> {

    T evaluateIn(Context context);

    default T evaluate() {
        return evaluateIn(null);
    }
}
