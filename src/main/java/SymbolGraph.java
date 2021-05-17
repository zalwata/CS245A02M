import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.TreeMap;

public class SymbolGraph {

    private Graph G;
    public TreeMap<String, Integer> st;
    private String[] ifk;
    int v = 0;

    /**
     * Initializes a graph from a file using the specified delimiter.
     * Each line in the file contains
     * the name of a vertex, followed by a list of the names
     * of the vertices adjacent to that vertex, separated by the delimiter.
     * @param filename the name of the file
     * @param delimiter the delimiter between fields
     */
    public SymbolGraph(String filename, String delimiter) throws FileNotFoundException, IOException {

        st = new TreeMap<>();

        // First pass builds the index by reading strings to associate
        // distinct strings with an index
        BufferedReader reader = new BufferedReader(new FileReader(
                new File(filename)));
        // while (in.hasNextLine()) {
        String line;
        int counter = 0;

        //TODO:
        // 1. make it so that this block stores everything up to ']'
        // 2. before storing, remove all non-alphabetical characters
        // 3. right now it only stores actos name.
        String charReader = "";
        String query = "";

//        while ((charReader = reader.readLine()) != null) {
//
//            //i think this might be more helpful with graph making
//            if (charReader.charAt(charReader.length() - 2) != ']') {
//                //add items
//                String[] data = charReader.split(delimiter);// EX) ""name"": ""Sam Worthington""
//                actorNameData = new String[data.length];
//
//                for (String s: data) {
//                    String[] actorNames = s.split("\\W+");
//
//                    if(s.contains("name"))
//                    {
//                        String[] findActorNames = s.split(":");
//                        String actorName = findActorNames[1];
////                        String[] actorNames = actorName.split("\\W+");
//                        String actorNameOnly = "";
//                        for(int i = 0; i < actorNames.length;i++){
//                            actorNameOnly = actorNameOnly + actorNames[i] +" ";
//                        }
//                        actorNameData[counter++] = actorNameOnly;
//                    }
//                }
//
//
//                //this finds the list of actors in one movie and loops movie by movie
//                if (charReader.charAt(charReader.length() - 2) == ']') {
//                    query = "";
//                    System.out.println("']' is found");
//                } else {
//                    query += charReader;
//                }
//            }
//        }

        try {

            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filename));
            CSVParser csvParser = CSVFormat.DEFAULT.parse(inputStreamReader);
            for (CSVRecord csvRecord : csvParser) {

                System.out.println("Column 2 : " + csvRecord.get(1)); // list of movie names

                String movieName = csvRecord.get(1);
                System.out.println(movieName);
                st.put(movieName, st.size());


                System.out.println("Column 3 : " + csvRecord.get(2)); // list of actor names

                String actorNames = csvRecord.get(2);
                String[] data = actorNames.split(delimiter);
                for (String s: data) {
                    if(s.contains("name"))
                    {
                        String[] findActorNames = s.split(":");
                        String actorName = findActorNames[1];
                        String[] actorNamesArr = actorName.split("\\W+");
                        String actorNameOnly = "";
                        for(int i = 0; i < actorNamesArr.length;i++){
                            actorNameOnly = actorNameOnly + actorNamesArr[i] +" ";
                        }
                        String trimedactorName = actorNameOnly.trim();
                        if(!st.containsKey(trimedactorName))
                            st.put(trimedactorName, st.size());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error in Parsing CSV File");
        }

//        while ((line = reader.readLine()) != null)
//        {
//            String[] data = line.split(delimiter);// EX) ""name"": ""Sam Worthington""
//            String[] actorNameData = new String[data.length*10];
//
//            for (String t: data)
//            {
//                if(t.contains(":"))
//                {
//                    String[] findActorNames = t.split(":");
//                    String actorName = findActorNames[1];
//                    String[] actorNames = actorName.split("\\W+");
//                    String actorNameOnly = "";
//                    for(int i = 0; i < actorNames.length;i++){
//                        actorNameOnly = actorNameOnly + actorNames[i] +" ";
//                    }
//                    actorNameData[counter++] = actorNameOnly;
//                    for(String s: actorNameData)
//                    {
//                        if(s != null && !st.containsKey(s))
//                            st.put(s, st.size());
//                    } //error occured at counter = 25
//                } else if(!st.containsKey(t))
//                    st.put(t, st.size());
//            }
//        }


        System.out.println("Done reading " + filename);

        ifk = new String[st.size()];
        for(String name : st.keySet())
            ifk[st.get(name)] = name;

        // second pass builds the graph by connecting first vertex on each
        // line to all others
        G = new Graph(st.size());
        reader = new BufferedReader(new FileReader(
                new File(filename)));


//        //TODO: can ']' be the deciding factor?
//        while ((line = reader.readLine()) != null) {
//            String[] data = line.split(delimiter);
//
//            // Gets the location of the movie
//            int v = st.get(data[0]);
//
//            // Links the movies and actoors together
//            for(int i = 1; i < data.length; i++){
//                int w = st.get(data[i]);
//                G.addEdge(v, w);
//            }
//        }
        counter = 0;
        try {

            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filename));
            CSVParser csvParser = CSVFormat.DEFAULT.parse(inputStreamReader);
            for (CSVRecord csvRecord : csvParser) {

                System.out.println("Column 2 : " + csvRecord.get(1)); // list of movie names

                String movieName = csvRecord.get(1);

                int v = st.get(movieName);

                System.out.println(movieName);

                System.out.println("Column 3 : " + csvRecord.get(2)); // list of actor names

                String actorNames = csvRecord.get(2);
                String[] data = actorNames.split(delimiter);
                for (String s: data) {
                    if(s.contains("name"))
                    {
                        String[] findActorNames = s.split(":");
                        String actorName = findActorNames[1];
                        String[] actorNamesArr = actorName.split("\\W+");
                        String actorNameOnly = "";
                        for(int i = 0; i < actorNamesArr.length;i++){
                            actorNameOnly = actorNameOnly + actorNamesArr[i] +" ";
                        }
                        String trimedactorName = actorNameOnly.trim();
                        int w = st.get(trimedactorName);
                        G.addEdge(v, w);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error in Parsing CSV File");
        }

//        while ((charReader = reader.readLine()) != null) {
//
//            // if it is the end of the movie Avatar list, move to the next movie
//            if (charReader.charAt(charReader.length() - 2) != ']') {
//                //add items
//                String[] data = charReader.split(delimiter);// EX) ""name"": ""Sam Worthington""
//                String[] actorNameData = new String[data.length*10];
//
//                for (String t: data)
//                {
//                    if(t.contains(":"))
//                    {
//                        String[] findActorNames = t.split(":");
//                        String actorName = findActorNames[1];
//                        String[] actorNames = actorName.split("\\W+");
//                        String actorNameOnly = "";
//                        for(int i = 0; i < actorNames.length;i++){
//                            actorNameOnly = actorNameOnly + actorNames[i] +" ";
//                        }
//
//                            int w = st.get(actorNameOnly);
//                            G.addEdge(v, w);
//
//                    } else
//                    {
//                        if(t != null)
//                        {
//                            int w = st.get(t);
//                            G.addEdge(v, w);
//                        }
//
//                    }
//                }
//
//            } else
//            {
//                v++;
//            }
//        }
    }

    /**
     * Does the graph contain the vertex named s
     * @param s the name of a vertex
     * @return true if s is the name of a vertex, and false otherwise
     */
    public boolean contains(String s) {
        return st.containsKey(s);
    }

    /**
     * Returns the integer associated with the vertex named s.
     * @param s the name of a vertex
     * @return the integer (between 0 and V - 1) associated with the vertex named s
     */
    public int index(String s) {
        return st.get(s);
    }

    /**
     * Returns the graph associated with the symbol graph. It is the client's responsibility
     * not to mutate the graph.
     * @return the graph associated with the symbol graph
     */
    public Graph G() {
        return G;
    }

    /**
     * Returns the name of the vertex associated with the integer v.
     * @param v the integer corresponding to a vertex (between 0 and V - 1)
     * @return the name of the vertex associated with the integer <tt>v</tt>
     */
    public String name(int v) {
        return ifk[v];
    }

}
