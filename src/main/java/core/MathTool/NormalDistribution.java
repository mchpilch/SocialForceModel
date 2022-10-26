package core.MathTool;

import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;

public class NormalDistribution {
    static double mid = 22;
    static double coeff = 2;

    public static void main(String[] args) {
        int count = 20_000; // Generated random numbers
        double mid = 23;
        double coeff = 2;
        double lowest = mid;  // For statistics
        double highest = mid;
        double average = mid;
        Random random = new Random();

        for (int i = 0; i < count; ++i) {
            double gaussian = coeff * random.nextGaussian() + mid;
            average += gaussian;
            lowest = Math.min(gaussian, lowest);
            highest = Math.max(gaussian, highest);
            if (i%10 == 0) { // New line
                System.out.println();
            }
            System.out.printf("%10.4f", gaussian);
        }
        // Display statistics
        System.out.println("\n\nNumber of generated random values following Gaussian distribution: " + count);
        System.out.printf("\nLowest value:  %10.4f\nHighest value: %10.4f\nAverage:       %10.4f", lowest, highest, (average/count));
    }

    public static float generateRandomBMI(){
        double gaussian = coeff * random.nextGaussian() + mid;
        return (float) gaussian;
    }
}
