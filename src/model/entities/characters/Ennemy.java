package model.entities.characters;

import model.Direction;

public abstract class Ennemy extends Character {

    private int nbPoints;

    public Ennemy(String skin, Direction direction, int speed, boolean alive, boolean invulnerability, int respawnTime, int nbPoints) {
        super(skin, direction, speed, alive, invulnerability, respawnTime);
        this.nbPoints = nbPoints;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }
}
