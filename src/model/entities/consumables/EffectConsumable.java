package model.entities.consumables;

public abstract class EffectConsumable extends Consumable {

    protected int duree;

    public EffectConsumable(int nbPoints) {
        super(nbPoints);
    }

    public abstract void applyEffect();
}
