package model.entities.players.AI.Dijkstra;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;

import java.util.Map;
import java.util.Objects;

/**
 *
 * @author victor
 */
public class Node {
    protected Object contain;

    protected LinkedList<Node> shortestPath;

    protected Integer distance;

    protected Map<Node, Integer> adjacentNodes;

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node(Object contain) {
        this.contain = contain;
        distance = Integer.MAX_VALUE;
        adjacentNodes = new HashMap<>();
        shortestPath = new LinkedList<>();
    }

    public Object getContain() {
        return contain;
    }

    public void setName(String name) {
        this.contain = name;
    }

    public LinkedList<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(contain, node.contain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contain);
    }

    @Override
    public String toString() {
        return "Node{" +
                "contain=" + contain +
                ", shortestPath=" + shortestPath +
                ", distance=" + distance +
                '}';
    }
}
