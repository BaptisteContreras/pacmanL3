package model.entities.characters;

import model.Direction;

public class PacMan extends Character {
    public PacMan(String skin, Direction direction, int speed, boolean alive, boolean invulnerability, int respawnTime) {
        super(skin, direction, speed, alive, invulnerability, respawnTime);
    }
}
