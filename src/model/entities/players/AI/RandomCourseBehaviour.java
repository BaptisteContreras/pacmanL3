package model.entities.players.AI;

import model.Direction;
import model.JeuModel;
import model.coordonates.Coord;
import model.coordonates.Coord2D;
import model.entities.cells.Cell;
import model.entities.cells.Corridor;
import model.entities.characters.Character;
import model.entities.characters.Ennemy;
import model.entities.characters.PacMan;
import model.entities.players.AIPlayer;
import model.entities.players.HumanPlayer;
import model.entities.players.Player;
import model.grid.Grid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import static model.Direction.TOP;

public class RandomCourseBehaviour implements AIBehaviour, Serializable {
    @Override
    public Direction getBehaviourMove(JeuModel model, Player currentCharacter) {
        //return this.getRandomDirection();
        return this.getSmarterDirection(model, (AIPlayer) currentCharacter);
    }

    private Direction getRandomDirection (){
        //TODO gerer le fait que si le fantome et pacman se fonce dessus alors il n'y aura pas de collision donc il faut la forcer
            int pick = new Random().nextInt(Direction.values().length);
            return Direction.values()[pick];
    }

    private Direction getSmarterDirection (JeuModel model, AIPlayer currentPlayer){
        //si pacMan dans entourage alors on va sur pacman
        if (currentPlayer.getCharacter() instanceof Ennemy){//si on est bien un fantome

            Coord2D ghostCell = new Coord2D(0,0);//RISQUE ERREUR
            Coord2D pacManCoord = new Coord2D(0,0);//RISQUE ERREUR
            Player pacManPlayer = new HumanPlayer(null, "risque erreur");

            for (Map.Entry<Player, Coord> pair : model.getGrid().getPlayersCoord().entrySet()){
                if(currentPlayer == pair.getKey())
                    ghostCell = (Coord2D) pair.getValue();
                if(pair.getKey().getCharacter() instanceof PacMan) {
                    pacManPlayer = pair.getKey();
                    pacManCoord = (Coord2D)pair.getValue();
                }
            }
            //met tous les voisins du ghost dans un tableau
            ArrayList<Coord2D> ghostNeighboursCoord = new ArrayList<>();
            Coord2D tmp = ghostCell;
            tmp.setX(ghostCell.getX()-1);
            ghostNeighboursCoord.add(tmp);
            tmp = ghostCell;
            tmp.setX(ghostCell.getX()+1);
            ghostNeighboursCoord.add(tmp);
            tmp = ghostCell;
            tmp.setY(ghostCell.getY()+1);
            ghostNeighboursCoord.add(tmp);
            tmp = ghostCell;
            tmp.setY(ghostCell.getY()-1);

            //on regarde si les cooronnees du pacman sont dans ghostNeighbour

            for (Coord2D neighbour : ghostNeighboursCoord){
                if(pacManCoord == neighbour){
                    //TODO on compare la direction et si sens inverse set speed a 0 bien penser a remettre le speed à default au debit de la fonction
                    //todo  peut etre que dans direction on peut rajouter un stop qui mets le speed a 0
                    //todo demander a Baptiste si c'est pas trop degeu cmme soluces
                    return this.getTranslateDirectionFrom2Coord(ghostCell, pacManCoord);
                }
            }

        }
        //else if(){//si on est un pacMan
        else{
        }
        //TODO sinon on a une chance sur 2 de changer de direction
        return getRandomDirection();
    }

    private Direction getTranslateDirectionFrom2Coord(Coord2D source, Coord2D destination){
        if (source.getX()<destination.getX())
            return Direction.RIGHT;
        if (source.getX()>destination.getX())
            return Direction.LEFT;
        if (source.getY()<destination.getY())
            return Direction.TOP;
        else
            return Direction.BOTTOM;
    }
}
