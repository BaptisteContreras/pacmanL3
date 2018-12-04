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
import model.entities.players.AI.Dijkstra.Dijkstra;
import model.entities.players.AI.Dijkstra.Graph;
import model.entities.players.AI.Dijkstra.Node;
import model.entities.players.Player;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class FollowBehaviour implements AIBehaviour, Serializable {

    @Override
    public Direction getBehaviourMove(JeuModel model, Player currentCharacter) {
        Graph graph = Dijkstra.castGridToGraph(model.getGrid());
        Node source = this.getSource(model, currentCharacter, graph);
        graph = Dijkstra.runDijkstra(graph,source);
        Node destination = this.getDestination(model, currentCharacter, graph);
        LinkedList<Node> shortestPath = Dijkstra.calculateShortestPathFromSource(graph,source, destination);
        System.out.println("shortest path =");
        for (Node node : shortestPath){
            System.out.println(node);
        }
        return Direction.RIGHT;
    }

    public Node getSource(JeuModel model, Player currentCaracter, Graph graph){
        Node nodeToReturn = null;
        //On parcourt la map <palyer,coord> pour connaitre la case de notre currentPlayer
        for(Map.Entry<Player, Coord> entry : model.getGrid().getPlayersCoord().entrySet()) {
            Player player = entry.getKey();
            if (player == currentCaracter) {
                Coord coord = (Coord2D)entry.getValue();
                //on cherche la case de la grille correspondant a la position du cuurentPlayer
                Cell cell =  model.getGrid().getGrille()[((Coord2D) coord).getY()][((Coord2D) coord).getX()];
                cell.setCoordonnee(new Coord2D( ((Coord2D) coord).getX(), ((Coord2D)coord).getY()));
                nodeToReturn = new Node(cell);
                break;
            }
        }
        //pour l'intant le node to return ne possède pas de voisins
        //et le graph possède notre node source avec ses voisins
        //dans la boucle suivante on prend le node du graphe (avec ses voidins) correspondant à notre node source
        for (Node node : graph.getNodes()){
            Node n = node;
            Node t = nodeToReturn;
            if (node.equals(nodeToReturn))
                return node;
        }
        return nodeToReturn;
    }

    public Node getDestination(JeuModel model, Player currentPlayer, Graph graph){
        Node nodeToReturn = null;
        //Si on est un fantome
        if (currentPlayer.getCharacter() instanceof Ennemy){
            for(Map.Entry<Player, Coord> entry : model.getGrid().getPlayersCoord().entrySet()) {
                Player player = entry.getKey();
                //si on a trouvé pacMan
                if (player.getCharacter() instanceof PacMan){
                    Coord coord = (Coord2D)entry.getValue();
                    Cell cell =  model.getGrid().getGrille()[((Coord2D) coord).getY()][((Coord2D) coord).getX()];
                    nodeToReturn = new Node(cell);
                    break;
                }
            }
        }
        //sinon, si on est un pacMan
        else
        {
            //TODO faire l'IA du pacMan qui cherche un fantome (si possible chercher le plus proche)
        }

        //On a un noeud destination sans ses voisins et on a un graphe avec tous les noeuds et les voisins
        //on va donc chercher le nodeToReturn dans le graphe et retourner le noeud du graphe
        for (Node node : graph.getNodes()){
            if (node.equals(nodeToReturn)){
                return node;
            }
        }
        return nodeToReturn;
    }


    //TODO fonction qui donne la direction en fonction du tableau de pluscourtchemin
    protected LinkedList<Node> getShortestPathFromDest(Node destination){
        return destination.getShortestPath();

    }

    protected Direction getDirectionFromShotestPath(LinkedList<Node> shortestPath){
        if (shortestPath == null)
            System.out.println("sp null");
        if (shortestPath.isEmpty())
            System.out.println("sp empty");
        else {
            int  sizeSP = shortestPath.size();
            Cell whereToGo = (Cell) shortestPath.get(sizeSP -1).getContain();
            Cell cellSource = (Cell) shortestPath.get(sizeSP).getContain();

            if (((Coord2D) whereToGo.getCoordonnee()).getX() == ((Coord2D) cellSource.getCoordonnee()).getX() && ((Coord2D) whereToGo.getCoordonnee()).getY() < ((Coord2D) cellSource.getCoordonnee()).getY())
                return Direction.BOTTOM;
            if (((Coord2D) whereToGo.getCoordonnee()).getX() == ((Coord2D) cellSource.getCoordonnee()).getX() && ((Coord2D) whereToGo.getCoordonnee()).getY() < ((Coord2D) cellSource.getCoordonnee()).getY())
                return Direction.TOP;
            if (((Coord2D) whereToGo.getCoordonnee()).getX() < ((Coord2D) cellSource.getCoordonnee()).getX() && ((Coord2D) whereToGo.getCoordonnee()).getY() == (((Coord2D) cellSource.getCoordonnee()).getY()))
                return Direction.LEFT;
            else
                return Direction.RIGHT;
        }
        return Direction.RIGHT;
    }

}
