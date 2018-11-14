package model.entities.cells;

import model.coordonates.Coord;
import model.entities.consumables.Consumable;

public class Corridor extends Cell {

    private Consumable consumable;

    public Corridor(String skin, Coord c, Consumable consumable) {
        super(skin, c);
        this.consumable = consumable;
    }

    public Consumable getConsumable() {
        return consumable;
    }

    public void setConsumable(Consumable consumable) {
        this.consumable = consumable;
    }
}
