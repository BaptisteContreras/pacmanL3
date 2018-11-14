package model.entities.cells;

import model.coordonates.Coord;
import model.entities.Entity;

public abstract class Cell extends Entity {

    protected Coord coordonnee;

    public Cell(String skin, Coord c) {
        super(skin);
        coordonnee =  c;
    }


    public Coord getCoordonnee() {
        return coordonnee;
    }

    public void setCoordonnee(Coord coordonnee) {
        this.coordonnee = coordonnee;
    }
}
