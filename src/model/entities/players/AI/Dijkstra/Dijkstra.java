package model.entities.players.AI.Dijkstra;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import model.coordonates.Coord2D;
import model.entities.cells.Cell;
import model.entities.cells.Corridor;
import model.grid.Grid;

import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author victor
 */
public interface Dijkstra {

    public static Graph runDijkstra(Graph graph, Node source) {
        source.setDistance(0);
        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Entry < Node, Integer> adjacencyPair: currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    adjacentNode = minimumDistance(adjacentNode, edgeWeight, currentNode);//l'adjacentNode a changé de shortest path
                    for (Node node : graph.getNodes()){
                        if (node.equals(adjacentNode)){
                            node.setShortestPath(adjacentNode.getShortestPath());
                        }
                    }
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        System.out.println("graph =");
        for (Node node : graph.getNodes()){
            System.out.println(node);
        }
        return graph;
    }

    public static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }


    public static Node minimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
            return evaluationNode;
        }
        else return evaluationNode;
    }

    public static LinkedList<Node> calculateShortestPathFromSource(Graph graph, Node source, Node destination){
        Graph graphTmp = Dijkstra.runDijkstra(graph, source);
        LinkedList<Node> list = new LinkedList<>();
        //list = null;
        for (Node node : graph.getNodes()){
            if (destination.equals(node))
                list = (LinkedList) node.getShortestPath();
        }
        return list;
    }

    public static Graph castGridToGraph(Grid grid){
        Graph graphToReturn = new Graph();
        //TODO caster les Cells en Nodes et les ajouter au graph
        for (int i=1 ; i<grid.getHeight()-1 ; ++i){
            for (int j=1 ; j<grid.getWidth()-1 ; ++j){
                if (grid.getGrille()[i][j] instanceof Corridor){
                    Cell theCell = (Cell) grid.getGrille()[i][j];
                    theCell.setCoordonnee(new Coord2D(j,i));
                    Node node = new Node(theCell);
                    // caster les voisins des cell en node
                    ArrayList<Cell> cells = new ArrayList<>();
                    Cell cell1, cell2, cell3 , cell4;

                    cell1 =(Cell) grid.getGrille()[i][j+1];
                    cell1.setCoordonnee(new Coord2D(j+1, i));
                    cell2 = (Cell) grid.getGrille()[i][j-1];
                    cell2.setCoordonnee(new Coord2D(j-1, i));
                    cell3 = (Cell) grid.getGrille()[i+1][j];
                    cell3.setCoordonnee(new Coord2D( j , i+1));
                    cell4 =(Cell) grid.getGrille()[i-1][j];
                    cell4.setCoordonnee(new Coord2D(j,i-1));
                    cells.add(cell1);
                    cells.add(cell2);
                    cells.add(cell3);
                    cells.add(cell4);
                    node.setAdjacentNodes(Dijkstra.filterValidCellNeighbour(cells));
                    graphToReturn.addNode(node);
                }
            }
        }
        //TODO faire les ajout de voisins pour les bords(Ici Droite Gauche), on prends en compte les wraps
        for (int i=0; i<grid.getHeight() ; ++i){

        }
        //TODO faire les ajout de voisins pour les bords(Ici Haut Bas), on prends en compte les wraps
        for (int j =0 ; j<grid.getWidth() ; ++j){

        }
        return graphToReturn;
    }

    public static Map<Node, Integer> filterValidCellNeighbour(ArrayList<Cell> cells){
        Map<Node, Integer> validNodes = new HashMap<>();
        for (Cell cell : cells){
            Cell tata = cell;

            if (cell instanceof Corridor)
                validNodes.put(new Node(cell), 1);
        }
        return validNodes;
    }
}
