package model.effects;

import model.entities.players.Player;

public class UpLifeEffect extends PlayerEffect {
    public UpLifeEffect(int duree) {
        super(duree);
    }

    @Override
    public void apply(Player p) {
        p.upLife();
    }
}
