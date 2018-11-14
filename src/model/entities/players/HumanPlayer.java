package model.entities.players;

import model.entities.characters.Character;

public class HumanPlayer extends Player {
    private String pseudo;

    public HumanPlayer(Character character, String pseudo) {
        super(character);
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
