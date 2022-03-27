public class Perceptron {
    private Vector weights;
    private double bias;
    private double threshold;
    private int epochs;

    public Perceptron(Vector weights, int epochs) {
        this.weights = weights;
        this.epochs = epochs;
    }
    //0' = 0 - (d-y)a
    //W' = W + (d-y)aX
    public void learn(){

    }
    //vector * weights
    public void makeGuess(){

    }
}
