import java.util.*;

public class Perceptron {
    private Vector weights;
    private double theta;
    private double learningRate;
    private int epochs;
    private HashMap<Vector, String> data;
    private String activationClassName;
    private String noActivationClassName;
    public Perceptron(int size,  int epochs, HashMap<Vector, String> data, double learningRate, double theta) {
        ArrayList<Double> wagi = new ArrayList<>();
        for (int i = 0; i < size ; i++)
            wagi.add(Math.random()*10-5);
        weights = new Vector(wagi);

        this.epochs = epochs;
        this.data = data;
        this.learningRate = learningRate;
        this.theta = theta;
        ArrayList<String> classes = new ArrayList<String>(Arrays.asList(data.values().toArray(new String[0])));
        classes = new ArrayList<>(new HashSet<>(classes));
        activationClassName = classes.get(0);
        noActivationClassName = classes.get(1);
    }

    public void learn(){
        for (int i = 0; i < epochs; i++){
            for(Map.Entry<Vector, String> entry: data.entrySet()){
                String currentClass = entry.getValue();
                Vector currentVector = entry.getKey();
                double dotProduct = currentVector.dotProduct(weights);

                int correctAnswer = 0;
                int guess = dotProduct >= theta ? 1 : 0;

                if(currentClass.equals(activationClassName))
                    correctAnswer = 1;

                if(guess != correctAnswer){
                    alterWeights(evaluate(currentVector), correctAnswer, currentVector);
                }
            }
        }
    }

    private int evaluate(Vector values){
        return values.dotProduct(weights) >= theta ? 1 : 0;
    }

    public String predict(Vector values){
        double dotProduct = values.dotProduct(weights);
        System.out.println("dot= " +dotProduct);
        System.out.println("theta= " + theta);
        return isOvertheta(dotProduct) ? activationClassName: noActivationClassName;
    }
    public boolean isOvertheta(double val){
        return val > theta;
    }

    //0' = 0 - (d-y)a
    //W' = W + (d-y)aX
    public void alterWeights(int guessedOutput, int correctOutput, Vector x){
        double error = correctOutput - guessedOutput;
        for (int i = 0; i < x.getSize(); i++) {
            double toAdd = error * learningRate * x.get(i);
            weights.vals.set(i, weights.get(i) + error * learningRate * x.get(i));
        }
        //theta
        theta -= error * learningRate;
        System.out.println("theta = " + theta);
    }
}

