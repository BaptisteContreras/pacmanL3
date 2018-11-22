package model;

import model.coordonates.Coord;
import model.coordonates.Coord2D;
import model.effects.Effect;
import model.entities.cells.Cell;
import model.entities.cells.Corridor;
import model.entities.cells.Wall;
import model.entities.consumables.Consumable;
import model.entities.consumables.EffectConsumable;

public class Grid2dBuilder extends GridBuilder {

    public Grid2dBuilder(int width, int height) {
        super(width, height);
        Cell[][] tmp = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tmp[i][j] = new Wall(null);
            }
        }
        grid = new Grid(tmp);
    }

    @Override
    public void replaceGridBy(Cell[][] newGrid) {
        grid = new Grid(newGrid);
    }

    @Override
    public void setRespawnTime(int time) {
        grid.setRespawnTime(time);
    }

    @Override
    public void setEffect(Coord coord, Effect effect) {
        Coord2D coordCorridor = (Coord2D) coord;
        if (grid.getGrille()[coordCorridor.getY()][coordCorridor.getX()] instanceof Corridor){
           Consumable conso = ((Corridor)grid.getGrille()[coordCorridor.getY()][coordCorridor.getX()]).getConsumable();
            if (conso instanceof EffectConsumable){
                ((EffectConsumable) conso).setEffect(effect);
            }
        }
    }

    @Override
    public void addConsumable(Coord coord, Consumable cons) {
        Coord2D coordCorridor = (Coord2D) coord;
        if (grid.getGrille()[coordCorridor.getY()][coordCorridor.getX()] instanceof Corridor){
            ((Corridor)grid.getGrille()[coordCorridor.getY()][coordCorridor.getX()]).setConsumable(cons);
        }

    }

    @Override
    public void replace(Coord coord, Cell cell) {
        Coord2D coordReplace = (Coord2D) coord;
        grid.getGrille()[coordReplace.getY()][coordReplace.getX()] = cell;

    }

    @Override
    public void reWall(Coord coord) {
        replace(coord,new Wall(null));
    }



    @Override
    public void rebuild(int width, int height) {
        Cell[][] tmp = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tmp[i][j] = new Wall(null);
            }
        }
        grid = new Grid(tmp);
    }
}
