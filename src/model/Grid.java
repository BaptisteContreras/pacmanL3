package model;

import model.coordonates.Coord;
import model.coordonates.Coord2D;
import model.entities.cells.Cell;
import model.entities.cells.Corridor;
import model.entities.cells.Wall;
import model.entities.characters.Character;
import model.entities.characters.Ennemy;
import model.entities.characters.Ghost;
import model.entities.characters.PacMan;
import model.entities.consumables.Consumable;
import model.entities.consumables.EffectConsumable;
import model.entities.consumables.GoodConsumable;
import model.entities.players.Player;
import model.wrapper.Wrapper;
import model.wrapper.Wrapper2D;

import java.io.Serializable;
import java.util.*;

public class Grid implements Serializable {

    private Cell[][] grille;
    private Map<Player,Coord> playersCoord;
    private Coord pacmanCoord;
    private int width; // x = j
    private int height; // y = i
    private int respawnTime;
    private Wrapper2D wrapper;


    public Grid(){
        playersCoord = new HashMap<>();
        wrapper = new Wrapper2D();
        respawnTime = 10;
    }


    public Grid(int gridWidth, int gridHeight) {
        this();
        width = gridWidth;
        height = gridHeight;

        grille = new Cell[height][width];
    }

    public Grid(Cell[][] grid) {
        this();
        height = grid.length;
        if (height > 0)
            width = grid[0].length;
        else
            width = 0;

        grille = grid;
    }

    public boolean applyMove(Player player){
        Coord currentCoord = playersCoord.get(player);
        Coord moveCoord = translateDirection(player.getMove(),currentCoord);
        moveCoord = (Coord2D) wrapper.wrap(moveCoord,grille,player.getCharacter());
        if (moveCorrect(player,currentCoord,moveCoord)){
            playersCoord.replace(player,moveCoord);
            ((Corridor)grille[((Coord2D)moveCoord).getY()][((Coord2D)moveCoord).getX()]).addCharacter(player.getCharacter());
            ((Corridor)grille[((Coord2D)currentCoord).getY()][((Coord2D)currentCoord).getX()]).delCharacter(player.getCharacter());
            return true;
        }else{
            ((Corridor)grille[((Coord2D)currentCoord).getY()][((Coord2D)currentCoord).getX()]).addCharacter(player.getCharacter());

        }
        return false;
    }

    public boolean eatConsumable(Player p){
        Character charac = p.getCharacter();
        Coord2D currentCoord = (Coord2D) playersCoord.get(p);
        if (charac instanceof PacMan){
            Cell cellule = grille[currentCoord.getY()][currentCoord.getX()];
            if (cellule instanceof Corridor){ // Verification avant cast (même si normalement pas besoin)
                Corridor corridor = (Corridor) cellule;
                Consumable consumable = corridor.getConsumable();
                if (consumable != null){
                    p.upScore(consumable.getNbPoints());

                    // Si le consomable à un effet
                    if (consumable instanceof EffectConsumable){
                     ((EffectConsumable) consumable).applyEffect(p);
                    }
                    corridor.setConsumable(null);

                    if (!consumable.isNegativeEffect()){

                        return true;
                    }
                    return false;
                }else {
                    return false;
                }

            }else{
                return false;
            }
        }
        return false;
    }

    private Coord translateDirection(Direction d,Coord c){
        Coord2D current = (Coord2D) c;
        switch (d){
            case TOP: return new Coord2D(current.getX(),current.getY()-1);
            case LEFT: return new Coord2D(current.getX()-1,current.getY());
            case RIGHT: return new Coord2D(current.getX()+1,current.getY());
            case BOTTOM: return new Coord2D(current.getX(),current.getY()+1);
            default: return new Coord2D(current.getX(),current.getY());
        }
    }

    private boolean moveCorrect(Player p, Coord c, Coord m){
        // TODO verification en cas de sortie du tableau et wrap around !
        Coord2D currentPos = (Coord2D) c;
        Coord2D move = (Coord2D) m;
        Character perso = p.getCharacter();
        if (!p.getCharacter().isAlive())
            return false;

        // si le move ne peut être wrapper on renvois tel quel sinon le nouveau et envoyé


        // Verification de sortie du tableau
        if (move.getX() < 0 || move.getX() >= width || move.getY() < 0 || move.getY() >= height )
            return false;

        // Verification si on va dans un mur
        if (grille[move.getY()][move.getX()] instanceof Wall)
            return false;
        // Verification si il y a deja un fantome sur la case (seulement pour les fantomes)
        if (perso instanceof Ghost){
            Set keys = playersCoord.keySet();
            Iterator it = keys.iterator();
            while (it.hasNext()){
                Player tmpPlayer = (Player) it.next();
                Coord2D tmpCoord = (Coord2D) playersCoord.get(tmpPlayer);
                if (tmpPlayer.getCharacter() instanceof Ghost){
                    if (tmpCoord.equals(move))
                        return false;
                }
            }
        }

        return true;
    }

    private Player getPacManCoordInMap(){
        Set keys = playersCoord.keySet();
        Iterator it = keys.iterator();
        while (it.hasNext()){
            Player tmpPlayer = (Player) it.next();
            if (tmpPlayer.getCharacter() instanceof PacMan)
                return tmpPlayer;
        }
        return null;
    }

    public boolean hasCollision() {
        // Si pacman peut manger des fantome : il les mange dans cette methode
        Player pacman =  getPacManCoordInMap();
        if (pacman != null){
            Coord2D currentCoord = (Coord2D) playersCoord.get(pacman);
            Set keys = playersCoord.keySet();
            Iterator it = keys.iterator();
            while (it.hasNext()){
                Player tmpPlayer = (Player) it.next();
                Coord2D tmpCoord = (Coord2D) playersCoord.get(tmpPlayer);
                if (!(tmpPlayer.getCharacter() instanceof PacMan)){
                    if (tmpCoord.equals(currentCoord)){
                        if (pacman.getCharacter().isInvulnerability()){
                            pacman.upScore(((Ennemy)tmpPlayer.getCharacter()).getNbPoints());
                            tmpPlayer.getCharacter().setAlive(false);
                            tmpPlayer.getCharacter().setRespawnTime(respawnTime);
                            playersCoord.replace(tmpPlayer,new Coord2D(-1,-1));
                        }else{
                            System.out.println(tmpCoord + " : " + tmpPlayer.getCharacter());
                            int left  = pacman.looseOneLife();
                            System.out.println(left);
                            return left <= 0;
                        }
                    }
                }

            }
        }
        return false;


    }

    public Cell[][] getGrille() {
        return grille;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRespawnTime() {
        return respawnTime;
    }

    public void setPlayerCoord(Player p, Coord c){
        playersCoord.put(p,c);
    }

    public void respawn(Player p, Coord spawnGhost) {
        playersCoord.replace(p,spawnGhost);
        if (grille[((Coord2D)spawnGhost).getY()][((Coord2D)spawnGhost).getX()] instanceof Corridor){
            ((Corridor)grille[((Coord2D)spawnGhost).getY()][((Coord2D)spawnGhost).getX()]).addCharacter(p.getCharacter());
        }
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "grille=" + (grille == null ? null : Arrays.asList(grille)) +
                ", playersCoord=" + playersCoord +
                ", pacmanCoord=" + pacmanCoord +
                ", width=" + width +
                ", height=" + height +
                ", respawnTime=" + respawnTime +
                ", wrapper=" + wrapper +
                '}';
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Map<Player, Coord> getPlayersCoord() {
        return playersCoord;
    }

    public void setRespawnTime(int respawnTime) {
        this.respawnTime = respawnTime;
    }
}
