import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Vector weights = new Vector(Arrays.asList(1.5, 2.0, 1.0));
        System.out.println(weights);
        weights.normalize();
        System.out.println(weights);
    }
}
