package org.kitfox.cracking;

import java.util.LinkedList;

import org.junit.Test;
import org.kitfox.cracking.library.Graph;
import org.kitfox.cracking.library.GraphNode;
import org.kitfox.cracking.library.NodeState;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author Jean-Francois Larouche (resolutech) on 2019-08-06
 */
@Slf4j
public class Question4_1 {

    @Test
    public void testSearchRoute() {
        Graph g = createNewGraph();
        GraphNode[] n = g.getNodes();
        GraphNode start = n[3];
        GraphNode end = n[5];
        initGraph(g);
        log.info("Can find: [{}]", searchBFS(g, start, end));
        initGraph(g);
        log.info("Can find: [{}]", searchDFS(start, end));
    }

    private boolean searchBFS(Graph g, GraphNode start, GraphNode end) {
        // Queue
        LinkedList<GraphNode> q = new LinkedList<GraphNode>();

        start.state = NodeState.Visiting;
        q.add(start);

        while (!q.isEmpty()) {

            GraphNode visiting = q.removeFirst();
            if (visiting != null) {
                for (GraphNode futureVisit : visiting.getAdjacent()) {
                    if (futureVisit.state == NodeState.Unvisited) {
                        if (futureVisit == end) {
                            return true;
                        } else {
                            futureVisit.state = NodeState.Visiting;
                            q.add(futureVisit);
                        }
                    }
                }
                visiting.state = NodeState.Visited;
            }
        }
        return false;
    }

    private boolean searchDFS(GraphNode start, GraphNode end) {
        if(start == null || end == null) {
            return false;
        }
        start.state = NodeState.Visiting;
        return searchDFSInternal(start, end);
    }

    private boolean searchDFSInternal(GraphNode start, GraphNode end) {
        if(start == end) {
            return true;
        }
        for(GraphNode node : start.getAdjacent()) {
            if(node.state == NodeState.Unvisited) {
                node.state = NodeState.Visiting;
                if(searchDFS(node, end)) {
                    return true;
                }
            }
        }

        return false;
    }


    private Graph createNewGraph() {
        Graph g = new Graph();
        GraphNode[] temp = new GraphNode[6];

        temp[0] = new GraphNode("a", 3);
        temp[1] = new GraphNode("b", 0);
        temp[2] = new GraphNode("c", 0);
        temp[3] = new GraphNode("d", 1);
        temp[4] = new GraphNode("e", 1);
        temp[5] = new GraphNode("f", 0);

        temp[0].addAdjacent(temp[1]);
        temp[0].addAdjacent(temp[2]);
        temp[0].addAdjacent(temp[3]);
        temp[3].addAdjacent(temp[4]);
        temp[4].addAdjacent(temp[5]);
        for (int i = 0; i < 6; i++) {
            g.addNode(temp[i]);
        }
        return g;
    }

    private void initGraph(Graph g) {
        // Init states
        for (GraphNode u : g.getNodes()) {
            u.state = NodeState.Unvisited;
        }
    }
}
