package model.entities.consumables;

public class PacGomme extends NoEffectConsumable {


    public PacGomme(int nbPoints) {
        super("/assets/game/pacgum2.jpg", nbPoints);
    }


    @Override
    public String getSkin() {
        if (!isNegativeEffect())
            return super.getSkin();
        return "/assets/game/badgum.jpg";
    }
}
