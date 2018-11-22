package model.entities.players;

import model.Direction;
import model.Score;
import model.entities.characters.Character;

public abstract class Player {

    protected Character character;
    protected Score score;

    public Player(Character character) {
        this.character = character;
        score = new Score();
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

    public Direction getMove(){return getDirection();}

    public boolean isAffectedBy(Class c){
        return character.hasEffect(c);
    }

    public void upScore(int val){
        if (character.hasDoublePoint())
            val *= 2;
        score.up(val);
    }

    public int getScore(){
        return score.getScore();
    }

    public void decreaseEffect(){
        character.dicreaseEffectDuration();
    }
}
