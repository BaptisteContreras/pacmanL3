package model.effects;

import model.JeuModel;

public abstract class GameEffect extends Effect {

    protected JeuModel model;
    public GameEffect(int duree, JeuModel m) {
        super(duree);
        model = m;
    }
}
