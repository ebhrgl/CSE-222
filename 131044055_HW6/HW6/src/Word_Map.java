import java.util.*;


public class Word_Map implements Map, Iterable
{

    final static int INITCAP=1;  //initial capacity
    int CURRCAP = INITCAP;   //current capacity
    final static float LOADFACT = 0.75f;
    private Node table[];
    private int countKeys;
    private int numDeletes;
   // public Word_Map map = new Word_Map();
    public Word_Map() {
        this.table = new Node[CURRCAP];
    }

    class myiterator implements Iterator{

        int count =table.length;

        public boolean hasNext(){
            return count > 0;
        }

        public Object next(){
            if(count == 0){
                throw new NoSuchElementException();
            }else{
                count--;

            }
            return null;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }
    @Override
    public Iterator iterator() {

        return new myiterator();
    }

    static class Node {
        Object key;
        Object value;
        Node next;

        @Override
        public String toString() {
            return key + " " + value;
        }
    }

    @Override
    public int size() {

        return countKeys;
    }

    @Override
    public boolean isEmpty() {

        return countKeys == 0;
    }

    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsKey(Object key) {
        // Test whether the specified key has an associated value
        // in the table.
        int index = find(key);  // In what location should key be?
        Node list = table[index];  // For traversing the list.
        while (list != null) {
            // If we find the key in this node, return true.
            if (list.key.equals(key))
                return true;
            list = list.next;
        }
        // If we get to this point, we know that the key does
        // not exist in the table.
        return false;

    }

    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsValue(Object value) {
        for (int i = table.length - 1; i >= 0; i--)
        {
            Node list = table[i];
            while (list != null)
            {
                if (list.value.equals(value))
                    return true;
                list = list.next;
            }
        }
        return false;

    }

    @Override
    public Object get(Object key) {
        // Find the first table element that is empty
        // or the table element that contains the key.
        int index = find(key);

        Node list = table[index];  // For traversing the list.
        // If the search is successful, return the value.
        while (list!= null) {
            if (list.key.equals(key))
                return list.value;
            list = list.next;  // Move on to next node in the list.
        }

        return null; // key not found.
    }

    @Override
    /*
    Use linear probing in case of collision
    * */
    public Object put(Object key, Object value) {

        // Find the first table element that is empty
        // or the table element that contains the key.
        int index = find(key);
        Node list = table[index]; // For traversing the linked list
        Node newNode = new Node();

        while (list != null) {
            // Search the nodes in the list, to see if the key already exists.
            if (list.key.equals(key))
                break;
            list = list.next;
        }
        if (list != null) {
            // Since list is not null, we have found the key.
            // Just change the associated value.
            list.value = value;
        }
        if(list == null){
            // Add a new node at the head of the list to contain the
            // new key and its associated value.
            countKeys++;  // Count the newly added key.
            double loadFactor = (double) (countKeys + numDeletes) / table.length;
            if (loadFactor > LOADFACT) {
                // The table is becoming too full.  Increase its size
                // before adding the new node.
                rehash();

            }
            newNode.key = key;
            newNode.value = value;
            newNode.next = table[index];

            table[index] = newNode;
        }


        return newNode;
    }

    @Override
    /*You do not need to implement remove function
    * */
    public Object remove(Object key) {

        int index = find(key);
        if (table[index] == null) {
            return null;
        }

        if (table[index].key.equals(key)) {

            table[index] = table[index].next;
            countKeys--; // Remove new number of items in the table.
            return null;
        }

        Node prev = table[index];  // The node that precedes
        Node curr = prev.next;  // For traversing the list,
        // starting from the second node.
        while (curr != null && ! curr.key.equals(key)) {
            curr = curr.next;
            prev = curr;
        }

        if (curr != null) {
            prev.next = curr.next;
            countKeys--;  // Record new number of items in the table.
        }


        return null;
    }

    /*I used  this code from our book Koffman */
    private void rehash() {
        // Double the size of the table, and redistribute the
        // key/value pairs to their proper locations in the
        // new table.
        Node[] newtable = table;
        table = new Node[2 * newtable.length + 1];
        countKeys = 0;
        numDeletes = 0;

        for (int i = 0; i < table.length; i++) {
            Node list = table[i]; // For traversing linked list number i.
            while (list != null) {
                // Move the node pointed to by list to the new table.
                Node next = list.next;  // The is the next node in the list.

                // the value of list
                int hash = (Math.abs(list.key.hashCode())) % newtable.length;
                list.next = newtable[hash];
                newtable[hash] = list;
                list = next;  // Move on to the next node in the OLD table.
            }
        }
        table = newtable;  // Replace the table with the new table.
    } // end rehash()

    /*I used  this code from our book Koffman */
    private int find(Object key) {
        // Calculate the starting index.
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length; // Make it positive.

        // Increment index until an empty slot is reached
        // or the key is found.
        while ( (table[index] != null)
                && (!key.equals(table[index].key))) {
            index++;
            // Check for wraparound.
            if (index >= table.length)
                index = 0; // Wrap around.
        }
        return index;
    }

    @Override
    public void putAll(Map m) {
        Node[] tab = table;
        for (int i = 0; i < tab.length ; i++) {
            Node list = table[i];
            put(list.key, list.value);

        }
        if (!m.isEmpty()) {
            m.keySet().stream()
                    .forEach((k) -> put(k, m.get(k)));
        }
        Iterator i = new myiterator();

        while (i.hasNext())
        {
            Word_Map.Node node = (Word_Map.Node)i.next();
            put(node.key, node.value);
        }


    }

    @Override
    public void clear() {
        int length = table.length;
        table = new Node[length];
        this.countKeys = 0;
    }

    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Set keySet() {

        Set<Object> set = new HashSet<>();
        Node[] tab = table;
        for (int i = 0; i < tab.length ; i++) {
            Node list = table[i];
            while (list != null) {
                // If we find the key in this node, return true.
                System.out.println("keyset-> " + list.key);
                list = list.next;
            }
        }
        return set;

    }

    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Collection values() {
        Collection collection = (Collection)new HashSet<>();
        Node[] tab = table;
        for (int i = 0; i < tab.length ; i++)
        {
            Node list = table[i];

            while (list != null)
            {
                System.out.println("value-> " + list.value);
                list = list.next;

            }
        }

        return collection;
    }

    @Override
    /*You do not need to implement entrySet function
     * */
    public Set<Entry> entrySet() {
        return null;
    }


    @Override
    public String toString() {
        return Arrays.toString(table);
    }
}
