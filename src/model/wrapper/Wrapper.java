package model.wrapper;

import model.coordonates.Coord;
import model.entities.cells.Cell;
import model.entities.characters.Character;

import java.io.Serializable;

public abstract class Wrapper implements Serializable {

    // renvois move si wrap impossible
    public abstract Coord wrap(Coord move, Cell[][] grille, Character character);
}
