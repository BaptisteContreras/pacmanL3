package model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import model.coordonates.Coord;
import model.coordonates.Coord2D;
import model.entities.cells.Cell;
import model.entities.cells.Corridor;
import model.entities.cells.Wall;
import model.entities.characters.Ghost;
import model.entities.characters.PacMan;
import model.entities.consumables.PacGomme;
import model.entities.players.AIPlayer;
import model.entities.players.HumanPlayer;
import model.entities.players.Player;

import java.util.ArrayList;
import java.util.List;

public class JeuModel extends java.util.Observable {

    private Grid grid;
    protected List<Player> playerList;
    private int nbConsombaleLeft;
    private boolean finish;
    private Coord spawnGhost;

    public JeuModel() {
        finish = false;
        playerList = new ArrayList<>();
    }

    public void mainTurn(){
        // TODO lancer les ghost dans des thread mais pour Version 1 pas besoin
        // TODO les effets sont pour le moment infini
        while (!finish){
            for (Player p : playerList){
                if (p.getCharacter().isAlive()){
                    grid.applyMove(p);
                    grid.eatConsumable(p);
                }else{
                    p.getCharacter().setRespawnTime(p.getCharacter().getRespawnTime()-1);
                    if (p.getCharacter().getRespawnTime() <= 0){
                        p.getCharacter().isAlive();
                        grid.respawn(p,spawnGhost);
                    }
                }
            }
            setChanged();
            notifyObservers();
            finish = gameFinished();
            // notify view
        }
        // TODO retourner resultat partie et notifier vue

    }

    public void init(String map){


    }

    public void initV1(int w, int h, int conso, Coord2D spwan, String pseudo){
        Cell[][] tmp = new Cell[5][10];
        // Ligne 1
        tmp[0][0] = new Wall(null);
        tmp[0][1] = new Wall(null);
        tmp[0][2] = new Wall(null);
        tmp[0][3] = new Wall(null);
        tmp[0][4] = new Wall(null);
        tmp[0][5] = new Wall(null);
        tmp[0][6] = new Corridor(null,new PacGomme(5));
        tmp[0][7] = new Wall(null);
        tmp[0][8] = new Wall(null);
        tmp[0][9] = new Wall(null);
        // Ligne 2
        tmp[1][0] = new Wall(null);
        tmp[1][1] = new Corridor(null,new PacGomme(5));
        tmp[1][2] = new Wall(null);
        tmp[1][3] = new Corridor(null,new PacGomme(5));
        tmp[1][4] = new Corridor(null,new PacGomme(5));
        tmp[1][5] = new Corridor(null,new PacGomme(5));
        tmp[1][6] = new Corridor(null,new PacGomme(5));
        tmp[1][7] = new Wall(null);
        tmp[1][8] = new Corridor(null,new PacGomme(5));
        tmp[1][9] = new Corridor(null,new PacGomme(5));
        // Ligne 3
        tmp[2][0] = new Wall(null);
        tmp[2][1] = new Corridor(null,new PacGomme(5));
        tmp[2][2] = new Wall(null);
        tmp[2][3] = new Corridor(null,new PacGomme(5));
        tmp[2][4] = new Corridor(null,new PacGomme(5));
        tmp[2][5] = new Corridor(null,new PacGomme(5));
        tmp[2][6] = new Corridor(null,new PacGomme(5));
        tmp[2][7] = new Wall(null);
        tmp[2][8] = new Corridor(null,new PacGomme(5));
        tmp[2][9] = new Corridor(null,new PacGomme(5));
        // Ligne 4
        tmp[3][0] = new Wall(null);
        tmp[3][1] = new Corridor(null,new PacGomme(5));
        tmp[3][2] = new Wall(null);
        tmp[3][3] = new Corridor(null,new PacGomme(5));
        tmp[3][4] = new Corridor(null,new PacGomme(5));
        tmp[3][5] = new Corridor(null,new PacGomme(5));
        tmp[3][6] = new Corridor(null,new PacGomme(5));
        tmp[3][7] = new Wall(null);
        tmp[3][8] = new Corridor(null,new PacGomme(5));
        tmp[3][9] = new Corridor(null,new PacGomme(5));


        // Ligne 5
        tmp[4][0] = new Wall(null);
        tmp[4][1] = new Wall(null);
        tmp[4][2] = new Wall(null);
        tmp[4][3] = new Wall(null);
        tmp[4][4] = new Wall(null);
        tmp[4][5] = new Wall(null);
        tmp[4][6] = new Corridor(null,new PacGomme(5));
        tmp[4][7] = new Wall(null);
        tmp[4][8] = new Wall(null);
        tmp[4][9] = new Wall(null);


        grid = new Grid(tmp);
        nbConsombaleLeft = conso;
        spawnGhost = spwan;
        playerList.add(new HumanPlayer(new PacMan(Direction.TOP,5,true,false,0),pseudo));
        playerList.add(new HumanPlayer(new Ghost(Direction.TOP,5,true,false,0,10),pseudo));

    }

    public boolean gameFinished(){
        if (nbConsombaleLeft == 0 || grid.hasCollision() )
            return true;
        return false;
    }

    public void setDirection(Player player, Direction direction){
        player.setDirection(direction);
    }





}
