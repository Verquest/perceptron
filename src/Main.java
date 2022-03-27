import java.text.DecimalFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Loader loader = new Loader();
        LinkedHashMap<Vector, String> map = loader.load("src/training.txt");
        System.out.println("map size " + map.size());
        Perceptron perceptron = new Perceptron( 4, 3, map, 0.05, Math.random()*10-5);
        perceptron.learn();
        //test set
        LinkedHashMap<Vector, String> testMap = loader.load("src/test.txt");
        double testCount = testMap.size();
        double successfulGuesses = 0;
        for(Map.Entry<Vector, String> entry : testMap.entrySet()){
            Vector vector = entry.getKey();
            String value = entry.getValue();
            String prediction = perceptron.predict(vector);
            System.out.println("prediction = " + prediction + " real val = " + value);
            if (value.equals(prediction)) {
                successfulGuesses++;
            }
        }
        DecimalFormat df = new DecimalFormat("00.00");
        System.out.println("success rate = " + df.format(successfulGuesses/testCount*100)+"%");
        System.out.println("Correct " + successfulGuesses + " of " + testCount);
    }
}
