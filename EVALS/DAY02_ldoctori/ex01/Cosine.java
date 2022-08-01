import java.util.Map;

public class Cosine {
    

    private double cosA;
    public Cosine(){
        this.cosA = 0;
    }

    public void cosCalculate(Map<String, Integer> v1, Map<String, Integer> v2) {

        double numerator = 0;
        double delimiter1 = 0;
        double delimiter2 = 0;

        for (Map.Entry<String, Integer> it : v1.entrySet()) {
            numerator += it.getValue() * v2.get(it.getKey());
        }
        for (Map.Entry<String, Integer> it : v1.entrySet()) {
            delimiter1 += it.getValue() * it.getValue();
        }
        delimiter1 = Math.sqrt(delimiter1);
        for (Map.Entry<String, Integer> it : v2.entrySet()) {
            delimiter2 += it.getValue() * it.getValue();
        }
        delimiter2 = Math.sqrt(delimiter2);
        this.cosA = numerator/(delimiter1*delimiter2);
    }

    public double getCoine() {
        return this.cosA;
    }

    

}
