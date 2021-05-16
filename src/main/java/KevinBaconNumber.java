import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;

public class KevinBaconNumber {

    SymbolGraph sg;
    public static final String FILENAME =".\\jsonFiles\\tmdb_5000_credits.csv";


    KevinBaconNumber(String filename, String delimiter) throws FileNotFoundException, IOException {
        sg = new SymbolGraph(filename, delimiter);
    }

    public void path(String source, String sink){
        Graph G = sg.G();
        if (!sg.contains(source)) {
            System.out.println(source + " not in database.");
            return;
        }

        int s = sg.index(source);

        // Do a Breadth First Search
        BreadthFirstSearch bfs = new BreadthFirstSearch(G, s);

        System.out.println("");
        if(sg.contains(sink)){
            int t = sg.index(sink);
            if(bfs.hasPathTo(t)){
                System.out.println(sg.name(s) + " --> " + sg.name(t) + " || Bacon number = " + (bfs.dt(t)/2));
                for(int x : bfs.pathTo(t)){
                    if(x == s)
                        System.out.println(sg.name(x));                 // Kevin Bacon
                    else{
                        if(bfs.dt(x) % 2 != 0)
                            System.out.println("   " + sg.name(x));     // Indents movie names
                        else
                            System.out.println(sg.name(x));
                    }
                }
            }
            else
                System.out.println("Not connected");
        }
        else
            System.out.println("Not in database");
    }

    public static void main(String[] args) {

        //TODO:
        // 2. CS112 Parser example
        // 3. grab only actor's name
        // 4. make the second version of github repo before turning in and remove this repo.

        System.out.println("=============Java CSV Parse Example================");

        File csvFile=new File(".\\jsonFiles\\tmdb_5000_credits.csv");
        String delimiter = ",";
        KevinBaconNumber kv;


        try {
            String from;
            String to;
            kv = new KevinBaconNumber(FILENAME, delimiter);
            System.out.println("Type name of two actors to find one of the shortest paths between them---------------");
            from = InputHelper.getStringInput("Enter the name (lastname, firstname): ");
            to = InputHelper.getStringInput("Enter the name (lastname, firstname): ");
            kv.path(from, to);


//            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(csvFile));
//            CSVParser csvParser= CSVFormat.DEFAULT.parse(inputStreamReader);
//            for (CSVRecord csvRecord:csvParser){
//                System.out.println("Column 1 : "+csvRecord.get(0)+" | Column 2 : "+csvRecord.get(1)+
//                        "| Column 3 : "+csvRecord.get(2));
//            }

        }
        catch (Exception e){
            System.out.println("Error in Parsing CSV File");
        }




//        FileReader reader;
//        JSONParser jsonparser = new JSONParser();
//
//        JSONObject empjsonobj = null;
//        {
//            try {
//                reader = new FileReader(".\\jsonFiles\\tmdb_5000_credits.csv");
//                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
//                Object obj = jsonparser.parse(String.valueOf(csvParser));
//                empjsonobj = (JSONObject) obj;
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (ParseException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        //fails
////        String movie_id = (String) empjsonobj.get("movie_id");
////        String title = (String) empjsonobj.get("title");
////        System.out.println(movie_id + title);
//        JSONArray arr = (JSONArray) empjsonobj.get("cast");
//        for(int i = 0; i < arr.size(); i++)
//        {
//            JSONObject cast = (JSONObject) arr.get(i);
//            String cast_id = (String) cast.get("cast_id");
//            System.out.println(cast_id);
//        }



    }
}
