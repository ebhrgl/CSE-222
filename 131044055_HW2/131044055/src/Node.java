public class Node {
    /**
     * next linked node of current node
     */
    private Node next;
    /**
     * next same day experiment node
     */
    private Node nextday;
    /**
     * Indicates that node is head of day circular list
     */
    private Experiment data;

    /**
     *
     * @param data experiment to be inserted to node
     */
    Node(){
        next = null;
        nextday = null;

    }

    /**
     *
     * @param data
     */
    Node(Experiment data){
        this.data = data;
        next = null;
        nextday = null;

    }

    /**
     *
     * @param dataValue
     * @param nextValue
     * @param nextDayValue
     */
    public Node(Experiment dataValue, Node nextValue, Node nextDayValue) {
        next = nextValue;
        data = dataValue;
        nextday = nextDayValue;

    }

    /**
     *
     * @return
     */
    public Node getNext() {

        return next;
    }

    /**
     *
     * @param next
     */
    public void setNext(Node next) {

        this.next = next;
    }

    /**
     *
     * @return
     */
    public Node getNextday() {

        return nextday;
    }

    /**
     *
     * @param nextday
     */
    public void setNextday(Node nextday) {

        this.nextday = nextday;
    }

    /**
     *
     * @return
     */
    public Experiment getData() {

        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(Experiment data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "next=" + next +
                ", nextday=" + nextday +
                ", data=" + data +
                '}';
    }
}





