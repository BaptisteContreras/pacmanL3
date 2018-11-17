package model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import model.coordonates.Coord;
import model.entities.players.Player;

import java.util.List;

public class JeuModel implements Observable {

    private Grid grid;
    protected List<Player> playerList;
    private int nbConsombaleLeft;
    private boolean finish;
    private Coord spawnGhost;

    public JeuModel() {
        finish = false;
    }

    public void mainTurn(){
        // TODO lancer les ghost dans des thread mais pour Version 1 pas besoin
        while (!finish){
            for (Player p : playerList){
                if (p.getCharacter().isAlive()){
                    grid.applyMove(p);
                    grid.eatConsumable(p);
                }else{
                    p.getCharacter().setRespawnTime(p.getCharacter().getRespawnTime()-1);
                    if (p.getCharacter().getRespawnTime() == 0){
                        p.getCharacter().isAlive();
                        grid.respawn(p,spawnGhost);
                    }
                }
            }
            finish = gameFinished();
            // notify view
        }
        // TODO retourner resultat partie et notifier vue

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
