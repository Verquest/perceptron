import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class Vector {
    private ArrayList<Double> vals;

    public Vector(double... values) {
        vals = new ArrayList<>();
        for(double d: values)
            vals.add(d);
    }
    public Vector(Collection<Double> values) {
        vals = new ArrayList<>();
        for(double d: values)
            vals.add(d);
    }

    public void normalize(){
        ArrayList<Double> valsSquared = new ArrayList<>(vals);
        for(double d: valsSquared){
            d = d*d;
        }
        System.out.println("pow " + this);
        double value = valsSquared.stream().mapToDouble(x -> x).sum();
        System.out.println("sum " + value);
        double squareRooted = Math.sqrt(value);
        System.out.println("sqrt " + squareRooted);
        vals.stream().mapToDouble(x -> x/squareRooted);
    }

    public double multiply(Vector vector){
        ArrayList<Double> values = new ArrayList<>();
        if(vector.getSize()==this.getSize()){
            for(int i = 0; i < vector.getSize(); i++){
                values.add(vector.getVals().get(i)* this.getVals().get(i));
            }
        }
        return values.stream().mapToDouble(Double::doubleValue).sum();
    }
    public ArrayList<Double> getVals() {
        return vals;
    }
    public int getSize(){
        return vals.size();
    }

    @Override
    public String toString() {
        return vals.toString();
    }
}
