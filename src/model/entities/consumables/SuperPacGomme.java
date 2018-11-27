package model.entities.consumables;

import model.effects.Effect;
import model.entities.players.Player;

public class SuperPacGomme extends EffectConsumable implements GoodConsumable {


    public SuperPacGomme(int nbPoints, Effect e) {
        super("/assets/game/superpacgum.jpg",nbPoints,e);
    }

    @Override
    public void applyEffect(Player p) {
        effect.apply(p);
        //p.getCharacter().addEffect(effect);
    }

    @Override
    public String getSkin() {
        if (!super.isNegativeEffect()){
            return super.getSkin();
        }
        return "/assets/game/superbadgum.jpg";
    }
}
