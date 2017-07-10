package net.itarray.automotion.internal.geometry;

public enum Direction implements ExtendGiving<Scalar> {
    DOWN{
        @Override
        public boolean beforeOrEqual(Scalar p1, Scalar p2) {
            return p1.isLessOrEqualThan(p2);
        }

        public String beforeName() {
            return "Above";
        }

        @Override
        public Direction opposite() {
            return UP;
        }

        @Override
        public Scalar begin(Rectangle rectangle) {
            return rectangle.getOrigin().getY();
        }

        @Override
        public String beginName() {
            return "top";
        }

        @Override
        public String extendName() {
            return "height";
        }
    },
    UP {
        @Override
        public boolean beforeOrEqual(Scalar p1, Scalar p2) {
            return p2.isLessOrEqualThan(p1);
        }

        public String beforeName() {
            return "Below";
        }

        @Override
        public Direction opposite() {
            return DOWN;
        }

        @Override
        public Scalar begin(Rectangle rectangle) {
            return rectangle.getCorner().getY();
        }

        @Override
        public String beginName() {
            return "bottom";
        }

        @Override
        public String extendName() {
            return "height";
        }
    },
    RIGHT {
        @Override
        public boolean beforeOrEqual(Scalar p1, Scalar p2) {
            return p1.isLessOrEqualThan(p2);
        }

        public String beforeName() {
            return "Left";
        }

        @Override
        public Direction opposite() {
            return LEFT;
        }

        @Override
        public Scalar begin(Rectangle rectangle) {
            return rectangle.getOrigin().getX();
        }

        @Override
        public String beginName() {
            return "left";
        }

        @Override
        public String extendName() {
            return "width";
        }
    },
    LEFT {
        @Override
        public boolean beforeOrEqual(Scalar p1, Scalar p2) {
            return p2.isLessOrEqualThan(p1);
        }

        public String beforeName() {
            return "Right";
        }

        @Override
        public Direction opposite() {
            return RIGHT;
        }

        @Override
        public Scalar begin(Rectangle rectangle) {
            return rectangle.getCorner().getX();
        }

        @Override
        public String beginName() {
            return "right";
        }

        @Override
        public String extendName() {
            return "width";
        }
    };

    public abstract Direction opposite();

    public abstract Scalar begin(Rectangle rectangle);

    public Scalar end(Rectangle rectangle) {
        return opposite().begin(rectangle);
    }

    public String beginName() {
        return "begin";
    }

    public String endName() {
        return opposite().beginName();
    }

    public abstract String beforeName();

    public String afterName() {
        return opposite().beforeName();
    }

    public abstract String extendName();

    public abstract boolean beforeOrEqual(Scalar p1, Scalar p2);

    public Scalar signedDistance(Scalar p1, Scalar p2) {
        return beforeOrEqual(p1, p2) ? distance(p1, p2) : distance(p1, p2).negated();
    }

    public Scalar distance(Scalar p1, Scalar p2) {
        return p2.minus(p1).abs();
    }
}
