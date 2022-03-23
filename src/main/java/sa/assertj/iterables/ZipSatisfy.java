package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipSatisfy extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list1 = new ArrayList<>();
            ArrayList<Integer> list2 = new ArrayList<>();
            Random random = new Random();
            int element = random.nextInt();
            for (int j = 0; j < size; j++) {
                list1.add(element);
                list2.add(element);
            }

            BiConsumer<Integer, Integer> bi_consumer = Integer::equals;

            result[i] = new Object[] { list1, list2, bi_consumer };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0])
            .zipSatisfy((ArrayList<Integer>) s[1], (BiConsumer<Integer, Integer>) s[2]);

    public ZipSatisfy() {
        super(provider, runner);
    }
}
