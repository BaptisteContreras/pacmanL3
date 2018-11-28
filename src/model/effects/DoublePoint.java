package model.effects;

import model.entities.players.Player;

public class DoublePoint extends PlayerEffect {
    public DoublePoint(int duree) {
        super(duree);
    }

    @Override
    public void apply(Player p) {
        try {
            p.getCharacter().addEffect((Effect) this.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "DoublePoint{" +
                "duree=" + duree +
                ", time=" + time +
                '}';
    }
}
