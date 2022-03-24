package sa.assertj;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Main {
    private static final int ITER_NUM = 30;
    private static final int MAX_SIZE = 200000;
    private static final int STEP_SIZE = 10000;


    public static void main(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        runExperiments();
    }

    private static void runExperiments() throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Taking too long: sa.assertj.iterables.ContainsAll
        List<? extends Class<?>> allClasses =
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
                }).sorted(Comparator.comparing((Function<Class<?>, String>) Class::getName)).skip(53).toList();

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
            try {
                // When assertion takes over 15 seconds, stop
                e.run(size, ITER_NUM, 15L * 1000000000L);
            } catch (RuntimeException | OutOfMemoryError exception) {
                System.out.println("Aborted.");
                break;
            } finally {
                e.writeResults("results/%s_%d.csv".formatted(exp.getName(), size));
            }
        }
    }
}
