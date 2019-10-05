import java.util.Arrays;

/**
 * Edge class ı help from Koffman book
 */
public class Edge {

    private int[] source;

    private int[] dest;

    public Edge() {
        this.source = new int[100];
        this.dest = new int[100];
    }

    public int[] getSource() {
        return source;
    }

    public int[] getDest() {
        return dest;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "source=" + Arrays.toString(source) +
                ", dest=" + Arrays.toString(dest) +
                '}';
    }

    public boolean equals(Object obj) {
        if (obj instanceof Edge) {
            Edge edge = (Edge) obj;
            return (source == edge.source
                    && dest == edge.dest);
        }
        else {
            return false;
        }
    }


    /**** END EXERCISE ****/
}