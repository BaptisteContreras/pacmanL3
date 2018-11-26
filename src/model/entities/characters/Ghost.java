package model.entities.characters;

import model.Direction;

public class Ghost extends Ennemy {


    public Ghost(Direction direction, int speed, boolean alive, boolean invulnerability, int respawnTime, int nbPoints) {
        super("/assets/game/ghost.png", direction, speed, alive, invulnerability, respawnTime, nbPoints,999999);
    }
    public Ghost(Direction direction, int speed, boolean alive, boolean invulnerability, int respawnTime, int nbPoints,int lives) {
        super("/assets/game/ghost.png", direction, speed, alive, invulnerability, respawnTime, nbPoints,lives);
    }
}
