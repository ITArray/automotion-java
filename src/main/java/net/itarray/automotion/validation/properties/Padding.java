package net.itarray.automotion.validation.properties;

/**
 * Created by ZayCo on 03/03/17.
 */
public class Padding {

    private int top = -1;
    private int right = -1;
    private int bottom = -1;
    private int left = -1;

    public Padding(int padding) {
        top = padding;
        right = padding;
        bottom = padding;
        left = padding;
    }

    public Padding(int top, int right, int bottom, int left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }
}
