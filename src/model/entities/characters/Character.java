package model.entities.characters;

import model.Direction;
import model.entities.Entity;

public abstract class Character extends Entity {

    protected Direction direction;
    protected int speed;
    protected boolean alive;
    protected boolean invulnerability;
    protected int respawnTime;

    public Character(String skin, Direction direction, int speed, boolean alive, boolean invulnerability, int respawnTime) {
        super(skin);
        this.direction = direction;
        this.speed = speed;
        this.alive = alive;
        this.invulnerability = invulnerability;
        this.respawnTime = respawnTime;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isInvulnerability() {
        return invulnerability;
    }

    public void setInvulnerability(boolean invulnerability) {
        this.invulnerability = invulnerability;
    }

    public int getRespawnTime() {
        return respawnTime;
    }

    public void setRespawnTime(int respawnTime) {
        this.respawnTime = respawnTime;
    }
}
