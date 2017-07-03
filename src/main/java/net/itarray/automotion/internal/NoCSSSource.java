package net.itarray.automotion.internal;

public class NoCSSSource  extends CSSSource{
    @Override
    public String getCssValue(String propertyName) {
        return "";
    }
}
