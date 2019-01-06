package android.support.constraint.solver.widgets;

public class Rectangle {
    public int height;
    public int width;
    /* renamed from: x */
    public int f4x;
    /* renamed from: y */
    public int f5y;

    public void setBounds(int i, int i2, int i3, int i4) {
        this.f4x = i;
        this.f5y = i2;
        this.width = i3;
        this.height = i4;
    }

    void grow(int i, int i2) {
        this.f4x -= i;
        this.f5y -= i2;
        this.width += i * 2;
        this.height += i2 * 2;
    }

    boolean intersects(Rectangle rectangle) {
        return (this.f4x < rectangle.f4x || this.f4x >= rectangle.f4x + rectangle.width || this.f5y < rectangle.f5y || this.f5y >= rectangle.f5y + rectangle.height) ? null : true;
    }

    public boolean contains(int i, int i2) {
        return i >= this.f4x && i < this.f4x + this.width && i2 >= this.f5y && i2 < this.f5y + this.height;
    }

    public int getCenterX() {
        return (this.f4x + this.width) / 2;
    }

    public int getCenterY() {
        return (this.f5y + this.height) / 2;
    }
}
