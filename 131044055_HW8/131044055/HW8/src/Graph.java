import java.util.Iterator;

/**
 * Graph interface
 */
public interface Graph {

    int getNumV();

    boolean isDirected();

    boolean isEdge(int source, int dest);

    int getEdge(int source, int dest);

    void addEdge(int to, int from, int edge);
    Iterator< Edge > edgeIterator(int source);

}