package model.entities.consumables;

public abstract class NoEffectConsumable extends Consumable implements GoodConsumable {


    public NoEffectConsumable(String skin, int nbPoints) {
        super(skin, nbPoints);
    }
}
