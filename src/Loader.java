import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Loader {
    public Loader(){}
    public HashMap<String, ArrayList<Vector>> load(String path){
        HashMap<String, ArrayList<Vector>> data = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                LinkedList<String> lineValues = new LinkedList<>(Arrays.asList(line.split(",")));
                String className = lineValues.removeLast();
                LinkedList<Double> vectorVals = lineValues.stream().map(Double::valueOf).collect(Collectors.toCollection(LinkedList::new));
                Vector classVector = new Vector(vectorVals);
                if(data.containsKey(className))
                    data.get(className).add(classVector);
                else
                    data.put(className, new ArrayList<>(Arrays.asList(classVector)));
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error reading training data.");
        }
        return data;
    }
}
