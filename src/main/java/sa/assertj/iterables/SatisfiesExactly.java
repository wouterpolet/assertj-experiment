package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

public class SatisfiesExactly extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> iterable_list = new ArrayList<>();
            ArrayList<Consumer<Integer>> consumer_list = new ArrayList<>();
            Consumer<Integer> positive_consumer = s -> assertThat(s).isGreaterThanOrEqualTo(0);
            Consumer<Integer> negative_consumer = s -> assertThat(s).isLessThan(0);
            Random random = new Random();
            for (int j = 0; j < size; j++) {
                if (random.nextBoolean()) {
                    iterable_list.add(random.nextInt(Integer.MAX_VALUE));
                    consumer_list.add(positive_consumer);
                } else {
                    iterable_list.add(-(random.nextInt(Integer.MAX_VALUE) + 1));
                    consumer_list.add(negative_consumer);
                }
            }
            result[i] = new Object[] { iterable_list, consumer_list.toArray(Consumer[]::new) };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).satisfiesExactly((Consumer<Integer>[]) s[1]);

    public SatisfiesExactly() {
        super(provider, runner);
    }
}
