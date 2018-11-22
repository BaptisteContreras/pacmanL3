package model.entities.consumables;

import model.effects.Effect;
import model.entities.players.Player;

public class SuperPacGomme extends EffectConsumable {


    public SuperPacGomme(int nbPoints, Effect e) {
        super("/assets/game/superpacgum.png",nbPoints,e);
    }

    @Override
    public void applyEffect(Player p) {
        p.getCharacter().addEffect(effect);
    }
}
