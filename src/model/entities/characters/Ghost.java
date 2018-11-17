package model.entities.characters;

import model.Direction;

public class Ghost extends Ennemy {


    public Ghost(String skin, Direction direction, int speed, boolean alive, boolean invulnerability, int respawnTime, int nbPoints) {
        super(skin, direction, speed, alive, invulnerability, respawnTime, nbPoints);
    }
}
