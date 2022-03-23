package sa.assertj;

import sa.assertj.iterables.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Experiment exp = new SatisfiesExactlyInAnyOrder();
        exp.run(100, 100);
        try {
            exp.writeResults("out.csv");
        } catch (IOException e) {
            System.out.println("F writing to the file :(");
            e.printStackTrace();
        }
    }
}
