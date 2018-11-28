package model.grid;

import model.coordonates.Coord;
import model.effects.Effect;
import model.entities.cells.Cell;
import model.entities.consumables.Consumable;
import model.entities.players.Player;

import java.io.Serializable;
import java.util.Map;

public abstract class GridBuilder implements Serializable {

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
    public abstract boolean addConsumable(Coord coord,Consumable cons);
    public abstract boolean replace(Coord coord, Cell cell);
    public abstract boolean reWall(Coord coord);
    public abstract boolean addEnnemy(Coord coord, Player ennemy);

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

    public Coord getPlayerCoord(Player p) {
        return grid.getPlayersCoord().get(p);
    }

    public  abstract  boolean checkIfEmpty(Coord coord);

    public Map<Player,Coord> getAllCoord() {
        return grid.getPlayersCoord();
    }

    public abstract Player getPlayerWithCoord(Coord coord);

    public void deletePlayer(Player p) {
        grid.getPlayersCoord().remove(p);
    }

    public void clearPlayers() {
        grid.getPlayersCoord().clear();
    }

    public abstract void initPosPlayers(Player p1, Coord p1Spawn);

    public abstract boolean hadConsu(Coord coord);
}
