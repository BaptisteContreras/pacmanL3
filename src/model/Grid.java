package model;

import model.coordonates.Coord;
import model.coordonates.Coord2D;
import model.entities.cells.Cell;
import model.entities.cells.Corridor;
import model.entities.cells.Wall;
import model.entities.characters.Character;
import model.entities.characters.Ghost;
import model.entities.characters.PacMan;
import model.entities.consumables.Consumable;
import model.entities.consumables.EffectConsumable;
import model.entities.players.Player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Grid {

    private Cell[][] grille;
    private Map<Player,Coord> playersCoord;
    private Coord pacmanCoord;
    private int width; // x = j
    private int height; // y = i


    public Grid(int gridWidth, int gridHeight) {
        width = gridWidth;
        height = gridHeight;

        grille = new Cell[height][width];
    }

    public Grid(Cell[][] grid) {
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
        if (moveCorrect(player,currentCoord,moveCoord)){
            playersCoord.replace(player,moveCoord);
            return true;
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


                    return true;
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
            case TOP: return new Coord2D(current.getX(),current.getY()+1);
            case LEFT: return new Coord2D(current.getX()-1,current.getY());
            case RIGHT: return new Coord2D(current.getX()+1,current.getY());
            case BOTTOM: return new Coord2D(current.getX(),current.getY()-1);
            default: return new Coord2D(current.getX(),current.getY());
        }
    }

    private boolean moveCorrect(Player p, Coord c, Coord m){
        Coord2D currentPos = (Coord2D) c;
        Coord2D move = (Coord2D) m;
        Character perso = p.getCharacter();
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
        // A FINIR
        // Si pacman peut manger des fantome : il les mange dans cette methode
        Player pacman =  getPacManCoordInMap();
        if (pacman != null){
            Coord2D currentCoord = (Coord2D) playersCoord.get(pacman);
        }
        return false;

        return false;
    }
}
