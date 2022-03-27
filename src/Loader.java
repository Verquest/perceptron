import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Loader {
    public Loader(){}
    public LinkedHashMap<Vector, String> load(String path){
        LinkedHashMap<Vector, String> data = new LinkedHashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                LinkedList<String> lineValues = new LinkedList<>(Arrays.asList(line.split(",")));
                String className = lineValues.removeLast();
                LinkedList<Double> vectorVals = lineValues.stream().map(Double::valueOf).collect(Collectors.toCollection(LinkedList::new));
                Vector classVector = new Vector(vectorVals);
                classVector.normalize();
                if(!data.containsKey(classVector))
                    data.put(classVector, className);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error reading training data.");
        }
        return data;
    }
}
