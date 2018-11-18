package model.entities.consumables;

import model.entities.players.Player;

public abstract class EffectConsumable extends Consumable {

    protected int duree;

    public EffectConsumable(String skin, int nbPoints) {
        super(skin, nbPoints);
    }


    public abstract void applyEffect(Player p);
}
