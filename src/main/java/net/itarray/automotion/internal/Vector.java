package net.itarray.automotion.internal;

public class Vector {
    private final Scalar x;
    private final Scalar y;

    public Vector(int x, int y) {
        this.x = new Scalar(x);
        this.y = new Scalar(y);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Vector)) {
            return false;
        }
        Vector other = (Vector) object;
        return x.equals(other.x) && y.equals(other.y);
    }

    @Override
    public int hashCode() {
        return x.hashCode() * 31 ^ y.hashCode();
    }

    public int getX() {
        return x.getValue();
    }

    public int getY() {
        return y.getValue();
    }

    @Override
    public String toString() {
        return toStringWithUnits("");
    }

    public String toStringWithUnits(String units) {
        return String.format(
                "%s x %s",
                x.toStringWithUnits(units),
                y.toStringWithUnits(units));
    }
}
