package model.entities.characters;

import model.Direction;

public class PacMan extends Character {
    public PacMan(Direction direction, int speed, boolean alive, boolean invulnerability, int respawnTime) {
        super("/assets/game/pacman.png", direction, speed, alive, invulnerability, respawnTime);
    }
}
