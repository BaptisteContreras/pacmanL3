package model.map;

import model.Grid;
import model.coordonates.Coord;
import model.effects.*;
import model.entities.cells.Cell;
import model.entities.cells.Corridor;
import model.entities.consumables.Consumable;
import model.entities.consumables.PacGomme;

public  abstract class MapBuilder {

    protected Map map;
    protected MapLoader loader;
    protected int ptsPacGum;

    public MapBuilder(Map map) {
        this.map = map;
        ptsPacGum = 5;
        loader = new MapLoader();
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
    public void rewall(Coord coord){map.reWall(coord);}
    public void replaceByCorridor(Coord coord){map.replace(coord,new Corridor(null,null));}
    public void replace(Coord coord, Cell c){map.replace(coord,c);}
    public void addConsumable(Coord coord, Consumable consumable){
        map.addConsumable(coord,consumable);
    }
    public void addPacGum(Coord coord){
        map.addConsumable(coord,new PacGomme(ptsPacGum));
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

    @Override
    public String toString() {
        return "MapBuilder{" +
                "map=" + map +
                '}';
    }

    public Grid getTab() {
        return map.getGrid();
    }
}
