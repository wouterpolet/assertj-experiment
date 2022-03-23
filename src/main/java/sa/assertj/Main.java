package sa.assertj;

import org.reflections.Reflections;
import sa.assertj.maps.*;

import java.io.IOException;
import java.util.Set;

public class Main {
    private static int ITER_NUM = 30;
    private static int[] size = new int[] { 100000, 200000, 300000, 400000, 500000 };


    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().maxMemory());
        Experiment exp = new ContainsExactlyInAnyOrderEntriesOf();
        exp.run(700000, 30);
        try {
            exp.writeResults("out.csv");
        } catch (IOException e) {
            System.out.println("F writing to the file :(");
            e.printStackTrace();
        }
    }

    private static void runExperiments() {
//        Reflections reflections = new Reflections("sa.assertj");
//
//        Set<Class<? extends Object>> allClasses =
//                reflections.getSubTypesOf(Object.class);
//
//        System.out.println(allClasses);
    }
}
