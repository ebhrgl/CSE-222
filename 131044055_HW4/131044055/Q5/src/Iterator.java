import java.util.ArrayList;

public class Iterator {
    /**
     * list,iterator,row and column definition
     */
    private ArrayList<Integer> list;
    private java.util.Iterator<Integer> itr = null;
    private int row;
    private int column;


    public Iterator(int [][] data) {

        list = new ArrayList<Integer>();
        row = data.length-1;
        column = data[0].length-1;

    }

    /**
     *
     * @param data
     * @param rowStart
     * @param colStart
     * @param colValue
     * @param rowValue
     * @return
     */
    private java.util.Iterator<Integer> MatrixRecursion(int[][] data, int rowStart, int colStart, int colValue,  int rowValue){
        for (int i = rowStart; i <= colValue; i++) {

            list.add(data[rowStart][i]);
        }
        for (int i = rowStart+1; i <= rowValue; i++) {

            list.add(data[i][colValue]);
        }

        if(rowStart+1 <= rowValue){
            for (int i = colValue-1; i >= colStart; i--) {

                list.add(data[rowValue][i]);
            }
        }

        if(colStart+1 <= colValue){
            for (int i = rowValue-1; i > rowStart; i--) {

                list.add(data[i][colStart]);
            }
        }

        //Recursion call
        if(rowStart+1 <= rowValue-1 && colStart+1 <= colValue-1){
            MatrixRecursion(data, rowStart+1, colStart+1, colValue-1, rowValue-1);
        }
        itr = list.iterator();
        return itr;
    }


    public Object next(){
        return (Object)itr.next();
    }

    public boolean hasNext(){
        return itr.hasNext();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public static void main(String[] args) {
        int[][] data = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };
        Iterator x = new Iterator(data);
        x.MatrixRecursion(data,0,0,x.getColumn(),x.getRow());
        while(x.hasNext()){
            System.out.println(x.next());
        }
    }
}
