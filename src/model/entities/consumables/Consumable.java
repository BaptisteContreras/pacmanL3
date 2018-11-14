package model.entities.consumables;

public abstract class Consumable {

    protected int nbPoints;

    public Consumable(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }
}
