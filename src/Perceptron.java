import java.util.*;

public class Perceptron {
    private Vector weights;
    private double theta;
    private double learningRate;

    public Perceptron(int size,  double learningRate, double theta) {
        ArrayList<Double> wagi = new ArrayList<>();
        for (int i = 0; i < size ; i++)
            wagi.add(Math.random()*10-5);

        weights = new Vector(wagi);
        this.learningRate = learningRate;
        this.theta = theta;
    }

    public void learn(Vector toLearn, int expectedValue){
        double dotProduct = toLearn.dotProduct(weights);

        int guess = dotProduct >= theta ? 1 : 0;

        if(guess != expectedValue){
            alterWeights(evaluate(toLearn), expectedValue, toLearn);
        }
    }

    private int evaluate(Vector values){
        return values.dotProduct(weights) >= theta ? 1 : 0;
    }

    public int predict(Vector values){
        double dotProduct = values.dotProduct(weights);
        System.out.println("dot= " +dotProduct);
        System.out.println("theta= " + theta);
        return isOvertheta(dotProduct) ? 1: 0;
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
    public Vector getWeights(){
        return weights;
    }
    public double getTheta(){
        return theta;
    }
}

