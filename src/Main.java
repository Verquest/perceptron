import java.text.DecimalFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Loader loader = new Loader();
        LinkedHashMap<Vector, String> map = loader.load("src/training.txt");

        ArrayList<String> classes = new ArrayList<>(new HashSet<>(map.values()));
        String activationClass = classes.get(0);
        String noActivationClass = classes.get(1);

        Perceptron perceptron = new Perceptron( 4,   0.5, Math.random()*10-5);

        //learning
        int sampleSize = 25;
        int epochs = 100;
//        for(int i = 0; i < epochs; i++){
//            LinkedHashMap<Vector, String> probka = getSubset(scramble(map), sampleSize);
//            for(Map.Entry<Vector, String> entry: probka.entrySet()){
//                perceptron.learn(entry.getKey(), entry.getValue().equals(activationClass) ? 1 : 0);
//            }
//        }
        for(int i = 0; i < epochs; i++){
            for(Map.Entry<Vector, String> entry: map.entrySet()){
                perceptron.learn(entry.getKey(), entry.getValue().equals(activationClass) ? 1 : 0);
            }
            scramble(map);
        }
        //decision
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("1 - enter own vector\n2 - use test vectors\n3 - relearn");
            int choice = scanner.nextInt();
            if (choice == 1) {
                classify(perceptron, activationClass, noActivationClass);
            } else if (choice == 2) {
                classifyTestVectors(perceptron, activationClass, noActivationClass);
            }else{
                perceptron.resetWeights();
                for(int i = 0; i < epochs; i++){
                    LinkedHashMap<Vector, String> probka = getSubset(scramble(map), sampleSize);
                    for(Map.Entry<Vector, String> entry: probka.entrySet()){
                        perceptron.learn(entry.getKey(), entry.getValue().equals(activationClass) ? 1 : 0);
                    }
                }
            }
        }

    }
    //scramble linkedhashmap
    public static LinkedHashMap<Vector, String> scramble(LinkedHashMap<Vector, String> map){
        List<Map.Entry<Vector, String>> entries = new ArrayList<>(map.entrySet());
        Collections.shuffle(entries);
        LinkedHashMap<Vector, String> scrambled = new LinkedHashMap<>();
        for(Map.Entry<Vector, String> entry: entries){
            scrambled.put(entry.getKey(), entry.getValue());
        }
        return scrambled;
    }
    //return a map containing some of the inputed map's entries
    public static LinkedHashMap<Vector, String> getSubset(LinkedHashMap<Vector, String> map, int size){
        LinkedHashMap<Vector, String> subset = new LinkedHashMap<>();
        for(int i = 0; i < size; i++){
            subset.put((Vector) map.keySet().toArray()[i], (String) map.values().toArray()[i]);
        }
        return subset;
    }
    public static void classifyTestVectors(Perceptron perceptron, String activationClass, String noActivationClass){
        Loader loader = new Loader();
        //test set
        LinkedHashMap<Vector, String> testMap = loader.load("src/test.txt");
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

    //take input from user and classify it
    public static void classify(Perceptron perceptron, String activationClass, String noActivationClass){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a vector: ");
        String input = scanner.nextLine();
        String[] split = input.split(" ");
        double[] vals = Arrays.stream(split).mapToDouble(Double::parseDouble).toArray();
        Vector vector = new Vector(vals);
        int prediction = perceptron.predict(vector);
        System.out.println("Prediction: " + (prediction == 1 ? activationClass : noActivationClass));
    }
}
