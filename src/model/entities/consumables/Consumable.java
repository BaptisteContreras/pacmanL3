package model.entities.consumables;

import model.entities.Entity;

public abstract class Consumable extends Entity {

    protected int nbPoints;

    public Consumable(String skin, int nbPoints) {
        super(skin);
        this.nbPoints = nbPoints;
    }


    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }
}
