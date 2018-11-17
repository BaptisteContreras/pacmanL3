package model.coordonates;

import java.util.Objects;

public class Coord2D extends Coord {

    private int x;
    private int y;

    public Coord2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coord2D)) return false;
        Coord2D coord2D = (Coord2D) o;
        return x == coord2D.getX() &&
                y == coord2D.getY();
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coord2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
