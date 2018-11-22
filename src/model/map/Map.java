package model.map;

import model.Grid;
import model.GridBuilder;
import model.coordonates.Coord;
import model.effects.Effect;
import model.entities.cells.Cell;
import model.entities.consumables.Consumable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Map implements Serializable {



    protected int nbConso;

    protected List<Coord> pacmanSpawn;

    protected List<Coord> ghostSpawn;

    private java.util.Map<String,Effect> customMapEffect;

    protected int width;

    protected int height;

    protected GridBuilder gridBuilder;

    public Map(int width, int height) {
        this.nbConso = 0;
        this.width = width;
        this.height = height;
        pacmanSpawn = new ArrayList<>();
        ghostSpawn = new ArrayList<>();
        customMapEffect = new HashMap<>();



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
        return pacmanSpawn;
    }

    public void setPacmanSpawn(List<Coord> pacmanSpawn) {
        this.pacmanSpawn = pacmanSpawn;
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

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
