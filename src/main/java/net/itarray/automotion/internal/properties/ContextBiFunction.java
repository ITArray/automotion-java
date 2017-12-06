package net.itarray.automotion.internal.properties;

@FunctionalInterface
public interface ContextBiFunction<L, R, T> {
    T apply(L l, R r, Context context);
}
