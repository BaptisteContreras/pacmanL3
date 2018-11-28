package model.map;

import model.Grid;
import model.GridBuilder;
import model.MetaData;
import model.TypeMap;
import model.coordonates.Coord;
import model.effects.Effect;
import model.entities.cells.Cell;
import model.entities.consumables.Consumable;
import model.entities.players.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public abstract class Map implements Serializable {



    protected int nbConso;

    protected List<Coord> humanSpawn;

    protected List<Coord> ghostSpawn;

    protected MetaData metaData;

    private java.util.Map<String,Effect> customMapEffect;

    protected int width;

    protected int height;

    protected GridBuilder gridBuilder;

    protected List<Player> players;

    public Map(int width, int height) {
        this.nbConso = 0;
        this.width = width;
        this.height = height;
        humanSpawn = new ArrayList<>();
        ghostSpawn = new ArrayList<>();
        customMapEffect = new HashMap<>();
        players = new ArrayList<>();
        metaData = new MetaData("new01",1.0,TypeMap.MULTI,true);



    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void addPlayer(Coord coord, Player p){
        for (Coord c:humanSpawn){
            if (c.equals(coord))
                return;
        }
        if (gridBuilder.addEnnemy(coord,p))
            players.add(p);
    }


    public Coord getPlayerCoord(Player p){
        return  gridBuilder.getPlayerCoord(p);
    }
    public void addEffect(String name, Effect effect){customMapEffect.put(name,effect);}
    public Effect getEffect(String name){return customMapEffect.get(name);}

    public java.util.Map<String, Effect> getCustomMapEffect() {
        return customMapEffect;
    }

    public void setRespawnTime(int time){
        gridBuilder.setRespawnTime(time);
    }
    public void setEffect(Coord coord, Effect effect){
        gridBuilder.setEffect(coord,effect);
    }
    public  void addConsumable(Coord coord,Consumable cons){
        gridBuilder.addConsumable(coord,cons);
    }
    public  void replace(Coord coord, Cell cell){
        gridBuilder.replace(coord,cell);
    }
    public void reWall(Coord coord){
        gridBuilder.reWall(coord);
    }

    public void resizeMap(int width,int height){
        gridBuilder.rebuild(width,height);
        humanSpawn.clear();
        gridBuilder.clearPlayers();
        this.width = width;
        this.height = height;
    }

    public Grid getGrid(){
        return gridBuilder.getGrid();
    }

    public void replaceGrid(Cell[][] newGrid){
        gridBuilder.replaceGridBy(newGrid);
    }



    public int getNbConso() {
        return nbConso;
    }

    public void setNbConso(int nbConso) {
        this.nbConso = nbConso;
    }

    public List<Coord> getPacmanSpawn() {
        return humanSpawn;
    }

    public void setPacmanSpawn(List<Coord> pacmanSpawn) {
        this.humanSpawn = pacmanSpawn;
    }

    public List<Coord> getGhostSpawn() {
        return ghostSpawn;
    }

    public void setGhostSpawn(List<Coord> ghostSpawn) {
        this.ghostSpawn = ghostSpawn;
    }

    public int getWidth() {
        return width;
    }

    public List<Coord> getHumanSpawn() {
        return humanSpawn;
    }

    public java.util.Map<Player,Coord> getAllCoord(){
        return gridBuilder.getAllCoord();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Map{" +
                "nbConso=" + nbConso +
                ", pacmanSpawn=" + humanSpawn +
                ", ghostSpawn=" + ghostSpawn +
                ", customMapEffect=" + customMapEffect +
                ", width=" + width +
                ", height=" + height +
                ", gridBuilder=" + gridBuilder +
                '}';
    }

    public void addHumanSpawn(Coord coord) {
        for (Coord c:humanSpawn){
            if (c.equals(coord))
                return;
        }
        if (gridBuilder.checkIfEmpty(coord))
            humanSpawn.add(coord);
    }

    public void deletePlayer(Coord coord) {
        Player p = gridBuilder.getPlayerWithCoord(coord);
        if (p != null){
            gridBuilder.deletePlayer(p);
            players.remove(p);
        }
    }
    public void deleteSpawn(Coord coord) {
        humanSpawn.remove(coord);
    }


    public void initGridToPlay(Player p1){
        Coord p1Spawn = getRealHumanSpawn();
        gridBuilder.initPosPlayers(p1,p1Spawn);

    }

    public Coord getRealHumanSpawn(){
        Random r = new Random();
        System.out.println(humanSpawn.size());
        int ind = r.nextInt((humanSpawn.size()));
        return  humanSpawn.get(ind);
    }
    public Coord realGhostSpawn(){
        Random r = new Random();
        int ind = r.nextInt((players.size()-1));
        return gridBuilder.getGrid().getPlayersCoord().get(players.get(ind));
    }
}
