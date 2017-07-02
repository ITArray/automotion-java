package net.itarray.automotion.internal;

public enum Direction {
    DOWN{
        @Override
        public Direction oposite() {
            return UP;
        }

        @Override
        public int begin(UIElement element) {
            return element.getY();
        }
        @Override
        public int begin(Rectangle element) {
            return element.getOriginY();
        }
    },
    UP {
        @Override
        public Direction oposite() {
            return DOWN;
        }

        @Override
        public int begin(UIElement element) {
            return element.getCornerY();
        }
        @Override
        public int begin(Rectangle element) {
            return element.getCornerY();
        }
    },
    RIGHT {
        @Override
        public Direction oposite() {
            return LEFT;
        }

        @Override
        public int begin(UIElement element) {
            return element.getX();
        }
        @Override
        public int begin(Rectangle element) {
            return element.getOriginX();
        }
    },
    LEFT {
        @Override
        public Direction oposite() {
            return RIGHT;
        }

        @Override
        public int begin(UIElement element) {
            return element.getCornerX();
        }

        @Override
        public int begin(Rectangle element) {
            return element.getCornerX();
        }
    };

    public abstract Direction oposite();

    public abstract int begin(UIElement element);
    public abstract int begin(Rectangle element);
}
