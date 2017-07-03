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
    };

    public abstract Direction opposite();

    public abstract int begin(Rectangle element);

    public int end(Rectangle rectangle) {
        return opposite().begin(rectangle);
    }
}
