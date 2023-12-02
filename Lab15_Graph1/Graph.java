package Week8_Lab15;

/**
 * Graph.java
 * @author Theo Lee
 * CIS 22C, Lab 15
 */
import java.util.ArrayList;

public class Graph {
    private int vertices;
    private int edges;
    private ArrayList<LinkedList<Integer>> adj;
    private ArrayList<Character> color;
    private ArrayList<Integer> distance;
    private ArrayList<Integer> parent;
    private ArrayList<Integer> discoverTime;
    private ArrayList<Integer> finishTime;
    private static int time = 0;

    /** Constructors and Destructors */

    /**
     * initializes an empty graph, with n vertices and 0 edges
     *
     * @param numVtx the number of vertices in the graph
     * @precondition numVtx > 0
     * @throws IllegalArgumentException when numVtx <= 0
     */
    public Graph(int numVtx) throws IllegalArgumentException {
        if (numVtx <= 0) {
            throw new IllegalArgumentException("Number of vertices must be greater than 0.");
        }
        this.vertices = numVtx;
        this.edges = 0;
        adj = new ArrayList<>();
        color = new ArrayList<>();
        distance = new ArrayList<>();
        parent = new ArrayList<>();
        discoverTime = new ArrayList<>();
        finishTime = new ArrayList<>();

        for (int i = 0; i < numVtx; i++) {
            adj.add(new LinkedList<>());
            color.add('W');
            distance.add(-1);
            parent.add(0);
            discoverTime.add(-1);
            finishTime.add(-1);
        }
    }

    /*** Accessors ***/

    /**
     * Returns the number of edges in the graph
     *
     * @return the number of edges
     */
    public int getNumEdges() {
        return this.edges;
    }

    /**
     * Returns the number of vertices in the graph
     *
     * @return the number of vertices
     */
    public int getNumVertices() {
        return this.vertices;
    }

    /**
     * returns whether the graph is empty (no edges)
     *
     * @return whether the graph is empty
     */
    public boolean isEmpty() {
        return this.edges == 0;
    }

    /**
     * Returns the value of the distance[v]
     *
     * @param v a vertex in the graph
     * @precondition 0 < v <= vertices
     * @return the distance of vertex v
     * @throws IndexOutOfBoundsException when v is out of bounds
     */
    public Integer getDistance(Integer v) throws IndexOutOfBoundsException {
        if (v < 1 || v > this.vertices) {
        	throw new IndexOutOfBoundsException("Vertex is out of bounds.");
        }
        return distance.get(v - 1);
    }

    /**
     * Returns the value of the parent[v]
     *
     * @param v a vertex in the graph
     * @precondition 0 < v <= vertices
     * @return the parent of vertex v
     * @throws IndexOutOfBoundsException when v is out of bounds
     */
    public Integer getParent(Integer v) throws IndexOutOfBoundsException {
        if (v < 1 || v > this.vertices) {
            throw new IndexOutOfBoundsException("Vertex " + v + " is out of bounds.");
        }
        Integer parentVertex = parent.get(v - 1);
        if (parentVertex == null) {
            return -1;
        } else {
            return parentVertex;
        }
    }


    /**
     * Returns the value of the color[v]
     *
     * @param v a vertex in the graph
     * @precondition 0 < v <= vertices
     * @return the color of vertex v
     * @throws IndexOutOfBoundsException when v is out of bounds
     */
    public Character getColor(Integer v) throws IndexOutOfBoundsException {
        if (v < 1 || v > this.vertices) {
            throw new IndexOutOfBoundsException("Vertex is out of bounds.");
        }
        return color.get(v - 1);
    }

    /**
     * Returns the value of the discoverTime[v]
     *
     * @param v a vertex in the graph
     * @precondition 0 < v <= vertices
     * @return the discover time of vertex v
     * @throws IndexOutOfBoundsException when v is out of bounds
     */
    public Integer getDiscoverTime(Integer v) throws IndexOutOfBoundsException {
        if (v < 1 || v > this.vertices) {
            throw new IndexOutOfBoundsException("Vertex is out of bounds.");
        }
        return discoverTime.get(v - 1);
    }

    /**
     * Returns the value of the finishTime[v]
     *
     * @param v a vertex in the graph
     * @precondition 0 < v <= vertices
     * @return the finish time of vertex v
     * @throws IndexOutOfBoundsException when v is out of bounds
     */
    public Integer getFinishTime(Integer v) throws IndexOutOfBoundsException {
        if (v < 1 || v > this.vertices) {
            throw new IndexOutOfBoundsException("Vertex is out of bounds.");
        }
        return finishTime.get(v - 1);
    }

    /**
     * Returns the LinkedList stored at index v
     *
     * @param v a vertex in the graph
     * @return the adjacency LinkedList at v
     * @precondition 0 < v <= vertices
     * @throws IndexOutOfBoundsException when v is out of bounds
     */
    public LinkedList<Integer> getAdjacencyList(Integer v) throws IndexOutOfBoundsException {
        if (v < 1 || v > this.vertices) {
            throw new IndexOutOfBoundsException("Vertex is out of bounds.");
        }
        return adj.get(v - 1);
    }

    /*** Manipulation Procedures ***/

    /**
     * Inserts vertex v into the adjacency list of vertex u (i.e. inserts v into
     * the list at index u) @precondition, 0 < u, v <= vertices
     *
     * @param u a vertex in the graph
     * @param v a vertex in the graph
     * @throws IndexOutOfBounds exception when u or v is out of bounds
     */
    public void addDirectedEdge(Integer u, Integer v) throws IndexOutOfBoundsException {
        if (u < 1 || u > this.vertices || v < 1 || v > this.vertices) {
            throw new IndexOutOfBoundsException("Vertex is out of bounds.");
        }
        adj.get(u - 1).addLast(v);
        this.edges++;
    }

    /**
     * Inserts vertex v into the adjacency list of vertex u (i.e. inserts v into
     * the list at index u) and inserts u into the adjacent vertex list of v.
     *
     * @param u a vertex in the graph
     * @param v a vertex in the graph
     * @precondition, 0 < u, v <= vertices
     * @throws IndexOutOfBoundsException when u or v is out of bounds
     */
    public void addUndirectedEdge(Integer u, Integer v) throws IndexOutOfBoundsException {
        if (u < 1 || u > this.vertices || v < 1 || v > this.vertices) {
            throw new IndexOutOfBoundsException("Vertex is out of bounds.");
        }
        adj.get(u - 1).addLast(v);
        adj.get(v - 1).addLast(u);
        this.edges++;
    }


    /*** Additional Operations ***/

    /**
     * Creates a String representation of the Graph Prints the adjacency list of
     * each vertex in the graph, vertex: <space separated list of adjacent
     * vertices>
     * @return a space separated list of adjacent vertices
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vertices; i++) {
            sb.append(i + 1).append(": ");
            LinkedList<Integer> list = adj.get(i);
            if (!list.isEmpty()) {
                list.positionIterator();
                for (int j = 0; j < list.getLength(); j++) {
                    sb.append(list.getIterator()).append(" ");
                    list.advanceIterator();
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    /**
     * Performs breath first search on this Graph give a source vertex
     *
     * @param source the starting vertex
     * @precondition source is a vertex in the graph
     * @throws IndexOutOfBoundsException when the source vertex is out of bounds
     *     of the graph
     */
    public void BFS(Integer source) throws IndexOutOfBoundsException {
    }

    /**
     * Performs depth first search on this Graph in order of vertex lists
     */
    public void DFS() {
    }

    /**
     * Private recursive helper method for DFS
     *
     * @param vertex the vertex to visit
     */
    private void visit(int vertex) {
    }
}
