import java.io.*;

//main class to run the six degrees of kevin bacon game
public class SixDegreesOfKevinBacon {

    CombinedGraphAndTreeMap GTMObject;
    public static final String FILENAME = ".\\jsonFiles\\tmdb_5000_credits.csv";
    public static final String TESTFILENAME = ".\\jsonFiles\\Test_tmdb_5000_credits.csv";

    //constructor
    SixDegreesOfKevinBacon(String inputFileName, String delimitingStringVal) throws FileNotFoundException, IOException {
        GTMObject = new CombinedGraphAndTreeMap(inputFileName, delimitingStringVal);
    }

    //creates path between two string value
    public void createPath(String inputActorName, String inputDataToCompare) {
        GraphDataStructure graphObject = GTMObject.grabGraphObject();
        if (!GTMObject.isContainingStringVal(inputActorName)) {
            System.out.println("Current Graph does not have -> "+inputActorName);
            return;
        }

        int indexValOfActor = GTMObject.grabIndexVal(inputActorName);

        BFSGraphSearch bfsObject = new BFSGraphSearch(graphObject, indexValOfActor);

        //prints out the final result
        if (GTMObject.isContainingStringVal(inputDataToCompare)) {
            int t = GTMObject.grabIndexVal(inputDataToCompare);
            if (bfsObject.isValidPath(t)) {
                System.out.println("Path between " + GTMObject.grabTheName(indexValOfActor) + " and " + GTMObject.grabTheName(t)
                +": "+ GTMObject.grabTheName(indexValOfActor) + " --> " + GTMObject.grabTheName(t));
                for (int node : bfsObject.grabThePath(t)) {
                    if (node == indexValOfActor) {
                        System.out.print(GTMObject.grabTheName(node));
                    }
                    else {
                        System.out.print(GTMObject.grabTheName(node));
                        System.out.print(" --> ");
                    }
                }
            } else
                System.out.println("No such connection");
        } else
            System.out.println("No such actor");
    }

    public static String handleStringInput(String dataInput)
            throws NumberFormatException {

        String stringInput = handleDataForInput(dataInput);
        return stringInput;
    }

    public static String handleDataForInput(String dataInput) {
        BufferedReader readingMaterial = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print(dataInput);
        System.out.flush();
        try {
            return readingMaterial.readLine();
        } catch (Exception e) {
            return "Exception error due to -> " + e.getMessage();
        }
    }

    public static void main(String[] args) {

        //TODO:
        // 2. CS112 Parser example
        // 3. grab only actor's name
        // 6. create 'characterData' from 'data' via "," or "character" string
        // 7. error occurs when 'characterData' is added before 'data'. checkout what data holds
        // 8. instead of going through filtering out all non-alphabetical characters, you can also strengthen the search so that as long as it contains the search item.

        System.out.println("=============Java CSV Parse Example================");

        File csvFile = new File(".\\jsonFiles\\tmdb_5000_credits.csv");
//        File csvFile = new File(".\\jsonFiles\\Test_tmdb_5000_credits.csv");

        String delimitingString = ",";
        SixDegreesOfKevinBacon sixDegreesOfKevinBacon;

        //to run the csv file and record user input
        try {
            String startingActor;
            String endPointActor;
            sixDegreesOfKevinBacon = new SixDegreesOfKevinBacon(FILENAME, delimitingString);
//            sixDegreesOfKevinBacon = new SixDegreesOfKevinBacon(TESTFILENAME, delimitingString);

            System.out.println("Type name of two actors endPointActor find one of the shortest paths between them---------------");
            startingActor = handleStringInput("Enter the name (firstname lastname): ");
            endPointActor = handleStringInput("Enter the name (firstname lastname): ");
            sixDegreesOfKevinBacon.createPath(startingActor, endPointActor);



//            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(csvFile));
//            CSVParser csvParser = CSVFormat.DEFAULT.parse(inputStreamReader);
//            for (CSVRecord csvRecord : csvParser) {
////                System.out.println("Column 1 : " + csvRecord.get(0) + " | Column 2 : " + csvRecord.get(1) +
////                        "| Column 3 : " + csvRecord.get(2));
//
//                System.out.println("Column 1 : " + csvRecord.get(0));
//                System.out.println("Column 2 : " + csvRecord.get(1)); // list of movie names
//
//                String movieName = csvRecord.get(1);
//
//                System.out.println(movieName);
//
//                System.out.println("Column 3 : " + csvRecord.get(2)); // list of actor names
//
//                String actorNames = csvRecord.get(2);
//            }

        } catch (Exception e) {
            System.out.println("Error Occurred");
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
