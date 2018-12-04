package model.entities;

import java.io.Serializable;

public abstract class Entity implements Serializable {

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
