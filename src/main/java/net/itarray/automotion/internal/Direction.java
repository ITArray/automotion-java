package net.itarray.automotion.internal;

public enum Direction {
    DOWN{
        @Override
        public boolean beforeOrEqual(int p1, int p2) {
            return p1 <= p2;
        }

        public String beforeName() {
            return "Above";
        }

        @Override
        public Direction opposite() {
            return UP;
        }

        @Override
        public int begin(Rectangle element) {
            return element.getOriginY();
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
        public boolean beforeOrEqual(int p1, int p2) {
            return p2 <= p1;
        }

        public String beforeName() {
            return "Below";
        }

        @Override
        public Direction opposite() {
            return DOWN;
        }

        @Override
        public int begin(Rectangle element) {
            return element.getCornerY();
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
        public boolean beforeOrEqual(int p1, int p2) {
            return p1 <= p2;
        }

        public String beforeName() {
            return "Left";
        }

        @Override
        public Direction opposite() {
            return LEFT;
        }

        @Override
        public int begin(Rectangle element) {
            return element.getOriginX();
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
        public boolean beforeOrEqual(int p1, int p2) {
            return p2 <= p1;
        }

        public String beforeName() {
            return "Right";
        }

        @Override
        public Direction opposite() {
            return RIGHT;
        }

        @Override
        public int begin(Rectangle element) {
            return element.getCornerX();
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

    public abstract int begin(Rectangle element);

    public int end(Rectangle rectangle) {
        return opposite().begin(rectangle);
    }

    public String beginName() {
        return "begin";
    }

    public String endName() {
        return opposite().beginName();
    }

    public abstract String beforeName();

    public abstract String extendName();

    public abstract boolean beforeOrEqual(int p1, int p2);
}
