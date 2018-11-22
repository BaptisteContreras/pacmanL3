package model.effects;

import model.entities.players.Player;

public class DoublePoint extends PlayerEffect {
    public DoublePoint(int duree) {
        super(duree);
    }

    @Override
    public void apply(Player p) {
        p.getCharacter().addEffect(this);
    }
}
