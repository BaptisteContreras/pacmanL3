package model;

import model.coordonates.Coord;
import model.effects.Effect;
import model.entities.cells.Cell;
import model.entities.consumables.Consumable;

public abstract class GridBuilder {

    protected Grid grid;

    protected int width;

    protected int height;

    public GridBuilder(int width, int height) {
        this.width = width;
        this.height = height;

    }
    public abstract void replaceGridBy(Cell[][] newGrid);
    public abstract void setRespawnTime(int time);
    public abstract void setEffect(Coord coord, Effect effect);
    public abstract void addConsumable(Coord coord,Consumable cons);
    public abstract void replace(Coord coord, Cell cell);
    public abstract void reWall(Coord coord);

    public abstract void rebuild(int width, int height);

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
