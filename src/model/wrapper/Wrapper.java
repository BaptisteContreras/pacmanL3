package model.wrapper;

import model.Grid;
import model.coordonates.Coord;
import model.entities.cells.Cell;
import model.entities.characters.Character;
import model.entities.players.Player;

import java.util.Map;

public abstract class Wrapper {

    // renvois move si wrap impossible
    public abstract Coord wrap(Coord move, Cell[][] grille, Character character);
}
