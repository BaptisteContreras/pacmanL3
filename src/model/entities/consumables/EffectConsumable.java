package model.entities.consumables;

import model.effects.Effect;
import model.entities.players.Player;

public abstract class EffectConsumable extends Consumable {

    protected int duree;
    protected Effect effect;

    public EffectConsumable(String skin, int nbPoints, Effect e) {
        super(skin, nbPoints);
        effect = e;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public abstract void applyEffect(Player p);
}
