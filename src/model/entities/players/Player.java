package model.entities.players;

import model.Direction;
import model.entities.characters.Character;

public abstract class Player {

    protected Character character;

    public Player(Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void setDirection(Direction direction){
        character.setDirection(direction);
    }

    public Direction getDirection (){
        return character.getDirection();
    }
}
