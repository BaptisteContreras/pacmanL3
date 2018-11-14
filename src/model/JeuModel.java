package model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import model.entities.players.Player;

import java.util.List;

public class JeuModel implements Observable {

    private Grid grid;
    protected List<Player> playerList;

    public void mainTurn(){

    }

    public boolean gameFinished(){
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
