package model.effects;

import model.Score;
import model.entities.players.Player;

public class ResetPointEffect extends PlayerEffect implements BadEffect {
    public ResetPointEffect(int duree) {
        super(duree);
    }

    @Override
    public void apply(Player p) {
        p.resetScore();
    }
}
