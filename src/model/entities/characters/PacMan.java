package model.entities.characters;

import model.Direction;

public class PacMan extends Character {
    public PacMan(Direction direction, int speed, boolean alive, boolean invulnerability, int respawnTime) {
        super("/assets/game/pacman.png", direction, speed, alive, invulnerability, respawnTime);
    }

    @Override
    public String getSkin(){
        switch (direction){
            case BOTTOM: return "/assets/game/pacman_down.png";
            case RIGHT: return "/assets/game/pacman.png";
            case TOP: return "/assets/game/pacman_top.png";
            case LEFT: return "/assets/game/pacman_left.png";
            default: return skin;
        }
    }
}
