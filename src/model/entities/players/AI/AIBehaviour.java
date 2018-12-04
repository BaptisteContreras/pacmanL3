package model.entities.players.AI;

import model.Direction;
import model.JeuModel;
import model.entities.characters.Character;
import model.entities.players.Player;

public interface AIBehaviour {
    public Direction getBehaviourMove(JeuModel model, Player currentCharacter);
}
