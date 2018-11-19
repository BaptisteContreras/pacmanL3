package model.entities;

public abstract class Entity {

    protected String skin;


    public Entity(String skin) {
        this.skin = skin;
    }


    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }


}
