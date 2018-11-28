package model;

import model.coordonates.Coord;
import model.coordonates.Coord2D;
import model.effects.*;
import model.entities.cells.Cell;
import model.entities.cells.Corridor;
import model.entities.cells.Wall;
import model.entities.characters.Ghost;
import model.entities.characters.PacMan;
import model.entities.consumables.PacGomme;
import model.entities.consumables.SuperPacGomme;
import model.entities.players.HumanPlayer;
import model.entities.players.Player;
import model.grid.Grid;
import model.map.Map;
import model.map.MapBuilder;

import java.util.*;

import static java.lang.Thread.sleep;

public class JeuModel extends java.util.Observable {

    private Grid grid;
    protected List<Player> playerList;
    private int nbConsombaleLeft;
    private boolean finish;
    private Coord spawnGhost;
    private boolean start;

    public JeuModel() {
        finish = false;
        start = false;
        playerList = new ArrayList<>();
    }

    public void mainTurn(){
        // TODO lancer les ghost dans des thread mais pour Version 1 pas besoin
        // TODO les effets sont pour le moment infini
        while (!finish){
            for (Player p : playerList){
                if (p.getCharacter().isAlive()){
                    //System.out.println(p+" : "+p.getCharacter().getLife());
                    if (grid.eatConsumable(p))
                        nbConsombaleLeft-=1;
                    grid.applyMove(p);
                    p.decreaseEffect();
                  //  applyOtherEffect(p);
                }else{
                    p.getCharacter().setRespawnTime(p.getCharacter().getRespawnTime()-1);
                    if (p.getCharacter().getRespawnTime() <= 0){
                        p.getCharacter().setAlive(true);
                        grid.respawn(p,spawnGhost);
                    }
                }
            }


            finish = gameFinished();

            setChanged();
            notifyObservers();
           // finish = true;
            try {
                sleep(350);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // notify view
            setChanged();
            notifyObservers();
        }
        System.out.println("finished ! gg");
        // TODO retourner resultat partie et notifier vue
        setChanged();
        notifyObservers();

    }

    private void applyOtherEffect(Player p) {


    }

    public Player getPacMan(){
        for (Player p:playerList){
            if (p.getCharacter() instanceof PacMan)
                return p;
        }
        return null;
    }



    public Cell[][] getCells(){
        return grid.getGrille();
    }

    public void init(MapBuilder builder, String pseudo, String mapName, int conso){
        start = true;
        Player p1 = new HumanPlayer(new PacMan(Direction.BOTTOM,5,true,false,0),pseudo);
        builder.loadMap(mapName);
        Map map = builder.getMap();
        nbConsombaleLeft = map.getNbConso();
        System.out.println("nb conso : "+nbConsombaleLeft);
        map.initGridToPlay(p1);
        spawnGhost = map.realGhostSpawn();
        grid = map.getGrid();
        playerList = map.getPlayers();

        playerList.add(p1);

    }

    public void initV1(int w, int h, int conso, Coord2D spwan, String pseudo){
        start = true;
        Player p1 = new HumanPlayer(new PacMan(Direction.BOTTOM,5,true,false,0),pseudo);
        playerList.add(p1);
        Player p2 =new HumanPlayer(new Ghost(Direction.RIGHT,5,true,false,0,10),"ia");
        playerList.add(p2);


        Cell[][] tmp = new Cell[5][5];
        // Ligne 1
        tmp[0][0] = new Wall(null);
        tmp[0][1] = new Wall(null);
        tmp[0][2] = new Wall(null);
        tmp[0][3] = new Wall(null);
        tmp[0][4] = new Wall(null);

        // Ligne 2
        tmp[1][0] = new Wall(null);
        tmp[1][1] = new Corridor(null,new PacGomme(5));
        tmp[1][2] = new Wall(null);
        tmp[1][3] = new Corridor(null,new PacGomme(5));
        tmp[1][4] = new Corridor(null,new PacGomme(5));
        ((Corridor)tmp[1][3]).addCharacter(playerList.get(0).getCharacter());

        // Ligne 3
        List<PlayerEffect> customs = new ArrayList<>();
        customs.add(new DoublePoint(200));
        customs.add(new Invulnerability(200));
       // customs.add(new UpLifeEffect(200));
        CustomPlayerEffect custom = new CustomPlayerEffect(customs,100);
        tmp[2][0] = new Wall(null);
        tmp[2][1] = new Corridor(null,new PacGomme(5));
        tmp[2][2] = new Corridor(null,new SuperPacGomme(5,custom));
        tmp[2][3] = new Corridor(null,new PacGomme(5));
        tmp[2][4] = new Corridor(null,new PacGomme(5));

        // Ligne 4
        tmp[3][0] = new Wall(null);
        tmp[3][1] = new Corridor(null,new PacGomme(5));
        tmp[3][2] = new Wall(null);
        tmp[3][3] = new Corridor(null,new PacGomme(5));
        ((Corridor)tmp[3][3]).addCharacter(playerList.get(1).getCharacter());
        tmp[3][4] = new Corridor(null,new PacGomme(5));

        // Ligne 5
        tmp[4][0] = new Wall(null);
        tmp[4][1] = new Wall(null);
        tmp[4][2] = new Wall(null);
        tmp[4][3] = new Wall(null);
        tmp[4][4] = new Wall(null);



        grid = new Grid(tmp);
        nbConsombaleLeft = conso;
        spawnGhost = spwan;
        grid.setPlayerCoord(p1,new Coord2D(3,1));
        grid.setPlayerCoord(p2,new Coord2D(3,3));


    }

    public boolean gameFinished(){
        if (nbConsombaleLeft == 0 || grid.hasCollision() || (getPacMan() !=null && getPacMan().getCharacter().getLife() <= 0) )
            return true;
        return false;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public int getNbConsombaleLeft() {
        return nbConsombaleLeft;
    }

    public void setNbConsombaleLeft(int nbConsombaleLeft) {
        this.nbConsombaleLeft = nbConsombaleLeft;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void setDirection(Player player, Direction direction){
        player.setDirection(direction);
    }

    public List<HumanPlayer> getHumanPlayers(){
        List<HumanPlayer> tmp = new ArrayList<>();
        for (Player p:playerList){
            if (p instanceof HumanPlayer){
                tmp.add((HumanPlayer) p);
            }
        }
        return tmp;
    }





}
