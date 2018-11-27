package model.map;

import model.Direction;
import model.Grid;
import model.coordonates.Coord;
import model.effects.*;
import model.entities.cells.Cell;
import model.entities.cells.Corridor;
import model.entities.characters.Ghost;
import model.entities.consumables.Consumable;
import model.entities.consumables.EffectConsumable;
import model.entities.consumables.PacGomme;
import model.entities.consumables.SuperPacGomme;
import model.entities.players.AIPlayer;
import model.entities.players.Player;

import java.io.IOException;
import java.util.List;

public  abstract class MapBuilder {

    protected Map map;
    protected MapLoader loader;
    protected int ptsPacGum;
    protected int ptsSuper;
    protected int ptsGhost;
    protected Direction ghostDirection;


    public MapBuilder(Map map) {
        this.map = map;
        ptsPacGum = 5;
        ptsSuper = 10;
        ptsGhost = 20;
        ghostDirection = Direction.RIGHT;
        loader = new MapLoader("/assets/map");
    }

    public void addEnnemy(Coord coord){
        map.addPlayer(coord,new AIPlayer(new Ghost(ghostDirection,5,true,false,0,ptsGhost)));
    }
    public void addPlayer(Coord coord, Player ennemy){
        map.addPlayer(coord,ennemy);
    }
    public Coord getPlayerCoord(Player player){
        return map.getPlayerCoord(player);
    }

    public void addHumanSpawn(Coord coord){
        map.addHumanSpawn(coord);
    }

    public java.util.Map<Player,Coord> getAllCoord(){
        return map.getAllCoord();
    }

    public List<Player> getPlayers(){
        return map.getPlayers();
    }

    public void deletePlayer(Coord coord){
        map.deletePlayer(coord);
    }

    public void deleteSpawn(Coord coord){
        map.deleteSpawn(coord);
    }

    public List<Coord> getSpawn(){
        return map.getHumanSpawn();
    }



    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void resize(int width, int height){
        map.resizeMap(width,height);
    }
    public void rewall(Coord coord){
        map.deletePlayer(coord);
        map.deleteSpawn(coord);
        map.reWall(coord);}
    public void replaceByCorridor(Coord coord){
        map.deletePlayer(coord);
        map.deleteSpawn(coord);
        map.replace(coord,new Corridor(null,null));
    }
    public void replace(Coord coord, Cell c){
        map.deletePlayer(coord);
        map.deleteSpawn(coord);
        map.replace(coord,c);
    }
    public void addConsumable(Coord coord, Consumable consumable){
        map.addConsumable(coord,consumable);
    }
    public void addPacGum(Coord coord){
        map.addConsumable(coord,new PacGomme(ptsPacGum));
    }

    public void addSuperPacgum(Coord coord, Effect effect){
        map.addConsumable(coord,new SuperPacGomme(ptsSuper,effect));
    }
    public void addEffect(String name, Effect effect){
        map.addEffect(name,effect);
    }

    public java.util.Map<String, Effect> getEffects(){
        return map.getCustomMapEffect();
    }

    public void registerBasicEffect(){
        map.addEffect("Invulnérabilité",new Invulnerability(20));
        map.addEffect("Double Point",new DoublePoint(30));
        map.addEffect("Vie bonus",new UpLifeEffect(30));
        map.addEffect("Vie malus",new LooseLifeEffect(30));
    }

    public int getPtsPacGum() {
        return ptsPacGum;
    }

    public void setPtsPacGum(int ptsPacGum) {
        this.ptsPacGum = ptsPacGum;
    }

    public int getPtsSuper() {
        return ptsSuper;
    }

    public boolean isMapNameValide(String name){
        return loader.isMapExist(name);
    }

    public void setPtsSuper(int ptsSuper) {
        this.ptsSuper = ptsSuper;
    }

    @Override
    public String toString() {
        return "MapBuilder{" +
                "map=" + map +
                '}';
    }

    public int getPtsGhost() {
        return ptsGhost;
    }

    public void setPtsGhost(int ptsGhost) {
        this.ptsGhost = ptsGhost;
    }

    public Direction getGhostDirection() {
        return ghostDirection;
    }

    public void setGhostDirection(Direction ghostDirection) {
        this.ghostDirection = ghostDirection;
    }

    public Grid getTab() {
        return map.getGrid();
    }

    public boolean saveMap(String name){
        try {
            return loader.saveMap(map,name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> getMaps() {
        return loader.getMapList();
    }

    public boolean loadMap(String name) {
        try {
            Map newMap =  loader.loadMap(name);
            if (newMap != null){
                map = newMap;
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


}
