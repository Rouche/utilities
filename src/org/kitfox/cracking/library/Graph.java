package org.kitfox.cracking.library;

/**
 * @Author Jean-Francois Larouche (resolutech) on 2019-08-07
 */
public class Graph {

    public static int MAX_VERTICES = 6;
    private GraphNode vertices[];
    public int count;

    public Graph() {
        vertices = new GraphNode[MAX_VERTICES];
        count = 0;
    }

    public void addNode(GraphNode x) {
        if (count < vertices.length) {
            vertices[count] = x;
            count++;
        } else {
            System.out.print("Graph full");
        }
    }

    public GraphNode[] getNodes() {
        return vertices;
    }
}