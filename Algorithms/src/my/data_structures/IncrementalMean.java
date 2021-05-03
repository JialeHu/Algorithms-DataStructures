package my.data_structures;

public class IncrementalMean {

    private double mean = 0d;
    private int k = 0;

    public double add(Number number) {
        this.k++;
        this.mean = this.mean + (number.doubleValue() - this.mean) / (double) this.k;
        return this.mean;
    }

    public static void main(String[] args) {
        IncrementalMean m = new IncrementalMean();
        for (int i = 0; i <= 1000000; i++) {
            double curMean = m.add(i);
            System.out.println(curMean);
        }
    }

}
