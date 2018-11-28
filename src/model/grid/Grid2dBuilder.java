package model.grid;

import model.coordonates.Coord;
import model.coordonates.Coord2D;
import model.effects.Effect;
import model.entities.cells.Cell;
import model.entities.cells.Corridor;
import model.entities.cells.Wall;
import model.entities.consumables.Consumable;
import model.entities.consumables.EffectConsumable;
import model.entities.players.Player;

import java.util.Map;

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
    public boolean addConsumable(Coord coord, Consumable cons) {
        Coord2D coordCorridor = (Coord2D) coord;
        if (grid.getGrille()[coordCorridor.getY()][coordCorridor.getX()] instanceof Corridor){
            ((Corridor)grid.getGrille()[coordCorridor.getY()][coordCorridor.getX()]).setConsumable(cons);
            return true;
        }

        return false;

    }

    @Override
    public boolean replace(Coord coord, Cell cell) {
        Coord2D coordReplace = (Coord2D) coord;
        boolean hadConsu = false;
        if (grid.getGrille()[coordReplace.getY()][coordReplace.getX()] instanceof Corridor){
            if (((Corridor)grid.getGrille()[coordReplace.getY()][coordReplace.getX()]).getConsumable() != null)
                hadConsu = true;
        }
        grid.getGrille()[coordReplace.getY()][coordReplace.getX()] = cell;
        return hadConsu;

    }

    @Override
    public boolean reWall(Coord coord) {
        return replace(coord,new Wall(null));
    }

    @Override
    public boolean addEnnemy(Coord coord, Player ennemy) {
        Coord2D c2d = (Coord2D) coord;
        if (grid.getGrille()[c2d.getY()][c2d.getX()] instanceof Corridor){
            if (checkIfEmpty(coord)){
                grid.setPlayerCoord(ennemy,coord);
                return true;
            }
        }
        return false;
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

    @Override
    public boolean checkIfEmpty(Coord coord) {
        Coord2D c2d = (Coord2D) coord;
        if (!(grid.getGrille()[c2d.getY()][c2d.getX()] instanceof Corridor))
            return false;
        for (Map.Entry<Player,Coord> entry:grid.getPlayersCoord().entrySet()){
            if (((Coord2D)entry.getValue()).equals(c2d)){
                System.out.println("Spawn already set");
                return false;
            }
        }

        return true;
    }

    @Override
    public Player getPlayerWithCoord(Coord coord) {
       for (Map.Entry<Player,Coord> entry: grid.getPlayersCoord().entrySet()){
           if (((Coord2D)entry.getValue()).equals(coord))
               return entry.getKey();
       }
       return null;
    }

    @Override
    public void initPosPlayers(Player p1, Coord p1Spawn) {
        grid.getPlayersCoord().put(p1,p1Spawn);
        for (Map.Entry<Player,Coord> entry:grid.getPlayersCoord().entrySet()){
            Coord2D coord = (Coord2D) entry.getValue();
           // System.out.println("during init : " + coord);
            Cell tmp = grid.getGrille()[coord.getY()][coord.getX()];
            if (tmp instanceof Corridor){
                Corridor corridor = (Corridor) tmp;
                corridor.addCharacter(entry.getKey().getCharacter());
            }
        }
    }

    @Override
    public boolean hadConsu(Coord coord) {
        Coord2D c2d = (Coord2D) coord;
        if ((grid.getGrille()[c2d.getY()][c2d.getX()] instanceof Corridor)){
             return ((Corridor)grid.getGrille()[c2d.getY()][c2d.getX()]).getConsumable() != null;
        }
        return false;
    }
}
