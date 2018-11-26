package view.components;

import model.effects.Effect;

public class CustomReturn {

    private String name;
    private Effect effect;

    public CustomReturn(String name, Effect effect) {
        this.name = name;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public Effect getEffect() {
        return effect;
    }

    @Override
    public String toString() {
        return "CustomReturn{" +
                "name='" + name + '\'' +
                ", effect=" + effect +
                '}';
    }
}
