package net.itarray.automotion.internal;

public class Vector {
    private final int x;
    private final int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Vector)) {
            return false;
        }
        Vector other = (Vector) object;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(x) * 31 ^ Integer.hashCode(y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return toStringWithUnits("");
    }

    public String toStringWithUnits(String units) {
        return String.format("%d%s x %d%s", x, units, y, units);
    }
}
