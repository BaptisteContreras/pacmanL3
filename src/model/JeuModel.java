package model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import model.entities.players.Player;

import java.util.List;

public class JeuModel implements Observable {

    private Grid grid;
    protected List<Player> playerList;
    private int nbConsombaleLeft;

    public JeuModel() {
    }

    public void mainTurn(){
        for (Player p : playerList){
            grid.applyMove(p);
            grid.eatConsumable(p);
        }
    }

    public void init(String map){

    }

    public boolean gameFinished(){
        if (nbConsombaleLeft == 0 || grid.hasCollision() )
            return true;
        return false;
    }

    public void setDirection(Player player, Direction direction){
        player.setDirection(direction);
    }




    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }
}
