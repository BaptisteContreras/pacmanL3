package model.entities.characters;

import model.Direction;
import model.effects.DoublePoint;
import model.effects.Effect;
import model.effects.Invulnerability;
import model.entities.Entity;
import model.entities.players.Player;

import java.util.*;

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

    public void dicreaseEffectDuration(){
        // TODO supprimer les effets périmés.
        Set keys = effets.keySet();
        Iterator it = keys.iterator();
        while (it.hasNext()){
            Effect effect = (Effect) it.next();
            effect.decremente();

        }
    }

    public boolean hasEffect(Class p){
        Set keys = effets.keySet();
        Iterator it = keys.iterator();
        while (it.hasNext()){
            Effect effect = (Effect) it.next();
            if (effect.getClass().getName().equals(p.getName())){
                if (effect.isActive())
                    return true;
            }
        }
        return false;
    }

    public boolean hasDoublePoint(){
        return hasEffect(DoublePoint.class);
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
        return hasEffect(Invulnerability.class);
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
