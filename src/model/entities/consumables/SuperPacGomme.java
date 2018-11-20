package model.entities.consumables;

import model.entities.players.Player;

public class SuperPacGomme extends EffectConsumable {


    public SuperPacGomme(int nbPoints) {
        super("/assets/game/superpacgum.png",nbPoints);
    }

    @Override
    public void applyEffect(Player p) {
        p.getCharacter().setInvulnerability(true);
    }
}
