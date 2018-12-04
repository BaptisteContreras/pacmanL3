package model.effects;

import model.entities.players.Player;

public class NeutralEffect extends Effect {
    public NeutralEffect(int duree) {
        super(duree);
    }

    @Override
    public void apply(Player p) {
        // DO NOTHING
    }
}
