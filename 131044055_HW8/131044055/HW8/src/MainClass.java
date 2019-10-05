import java.io.File;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {

        File file = new File("input.txt");
        int finalGraph[][] = new int[100][100];
        Edge edge = new Edge();
        int index = 0;
        int[][] matrix = new int[100][100];

        int v, e, count = 1, to = 0, from = 0;
        Scanner sc = new Scanner(System.in);
        TransitiveClass graph;
        try
        {
            /**
             * read from file
             */
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");
                edge.getSource()[index] = Integer.parseInt(words[0]);
                edge.getDest()[index] = Integer.parseInt(words[1]);
                index++;
            }
            /**
             * vertex and edge
             */
            v = edge.getSource()[0];
            e = edge.getDest()[0];

            graph = new TransitiveClass(v);

            while (count <= e)
            {
                for (int x=1; x<=count; x++){
                    to = edge.getSource()[x];
                    from = edge.getDest()[x];
                }
                graph.addEdge(to, from, 1);
                count++;
            }

            for (int i = 0; i < v; i++)
            {
                for (int j = 0; j < v; j++){
                    matrix[i][j] = graph.getEdge(i+1,j+1);
                }
            }
            /**
             * transitive
             */
            finalGraph = graph.transitiveClosure(matrix);

            System.out.println("Output: " + graph.popularValue(finalGraph));

        }
        catch (Exception E)
        {
            System.out.println("Wrong");
        }
        sc.close();
    }
}
