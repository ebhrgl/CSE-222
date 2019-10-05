import java.util.Iterator;

public class TransitiveClass implements Graph{
    private final int vertices;
    private int[][] adjacency_matrix;
    private boolean directed;

    public TransitiveClass(int v)
    {
        vertices = v;
        adjacency_matrix = new int[vertices + 1][vertices + 1];
        this.directed = false;
    }

    int[][] transitiveClosure(int graphMatrix[][])
    {
        int  i, j, k;

        for (k = 0; k < getNumV(); k++)
        {
            for (i = 0; i < getNumV(); i++)
            {
                for (j = 0; j < getNumV(); j++)
                {
                    if(i==j)
                    {
                        graphMatrix[i][j] = 0;
                    }
                    else{
                        if((graphMatrix[i][j]!=0) ||  ((graphMatrix[i][k]!=0) && (graphMatrix[k][j]!=0))){
                            graphMatrix[i][j]=1;
                        }
                        else{
                            graphMatrix[i][j] = 0;
                        }
                    }
                }
            }
        }
        return graphMatrix;
    }

    @Override
    public void addEdge(int to, int from, int edge)
    {
        try
        {
            adjacency_matrix[to][from] = edge;
        }
        catch (ArrayIndexOutOfBoundsException index)
        {
            System.out.println("vertices not exists");
        }
    }

    @Override
    public int getNumV() {
        return vertices;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public boolean isEdge(int source, int dest) {
        return Double.POSITIVE_INFINITY != getEdge(source, dest);
    }


    public int getEdge(int to, int from)
    {
        try
        {
            return adjacency_matrix[to][from];
        }
        catch (ArrayIndexOutOfBoundsException index)
        {
            System.out.println("vertices not exists");
        }
        return -1;
    }

    @Override
    public Iterator<Edge> edgeIterator(int source) {
        return null;
    }

    int popularValue(int[][] input){
        int max=0;
        int[] columnCountArr = new int[vertices];
        for (int i = 0; i <vertices ; i++) {
            for (int j = 0; j < vertices; j++) {
                if(input[j][i]==1){
                    columnCountArr[i]+=1;
                }
            }
        }
        //find the max value of the columnCountArr
        int maxColumnValue= 0;
        for (int i = 1; i <vertices ; i++) {
            if(columnCountArr[i]>maxColumnValue){
                maxColumnValue=columnCountArr[i];
            }
        }
        int maxColumnValue_counter=0;
        for (int i = 0; i < vertices; i++) {
            if(columnCountArr[i]==maxColumnValue){
                maxColumnValue_counter++;
            }
        }
        return  maxColumnValue_counter;
    }

}