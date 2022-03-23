package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class AnySatisfy extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            Random random = new Random();
            for (int j = 0; j < size; j++) {
                list.add(random.nextBoolean() ? random.nextInt(Integer.MAX_VALUE) : -(random.nextInt(Integer.MAX_VALUE) + 1));
            }

            Consumer<Integer> consumer = s -> assertThat(s).isGreaterThanOrEqualTo(0);
            result[i] = new Object[] { list, consumer };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).anySatisfy((Consumer<Integer>) s[1]);

    public AnySatisfy() {
        super(provider, runner);
    }
}
