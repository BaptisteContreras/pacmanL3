package model.effects;

import model.entities.players.Player;

public abstract class Effect {

    protected int duree;
    protected int time;

    public Effect(int duree) {
        this.duree = duree;
        time = duree;
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
}
