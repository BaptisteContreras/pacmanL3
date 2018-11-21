package model.entities.characters;

import model.Direction;
import model.effects.Effect;
import model.entities.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Character extends Entity {

    protected Direction direction;
    protected int speed;
    protected boolean alive;
    protected boolean invulnerability;
    protected int respawnTime;
    protected Map<Effect,Effect> effets;

    public Character(String skin, Direction direction, int speed, boolean alive, boolean invulnerability, int respawnTime) {
        super(skin);
        this.direction = direction;
        this.speed = speed;
        this.alive = alive;
        this.invulnerability = invulnerability;
        this.respawnTime = respawnTime;
        effets = new HashMap<>();
    }

    public void clearEffect(){
        effets.clear();
    }
    public void addEffect(Effect effect){
        effets.put(effect,effect);
    }
    public void removeEffect(Effect effect){
        effets.remove(effect);
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
