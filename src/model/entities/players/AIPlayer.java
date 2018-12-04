package model.entities.players;

import model.Direction;
import model.JeuModel;
import model.entities.characters.Character;
import model.entities.players.AI.AIBehaviour;
import model.entities.players.AI.FollowBehaviour;
import model.entities.players.AI.RandomCourseBehaviour;

import java.util.Observable;
import java.util.Observer;

public class AIPlayer extends Player implements Observer {

    protected JeuModel model;

    protected AIBehaviour behaviour;

    public AIPlayer(Character character) {
        super(character);
        this.behaviour = new RandomCourseBehaviour();
    }

    public void initAI(JeuModel model){
        this.model = model;
        this.model.addObserver(this);
    }

    public JeuModel getModel() {
        return model;
    }

    public void setModel(JeuModel model) {
        this.model = model;
    }

    public AIBehaviour getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(AIBehaviour behaviour) {
        System.out.println("le comportement |" + this.behaviour.getClass().getName() + "| à été attribué à l'IA");
        this.behaviour = behaviour;
    }

    public Direction changeBehaviourNextMove(){
        return this.behaviour.getBehaviourMove(this.model, this);
    }


    //TODO engregistre temps nomMap
    //boit dialogue fini ->boite dialogue scores
    //
    @Override
    public void update(Observable o, Object arg) {
        //this.setModel((JeuModel) o);
        this.character.setDirection(this.changeBehaviourNextMove());
    }
}
