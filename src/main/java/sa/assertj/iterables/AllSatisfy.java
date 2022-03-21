package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class AllSatisfy extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            Random rand = new Random();
            int element = rand.nextInt();
            for (int j = 0; j < size; j++) {
                list.add(element);
            }

            Consumer<Integer> consumer = s -> s.equals(element);

            result[i] = new Object[] { list, consumer };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).allSatisfy((Consumer<Integer>) s[1]);

    public AllSatisfy() {
        super(provider, runner);
    }
}
