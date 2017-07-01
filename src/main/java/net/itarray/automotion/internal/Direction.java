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
    };

    public abstract Direction oposite();

    public abstract int begin(UIElement element);
}
