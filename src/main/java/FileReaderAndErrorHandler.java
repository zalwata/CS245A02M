import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FileReaderAndErrorHandler {

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

    public static String handleStringInput(String dataInput)
            throws NumberFormatException {

        String stringInput = handleDataForInput(dataInput);
        return stringInput;

    }

}
