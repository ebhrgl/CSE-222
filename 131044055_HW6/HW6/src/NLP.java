import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.StringTokenizer;

public class NLP
{
    public Word_Map wmap;

    private File file;
    private File[] files;
    private String[] array;
    private int dirsize=0;
    private int wordDirSize=0;
    public   int count=0;
    public float getDirsize(){
        return dirsize;
    }
    public void readDataset(String dir)
    {
        try {
            int index=0;

            file = new File(dir);
            // Reading directory contents
            files = file.listFiles();

            for (int i = 0; i < files.length; i++) {
               // System.out.println(files[i]);
                // Reading content
                BufferedReader reader = null;
                reader = new BufferedReader(new FileReader(files[i]));
                dirsize++;

                String line = null;

                while(true)
                {
                    line = reader.readLine();
                    if(line == null)
                        break;

                    String delim = "[ \n\r\t,.;\\-()\"]+"; //insert here all delimitators
                    array = line.trim().split(delim);

                     for (int j = 0; j < array.length; j++){
                         if(array[j].length() > 0){
                            // System.out.println(index+ "->" + array[j]);
                             index++;
                         }
                     }
                }
                index=0;
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    /*Finds all the bigrams starting with the given word*/
    public List<String> bigrams(String word){

        List<String> mylist = new  ArrayList<String>();
        wmap = new Word_Map();

        for (int i = 0; i < files.length; i++) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(files[i]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String line = null;

            while(true)
            {
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(line == null)
                    break;

                String delim = "[ \n\r\t,.;\\-()\"]+"; //insert here all delimitators
                array = line.trim().split(delim);

                for (int j = 0; j < array.length; j++){
                    if(array[j].length() > 0){
                        if(word.equals(array[j]) && (!word.equals(array[array.length-1])))
                        {
                            wmap.put(word,array[j+1]);
                            mylist.add(String.valueOf(wmap));
                        }

                    }
                }
            }

        }
        System.out.println(mylist);
        return mylist;
    }

    @Override
    public String toString() {
        return "NLP{" +
                "wmap=" + wmap +
                '}';
    }

    /*Calculates the tfIDF value of the given word for the given file */
    public float tfIDF(String word, String fileName){
        String[] temp;
        float TFIDF=0;
        try {
            // Reading content
            BufferedReader reader = null;
            File inFile = new File(fileName);
            //File namesFile = new File(args[
            reader = new BufferedReader(new FileReader(inFile));
            String line = null;
            float wordcount=0;
            while (true) {
                line = reader.readLine();
                if (line == null)
                    break;

                String delim = "[ \n\r\t,.;\\-()\"]+"; //insert here all delimitators
                temp = line.trim().split(delim);


                for (int j = 0; j < temp.length; j++) {
                    if (temp[j].length() > 0) {
                       // System.out.println("->" + temp[j]);
                        count++;

                        if(word.equals(temp[j]))
                        {
                            wordcount++;
                        }

                    }
                }

            }

            //System.out.println(wordcount);
            //System.out.println(count);
            //System.out.println(getDirsize());

            int wcount = findcount(word);
            double tf = wordcount / count;
            double idf = Math.log(getDirsize() / (wcount-236));

            TFIDF = (float)(tf* idf);

        }catch(Exception e) {
            e.printStackTrace();
        }
        return TFIDF;
    }

    public int findcount(String word){

        for (int i = 0; i < files.length; i++) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(files[i]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String line = null;

            while(true)
            {
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(line == null)
                    break;

                String delim = "[ \n\r\t,.;\\-()\"]+"; //insert here all delimitators
                array = line.trim().split(delim);

                for (int j = 0; j < array.length; j++){
                    if(array[j].length() > 0){
                        if(word.equals(array[j]) && (!word.equals(array[array.length-1])))
                        {
                          wordDirSize++;
                        }

                    }
                }
            }

        }
        //System.out.println(wordDirSize);
        return wordDirSize;
    }
    /*Print the WordMap by using its iterator*/
    public  void printWordMap()
    {
        Word_Map hm = new Word_Map();

        hm.put("KEY1", "VALUE1");
        hm.put("KEY2", "VALUE2");

        Word_Map.myiterator iter = (Word_Map.myiterator) hm.keySet().iterator();

        while(iter.hasNext()) {

            Word_Map.Node entry = (Word_Map.Node) iter.next();
            System.out.println(entry.key + " - " + entry.value);

        }
    }

}
