package model.wrapper;

import model.Grid;
import model.coordonates.Coord;
import model.coordonates.Coord2D;
import model.entities.cells.Cell;
import model.entities.cells.Corridor;
import model.entities.cells.Wall;
import model.entities.characters.Character;
import model.entities.characters.Ennemy;
import model.entities.characters.Ghost;
import model.entities.players.Player;

import java.util.Map;

public class Wrapper2D extends Wrapper {


    @Override
    public Coord wrap(Coord move, Cell[][] grille, Character character) {
        Coord2D m = (Coord2D) move;
        int width = grille[0].length;
        int height = grille.length;
        if (m.getX() < 0){
            Cell next = grille[m.getY()][width-1];
            if (next instanceof Wall)
                return move;
            else if (hasGhost((Corridor)next) && character instanceof Ennemy){
                return move;
            }
            return new Coord2D(width-1,m.getY());
        }else if (m.getX() >= width){
            Cell next = grille[m.getY()][0];
            if (next instanceof Wall)
                return move;
            else if (hasGhost((Corridor)next) && character instanceof Ennemy){
                return move;
            }
            return new Coord2D(0,m.getY());
        }else if (m.getY() < 0){
            Cell next = grille[height-1][m.getX()];
            if (next instanceof Wall)
                return move;
            else if (hasGhost((Corridor)next) && character instanceof Ennemy){
                return move;
            }
            return new Coord2D(m.getX(),height-1);
        }else if (m.getY() >= height){
            Cell next = grille[0][m.getX()];
            if (next instanceof Wall)
                return move;
            else if (hasGhost((Corridor)next) && character instanceof Ennemy){
                return move;
            }
            return new Coord2D(m.getX(),0);
        }

        return move;
    }

    private boolean hasGhost(Corridor c){
        for (Character ch:c.getPersos()){
            if (ch instanceof Ennemy)
                return true;
        }
        return false;
    }
}
