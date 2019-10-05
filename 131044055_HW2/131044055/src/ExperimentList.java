import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.LinkedList;

public class ExperimentList implements Iterable<Experiment> {


    private static int counter;
    private Node head;
    private LinkedList<Experiment> experiments = new LinkedList<>();
    // Default constructor
    public ExperimentList() {
        head = null;
        counter=0;
    }

    /**
     *
     * @param data
     */
    // appends the specified element to the end of this list.
    public void addExp(Experiment data) {

        if(head == null){
            head = new Node(data);
        }

        Node newNode = new Node(data);
        Node temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(newNode);
        ++counter;

    }

    /**
     *
     */
    public void listAll()
    {
        System.out.println("List experiment view:");
        Node last = head;
        while( last != null) {
            System.out.println(last.getData().toString());
            last = last.getNext();
        }
        System.out.println("List day view:");
        last = head;
        while( last != null) {
            System.out.println(last.getData().toString());
            last = last.getNextday();
        }
    }

    /**
     *
     * @param day
     * @return
     */
    public LinkedList<Experiment> listExp(int day){

        if (day < 0 || day > 8)
            throw new InvalidParameterException("Invalid day! (1-8)");

        LinkedList<Experiment> allList = new LinkedList<>();
        for(int i=0; i<experiments.size() ; ++i){
            if (experiments.get(i).getDay()== day)
                allList.add(experiments.get(i));
        }
        System.out.println(allList);
        return allList;
    }

    /**
     *
     * @param day
     * @return
     */
    public ExperimentList orderDay(int day){
        ExperimentList list = new ExperimentList();
        if (counter > 1) {
            for (int i = 0; i < counter; i++ ) {
                Node currentNode = head;
                Node next = head.getNext();

                for (int j = 0; j < counter - 1; j++) {
                    if (currentNode.getData().getDay() == day){
                        if (currentNode.getData().getAccuracy() > next.getData().getAccuracy()) {
                            Node temp = currentNode;
                            currentNode = next;
                            next = temp;
                        }
                        currentNode = next;
                        next = next.getNext();
                        list.addExp(next.getData());
                    }

                }
            }
        }
        return list;
    }

    /**
     *
     * @return
     */
    public ExperimentList orderExperiments(){
        ExperimentList list = new ExperimentList();
        if (counter > 1) {
            for (int i = 0; i < counter; i++ ) {
                Node currentNode = head;
                Node next = head.getNext();

                for (int j = 0; j < counter - 1; j++) {
                    if (currentNode.getData().getAccuracy() > next.getData().getAccuracy()) {
                        Node temp = currentNode;
                        currentNode = next;
                        next = temp;
                    }
                    currentNode = next;
                    next = next.getNext();
                    list.addExp(currentNode.getData());
                }
            }
        }
        return list;
    }

    /**
     *
     * @param day
     * @param index
     * @return
     */
    public Experiment getExp(int day, int index) {
        Node current = null;
        if (head != null) {
            current = head.getNext();
            for (int i = 0; i < index; i++) {
                current.setNext(current.getNext());
            }
        }
        return (Experiment)current.getData();
    }

    /**
     *
     * @param day
     * @param index
     * @param data
     */
    public void setExp(int day, int index, Experiment data)
    {
        if (index < 1 || index > counter && day < 1 || day > 8)
            throw new InvalidParameterException("Invalid day! (1-8)");

        Node current = null;
        if (head != null) {
            current = head.getNext();
            for (int i = 0; i < experiments.size(); ++i) {
                if (experiments.get(i).getDay() == day) {
                    current.setNext(current.getNext());
                }
            }
        }
        current.setData(data);
    }


    /**
     *
     * @param index
     * @param day
     * @return
     */
    // removes the element at the specified position in this list.
    public boolean removeExp(int index,int day) {

        if (index < 1 || index > counter && day < 1 || day > 8)
            return false;
       // LinkedList<Experiment> expList = new LinkedList<>();
        Node temp = head;
        if (head != null) {
            for (int i = 0; i < index; i++) {
               // if(experiments.get(i).getDay()== day) {
                    if (temp.getNext() == null)
                        return false;

                    temp = temp.getNext();
            }

            temp.setNext(temp.getNext().getNext());
                counter--;

            return true;

        }
        return false;
    }


    public boolean removeDay(int day){

        if (day < 0 || day > 8)
            throw new InvalidParameterException("Invalid day! (0-8)");

        LinkedList<Experiment> expList = new LinkedList<>();
        Node temp = head;
        if (head != null) {
            for (int i = 0; i < expList.size(); i++) {
                if (temp.getNext() == null)
                    return false;
                else if(experiments.get(i).getDay()== day)
                temp = temp.getNext();
            }
            temp.setNext(temp.getNext().getNext());

            counter--;
            return true;

        }
        return false;
    }

    /**
     *
     * @return
     */
    public String toString() {
        String output = "";

        if (head != null) {
            Node crunchifyCurrent = head.getNext();
            while (crunchifyCurrent != null) {
                output +=crunchifyCurrent.getData().toString();
                crunchifyCurrent = crunchifyCurrent.getNext();
            }
        }
        return output;
    }


    /**
     *
     * @return
     */
    public Iterator iterator() {

        return new IteratorClass<Experiment>();
    }

    /**
     *
     * @param <Experiment>
     */
    public class IteratorClass<Experiment> implements Iterator<Experiment> {

        private Node next;

        public IteratorClass() {
            next = head;
        }

        /**
         *
         * @return
         */
        public boolean hasNext() {
            return next != null;
        }

        /**
         *
         * @return
         */
        public boolean hasNextDay(){
            return next != null;
        }

        /**
         *
         * @return
         */
        public Experiment nextDay() {
            if(hasNextDay()){
                Experiment data = (Experiment)next.getData();
                next = next.getNextday();
                return data;
            }
            return null;
        }

        /**
         *
         * @return
         */
        public Experiment next() {
            if (hasNext()) {
                Experiment data = (Experiment)next.getData();
                next = next.getNext();
                return data;
            }
            return null;
        }

        /**
         *
         */
        public void remove() {

            throw new UnsupportedOperationException("Remove not implemented.");
        }
    }

}
