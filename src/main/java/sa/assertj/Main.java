package sa.assertj;

import sa.assertj.maps.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Experiment exp = new DoesNotContainKeys();
        exp.run(1000, 100);
        try {
            exp.writeResults("out.csv");
        } catch (IOException e) {
            System.out.println("F writing to the file :(");
            e.printStackTrace();
        }
    }
}
