package org.kitfox.cracking.library;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author Jean-Francois Larouche (resolutech) on 2019-08-07
 */
@Slf4j
public class GraphNode {

    private GraphNode adjacent[];
    public int adjacentCount;
    private String vertex;
    public NodeState state;

    public GraphNode(String vertex, int adjacentLength) {
        this.vertex = vertex;
        adjacentCount = 0;
        adjacent = new GraphNode[adjacentLength];
    }

    public void addAdjacent(GraphNode x) {
        if (adjacentCount < adjacent.length) {
            this.adjacent[adjacentCount] = x;
            adjacentCount++;
        } else {
            log.info("No more adjacent can be added");
        }
    }

    public GraphNode[] getAdjacent() {
        return adjacent;
    }

    public String getVertex() {
        return vertex;
    }
}