import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Experiment exp = new ContainsExactly();
        exp.run(100000, 100);
        try {
            exp.writeResults("out.csv");
        } catch (IOException e) {
            System.out.println("F writing to the file :(");
            e.printStackTrace();
        }

    }
}
