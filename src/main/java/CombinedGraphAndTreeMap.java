import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.TreeMap;

public class CombinedGraphAndTreeMap {

    private GraphDataStructure graphObject;
    public TreeMap<String, Integer> treeMapObject;
    private String[] graphToTreeMapHelperArr;

    public CombinedGraphAndTreeMap(String filename, String delimiter) throws FileNotFoundException, IOException {

        treeMapObject = new TreeMap<>();

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filename));
            CSVParser csvParser = CSVFormat.DEFAULT.parse(inputStreamReader);
            for (CSVRecord csvRecord : csvParser) {
                String movieName = csvRecord.get(1);
                treeMapObject.put(movieName, treeMapObject.size());
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
                        if(!treeMapObject.containsKey(trimedactorName))
                            treeMapObject.put(trimedactorName, treeMapObject.size());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        graphToTreeMapHelperArr = new String[treeMapObject.size()];
        for(String name : treeMapObject.keySet())
            graphToTreeMapHelperArr[treeMapObject.get(name)] = name;
        graphObject = new GraphDataStructure(treeMapObject.size());
         try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filename));
            CSVParser csvParser = CSVFormat.DEFAULT.parse(inputStreamReader);
            for (CSVRecord csvRecord : csvParser) {
                System.out.println("Column 2 : " + csvRecord.get(1)); // list of movie names
                String movieName = csvRecord.get(1);
                int v = treeMapObject.get(movieName);
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
                        int w = treeMapObject.get(trimedactorName);
                        graphObject.initiateToAddEdge(v, w);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public boolean isContainingStringVal(String inputString) {
        return treeMapObject.containsKey(inputString);
    }

    public int grabIndexVal(String inputString) {
        return treeMapObject.get(inputString);
    }

    public GraphDataStructure grabGraphObject() {
        return graphObject;
    }

    public String grabTheName(int vertexIndexVal) {
        return graphToTreeMapHelperArr[vertexIndexVal];
    }

}
