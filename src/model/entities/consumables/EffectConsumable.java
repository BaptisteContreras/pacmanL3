package model.entities.consumables;

import model.entities.players.Player;

public abstract class EffectConsumable extends Consumable {

    protected int duree;

    public EffectConsumable(int nbPoints) {
        super(nbPoints);
    }

    public abstract void applyEffect(Player p);
}
