package model.effects;

import model.entities.players.Player;

public class LooseLifeEffect extends PlayerEffect {
    public LooseLifeEffect(int duree) {
        super(duree);
    }

    public LooseLifeEffect() {
        super(1);
    }

    @Override
    public void apply(Player p) {
        p.getCharacter().looseLife();
    }
}
