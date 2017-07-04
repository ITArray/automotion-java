package net.itarray.automotion.internal;

public enum Direction {
    DOWN{
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
    },
    UP {
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
    },
    RIGHT {
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
    },
    LEFT {
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
    };

    public abstract Direction opposite();

    public abstract int begin(Rectangle element);

    public int end(Rectangle rectangle) {
        return opposite().begin(rectangle);
    }

    public String beginName() {
        return "begin";
    }
}
