package model.entities.characters;

import model.Direction;
import model.effects.DoublePoint;
import model.effects.Effect;
import model.effects.Invulnerability;
import model.entities.Entity;

import java.util.*;

public abstract class Character extends Entity {

    protected Direction direction;
    protected int speed;
    protected boolean alive;
    protected boolean invulnerability;
    protected int respawnTime;
    protected Map<Effect,Effect> effets;
    protected int life;

    public Character(String skin, Direction direction, int speed, boolean alive, boolean invulnerability, int respawnTime) {
        super(skin);
        life = 8;
        this.direction = direction;
        this.speed = speed;
        this.alive = alive;
        this.invulnerability = invulnerability;
        this.respawnTime = respawnTime;
        effets = new HashMap<>();
    }
    public Character(String skin, Direction direction, int speed, boolean alive, boolean invulnerability, int respawnTime,int lives) {
        super(skin);
        life = 2;
        this.direction = direction;
        this.speed = speed;
        this.alive = alive;
        this.invulnerability = invulnerability;
        this.respawnTime = respawnTime;
        effets = new HashMap<>();
    }

    public int getLife() {
        return life;
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

    public Map<Effect, Effect> getEffets() {
        return effets;
    }

    public void addLife(int nb){
        life+=nb;
    }

    public void upLife(){
        addLife(1);
    }

    public int looseXLife(int nb){
        life -= nb;
        return life;
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

    public int looseLife(){
        life -= 1;
        return  life;
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
