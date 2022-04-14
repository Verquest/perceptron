import java.util.*;

public class Perceptron {
    private final Vector weights;
    private double theta;
    private final double learningRate;
    private final int size;
    public Perceptron(int size,  double learningRate, double theta) {
        Random random = new Random();
        ArrayList<Double> wagi = new ArrayList<>();
        for (int i = 0; i < size ; i++)
            wagi.add(random.nextDouble()*10-5);
        System.out.println(wagi);
        weights = new Vector(wagi);
        this.learningRate = learningRate;
        this.theta = theta;
        this.size = size;
    }

    public void learn(Vector toLearn, int expectedValue){
        double dotProduct = toLearn.dotProduct(weights);

        alterWeights(predict(toLearn), expectedValue, toLearn);
        weights.normalize();
    }

    public int predict(Vector values){
        double dotProduct = values.dotProduct(weights);
        return isOverTheta(dotProduct) ? 1: 0;
    }

    public boolean isOverTheta(double val){
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
        theta -= error * learningRate;
    }

    public void resetWeights(){
        for (int i = 0; i < size ; i++)
            weights.set(i, Math.random()*10-5);
        theta = Math.random()*10-5;
        System.out.println(weights);
        System.out.println(theta);
    }

    public Vector getWeights(){
        return weights;
    }
    public double getTheta(){
        return theta;
    }
}

