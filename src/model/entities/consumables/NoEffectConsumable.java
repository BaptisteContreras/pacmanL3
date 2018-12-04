package model.entities.consumables;

public abstract class NoEffectConsumable extends Consumable implements GoodConsumable {


    public NoEffectConsumable(String skin, int nbPoints) {
        super(skin, nbPoints);
    }

    @Override
    public boolean isNegativeEffect() {
        if (nbPoints < 0)
            return true;
        return false;
    }
}
