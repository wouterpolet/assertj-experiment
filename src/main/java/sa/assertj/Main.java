package sa.assertj;

import sa.assertj.strings.IsBase64;
import sa.assertj.strings.IsBetween;
import sa.assertj.strings.IsStrictlyBetween;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Experiment exp = new IsBase64();
        exp.run(100000, 100);
        try {
            exp.writeResults("out.csv");
        } catch (IOException e) {
            System.out.println("F writing to the file :(");
            e.printStackTrace();
        }
    }
}
