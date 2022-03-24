package sa.assertj;

import com.google.common.reflect.ClassPath;
import sa.assertj.maps.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final int ITER_NUM = 30;
    private static final int MAX_SIZE = 5000000;
    private static final int STEP_SIZE = 250000;


    public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        runExperiments();
    }

    private static void runExperiments() throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Set<Class<? extends Object>> allClasses =
                List.of("sa.assertj.iterables", "sa.assertj.maps", "sa.assertj.strings").stream().flatMap(pack -> {
                    try {
                        return ClassPath.from(ClassLoader.getSystemClassLoader())
                                .getAllClasses()
                                .stream()
                                .filter(clazz -> clazz.getPackageName()
                                        .equalsIgnoreCase(pack))
                                .map(clazz -> clazz.load());
                    } catch (IOException e) {
                        return Stream.empty();
                    }
                }).collect(Collectors.toSet());

        int done = 0;

        for (Class<?> exp : allClasses) {
            System.out.printf("Running %s%n", exp.getName());
            runExperiment(exp);
            done++;
            System.out.printf("Done %d out of %d%n", done, allClasses.size());
        }

    }

    private static void runExperiment(Class<?> exp) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (int size = STEP_SIZE; size <= MAX_SIZE; size += STEP_SIZE) {
            Experiment e = (Experiment) exp.getConstructor().newInstance();
            e.run(size, ITER_NUM);
            e.writeResults("results/%s_%d.csv".formatted(exp.getName(), size));
        }
    }
}
