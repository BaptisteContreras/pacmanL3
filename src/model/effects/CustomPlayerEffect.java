package model.effects;

import model.entities.players.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class CustomPlayerEffect extends PlayerEffect {
    private List<PlayerEffect> toapply;
    public CustomPlayerEffect(List<PlayerEffect> effets, int duree) {
        super(duree);
        toapply = effets;
    }

    @Override
    public boolean bad() {
        for (Effect e:toapply){
            if (e.bad())
                return true;
        }
        return false;
    }

    @Override
    public void apply(Player p) {
        for (PlayerEffect eff:toapply) {
            eff.apply(p);

        }

    }

    public List<PlayerEffect> getToapply() {
        return toapply;
    }

    @Override
    public String toString() {
        return "CustomPlayerEffect{" +
                "toapply=" + toapply +
                ", duree=" + duree +
                ", time=" + time +
                '}';
    }
}
