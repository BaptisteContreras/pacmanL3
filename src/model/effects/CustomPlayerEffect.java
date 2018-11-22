package model.effects;

import model.entities.players.Player;

import java.util.List;
import java.util.Map;

public class CustomPlayerEffect extends PlayerEffect {
    private Map<Class,Integer> toapply;
    public CustomPlayerEffect(Map<Class,Integer> effets, int duree) {
        super(duree);
        toapply = effets;
    }

    @Override
    public void apply(Player p) {

    }
}
