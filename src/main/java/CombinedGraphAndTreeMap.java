import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.TreeMap;

public class CombinedGraphAndTreeMap {

    public TreeMap<String, Integer> treeMapObject;
    private GraphDataStructure graphObject;
    private String[] graphToTreeMapHelperArr;


    public int grabIndexVal(String inputString) {
        return treeMapObject.get(inputString);
    }

    public GraphDataStructure grabGraphObject() {
        return graphObject;
    }

    //initiate treemap from the graph and param input
    public CombinedGraphAndTreeMap(String filename, String delimiter) {

        treeMapObject = new TreeMap<>();

        //goes through the csv file with delimiter to collect the right info and generate treemap
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

        //using the generated treemap, initiate the graph
        graphToTreeMapHelperArr = new String[treeMapObject.size()];
        for(String name : treeMapObject.keySet())
            graphToTreeMapHelperArr[treeMapObject.get(name)] = name;
        graphObject = new GraphDataStructure(treeMapObject.size());
         try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filename));
            CSVParser csvParser = CSVFormat.DEFAULT.parse(inputStreamReader);
            for (CSVRecord csvRecord : csvParser) {
                String movieName = csvRecord.get(1);
                int v = treeMapObject.get(movieName);
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

    public String grabTheName(int vertexIndexVal) {
        return graphToTreeMapHelperArr[vertexIndexVal];
    }

    public boolean isContainingStringVal(String inputString) {
        return treeMapObject.containsKey(inputString);
    }



}
