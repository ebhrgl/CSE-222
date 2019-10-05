import java.util.*;

public class File_Map implements Map
{
    /*
    For this hashmap, you will use arraylists which will provide easy but costly implementation.
    Your should provide and explain the complexities for each method in your report.
    * */
   ArrayList<String> fnames;
   ArrayList<List<Integer>> occurances;

    @Override
    public int size() {

      return fnames.size();
    }

    @Override
    public boolean isEmpty() {
        return fnames.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        // Test whether the specified key has an associated value
        // in the table.
       int i=0;
        while (!fnames.equals( null)) {
            // If we find the key in this node, return true.
            if (fnames.get(i).equals(key))
                return true;
            i++;
        }

        return false;
    }

    @Override
    public boolean containsValue(Object value) {

        int i=0;
        while (!occurances.equals( null)) {
            // If we find the key in this node, return true.
            if (occurances.get(i).equals(value))
                return true;
            i++;
        }

        return false;
    }

    @Override
    public Object get(Object key) {
        // Find the first table element that is empty
        // or the table element that contains the key.
        int i=0;
        // If the search is successful, return the value.
        while (fnames!= null) {
            if (fnames.get(i).equals(key))
                return occurances.get(i);
          i++;
        }

        return null; // key not found.
    }

    @Override
    /*Each put operation will extend the occurance list*/
    public Object put(Object key, Object value) {

        fnames = new ArrayList<String>();
        occurances = new ArrayList<List<Integer>>();

        fnames.add(String.valueOf(key));
        //occurances.add((List<Integer>)value);
        System.out.println(fnames);

        occurances.add(fnames.indexOf(String.valueOf(key)), (List<Integer>)value);

        return occurances;
    }

    @Override
    public Object remove(Object key) {
       int i=0;
        while (!fnames.equals( null)){
            if (fnames.get(i).equals(key)) {
                int s = fnames.size();
                s--;
                i++;
                return null;
            }
        }

        return null;
    }

    @Override
    public void putAll(Map m) {
        //occurances.addAll()
    }

    @Override
    public void clear() {
        this.fnames.clear();
    }

    @Override
    public Set keySet() {

        Set<Object> set = new HashSet<>();

        for (int i = 0; i < fnames.size() ; i++) {

            if (fnames != null) {
                // If we find the key in this node, return true.
                System.out.println("keyset-> " + fnames.get(i));
            }
        }
        return set;
    }

    @Override
    public Collection values() {
        Collection collection = (Collection)new HashSet<>();

        for (int i = 0; i < occurances.size() ; i++)
        {
            if (occurances != null)
            {
                System.out.println("value-> " + occurances.get(i));
            }
        }

        return collection;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}
