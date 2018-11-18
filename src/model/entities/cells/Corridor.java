package model.entities.cells;

import model.coordonates.Coord;
import model.entities.characters.Character;
import model.entities.consumables.Consumable;

import java.util.ArrayList;
import java.util.List;

public class Corridor extends Cell {

    private Consumable consumable;
    private List<Character> persos;

    public Corridor(Coord c, Consumable consumable) {
        super("/assets/game/corridor.jpg", c);
        this.consumable = consumable;
        persos = new ArrayList<>();
    }

    public Consumable getConsumable() {
        return consumable;
    }

    public List<Character> getPersos() {
        return persos;
    }
    public void addCharacter(Character c){

    }
    public void delCharacter(Character c){

    }

    public void setConsumable(Consumable consumable) {
        this.consumable = consumable;
    }
}
