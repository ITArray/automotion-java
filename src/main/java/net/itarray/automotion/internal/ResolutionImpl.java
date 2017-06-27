package net.itarray.automotion.internal;

import net.itarray.automotion.validation.properties.Resolution;
import org.openqa.selenium.Dimension;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResolutionImpl implements Resolution {
    public static final Pattern PATTERN = Pattern.compile("(\\d+)[xX](\\d+)");

    private final Dimension extend;

    private ResolutionImpl(Dimension extend) {
        this.extend = extend;
    }

    @Override
    public int hashCode() {
        return extend.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ResolutionImpl)) {
            return false;
        }
        return extend.equals(((ResolutionImpl)obj).extend);
    }

    @Override
    public String toString() {
        return String.format("%sx%s", extend.getWidth(), extend.getHeight());
    }

    public static Resolution of(Dimension extend) {
        return new ResolutionImpl(extend);
    }

    public static Resolution of(int width, int height) {
        return new ResolutionImpl(new Dimension(width, height));
    }

    public static Resolution of (String widthXheight) {
        Matcher matcher = PATTERN.matcher(widthXheight);
        if (!matcher.matches()) {
            throw new RuntimeException("bad format for a resoltion. Format is numberxnumber");
        }
        int width = Integer.parseInt(matcher.group(1));
        int height = Integer.parseInt(matcher.group(2));
        return new ResolutionImpl(new Dimension(width, height));
    }

    @Override
    public void applyTo(DriverFacade driver) {
        driver.setResolution(extend);
    }

    @Override
    public Resolution queryIfUnknown(DriverFacade driver) {
        return this;
    }
}
