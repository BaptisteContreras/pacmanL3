package model.effects;

import model.entities.players.Player;

import java.io.Serializable;

public abstract class Effect implements Cloneable, Serializable {

    protected int duree;
    protected int time;

    public Effect(int duree) {
        this.duree = duree;
        time = duree;
    }

    public boolean bad(){
        return this instanceof BadEffect;
    }

    public abstract void apply(Player p);

    public boolean isActive(){
        return time>0;
    }


    public int getDuree() {
        return duree;
    }

    public boolean decremente(){
        time -=1;
        return time <= 0;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
