package model.entities.cells;

import model.coordonates.Coord;


public class Wall extends Cell {

    public Wall(Coord c) {
        super("/assets/game/border.jpg", c);
    }
}
