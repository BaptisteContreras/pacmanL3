package model.entities.players.AI.Dijkstra;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author victor
 */
public class Graph {
    protected Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "Graph{" + "nodes=" + nodes + "}\n\n\n";
    }
}
