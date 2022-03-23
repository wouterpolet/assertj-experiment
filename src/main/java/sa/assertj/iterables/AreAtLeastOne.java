package sa.assertj.iterables;

import org.assertj.core.api.Condition;
import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class AreAtLeastOne extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            Random random = new Random();
            int random_index = random.nextInt(size);
            for (int j = 0; j < size; j++) {
                if (j == random_index) {
                    list.add(-(random.nextInt(Integer.MAX_VALUE) + 1));
                } else {
                    list.add(random.nextInt(Integer.MAX_VALUE));
                }
            }

            Predicate<Integer> predicate = (s -> s < 0);
            Condition<Integer> condition = new Condition<Integer>(predicate, "less than zero");

            result[i] = new Object[] { list, condition };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).areAtLeastOne((Condition<Integer>) s[1]);

    public AreAtLeastOne() {
        super(provider, runner);
    }
}
