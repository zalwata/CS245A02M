//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVRecord;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import java.io.*;
//
///**
// * An implementation of the Six Degrees of Kevin Bacon
// *
// * - task
// * -- shortest path print out.
// *
// * @author AJ
// */
//
//public class ReadDataFromJSONFile {
//
//    public static void main(String[] args) {
//
//        //TODO:
//        // 2. CS112 Parser example
//        // 3. grab only actor's name.
//
//        System.out.println("=============Java CSV Parse Example================");
//
//        File csvFile=new File(".\\jsonFiles\\tmdb_5000_credits.csv");
//        String delimiter = ",";
//        SixDegreesOfKevinBacon kv;
//
//
//        try {
//            kv = new SixDegreesOfKevinBacon();
//
//
//            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(csvFile));
//            CSVParser csvParser= CSVFormat.DEFAULT.parse(inputStreamReader);
//            for (CSVRecord csvRecord:csvParser){
//                System.out.println("Column 1 : "+csvRecord.get(0)+" | Column 2 : "+csvRecord.get(1)+
//                        "| Column 3 : "+csvRecord.get(2));
//            }
//
//        }
//        catch (Exception e){
//            System.out.println("Error in Parsing CSV File");
//        }
//
//
//
//
////        FileReader reader;
////        JSONParser jsonparser = new JSONParser();
////
////        JSONObject empjsonobj = null;
////        {
////            try {
////                reader = new FileReader(".\\jsonFiles\\tmdb_5000_credits.csv");
////                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
////                Object obj = jsonparser.parse(String.valueOf(csvParser));
////                empjsonobj = (JSONObject) obj;
////
////            } catch (FileNotFoundException e) {
////                e.printStackTrace();
////            } catch (ParseException e) {
////                e.printStackTrace();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
////
////        //fails
//////        String movie_id = (String) empjsonobj.get("movie_id");
//////        String title = (String) empjsonobj.get("title");
//////        System.out.println(movie_id + title);
////        JSONArray arr = (JSONArray) empjsonobj.get("cast");
////        for(int i = 0; i < arr.size(); i++)
////        {
////            JSONObject cast = (JSONObject) arr.get(i);
////            String cast_id = (String) cast.get("cast_id");
////            System.out.println(cast_id);
////        }
//
//
//
//    }
//
//
//}
