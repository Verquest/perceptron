import java.text.DecimalFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Loader loader = new Loader();
        HashMap<Vector, String> map = loader.load("src/training.txt");

        ArrayList<String> classes = new ArrayList<>(new HashSet<>(map.values()));
        String activationClass = classes.get(0);
        String noActivationClass = classes.get(1);

        Perceptron perceptron = new Perceptron( 4,   0.5, Math.random()*10-5);

        //learning
        for(int i = 0; i < 10; i++){
            for(Map.Entry<Vector, String> entry: map.entrySet()){
                perceptron.learn(entry.getKey(), entry.getValue().equals(activationClass) ? 1 : 0);
            }
        }
        //test set
        HashMap<Vector, String> testMap = loader.load("src/test.txt");
        double testCount = testMap.size();
        double successfulGuesses = 0;
        //testing
        for(Map.Entry<Vector, String> entry : testMap.entrySet()){
            Vector vector = entry.getKey();
            String value = entry.getValue();
            int prediction = perceptron.predict(vector);
            String classifiedAs = prediction == 1 ? activationClass : noActivationClass;
            System.out.println("prediction = " + classifiedAs + " real val = " + value);
            if (value.equals(classifiedAs)) {
                successfulGuesses++;
            }
        }
        DecimalFormat df = new DecimalFormat("00.00");
        System.out.println("success rate = " + df.format(successfulGuesses/testCount*100)+"%");
        System.out.println("Correct " + successfulGuesses + " of " + testCount);
        System.out.println("Weights: " + perceptron.getWeights());
        System.out.println("Theta: " + perceptron.getTheta());
    }
}
