package net.itarray.automotion.internal;

public class Vector {
    private final Scalar x;
    private final Scalar y;

    public Vector(Scalar x, Scalar y) {
        this.x = x;
        this.y = y;
    }

    public Vector(int x, int y) {
        this(new Scalar(x), new Scalar(y));
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

    public Scalar getX() {
        return x;
    }

    public Scalar getY() {
        return y;
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

    public Vector minus(Vector subtrahend) {
        return new Vector(x.minus(subtrahend.getX()), y.minus(subtrahend.getY()));
    }

    public Vector plus(Vector addend) {
        return new Vector(x.plus(addend.getX()), y.plus(addend.getY()));
    }
}
