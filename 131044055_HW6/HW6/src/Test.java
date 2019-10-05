import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args){
        String[] temp;
        try {

            NLP x = new NLP();
            String maindirpath = "dataset";
            x.readDataset(maindirpath);
            // Reading content
            File filename = new File(args[0]);
            File inFile = new File(String.valueOf(filename));
            String line = null;

            String[] broken_text = null; String text = "";
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            while((text = reader.readLine()) != null) {
                broken_text =    text.split(" ");
                String first_key = broken_text[0];
                String second_key = broken_text[1];

                if (first_key.equals("bigram")) {
                    x.bigrams(second_key);
                }

                else if(first_key.equals("tfidf")){
                    String third_key = broken_text[2];
                    System.out.println(x.tfIDF(second_key,third_key));
                }
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

       /* File_Map file = new File_Map();
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        file.put("key",list);
        System.out.println(file.get("key"));
        file.remove("key");
        System.out.println(file.size());

        System.out.println(file.keySet());
        System.out.println(file.values());
        System.out.println(file.size());
        System.out.println(file.containsKey("key"));
        file.clear();
        System.out.println(file.size());*/

       /* Word_Map table = new Word_Map();

        Word_Map newmap = new Word_Map();

        table.put("ABC","DEF");
        table.put("123","123");
        table.put("000","000");
        table.put("012","013");
        table.put("456","789");

        System.out.println("Initial Mappings are: " + table.get("ABC"));
        table.values();
        table.keySet();

        newmap.putAll(table);
        System.out.println(newmap);

        System.out.println("Initial Mappings are: " + table.values());

        table.clear();
        System.out.println("Initial Mappings are: " + table.get("ABC"));

        System.out.println(table.containsKey("ABC"));
        System.out.println(table.containsValue("789"));

        System.out.println(table.containsValue("ben"));
        table.get(table);
        System.out.println("\nHash table size is = " + table.size());
        System.out.println("\nHash get = " + table.get("ABC"));
        table.remove("ABC");

        table.remove("123");
        System.out.println(table.containsKey("ABC"));
        System.out.println(table.containsValue("DEF"));
        System.out.println("\nHash table size is = " + table.size());
        System.out.println("\nHash get = " + table.get("123"));
        System.out.println("\nHash get = " + table.get("ABC"));*/

    }

}
