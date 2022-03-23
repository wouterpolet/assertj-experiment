package sa.assertj.iterables;

import org.assertj.core.api.Condition;
import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class Contains extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            Random random = new Random();
            int element = -(random.nextInt(Integer.MAX_VALUE) + 1);
            int random_index = random.nextInt(size);
            for (int j = 0; j < size; j++) {
                if (j == random_index) {
                    list.add(element);
                } else {
                    list.add(random.nextInt(Integer.MAX_VALUE));
                }
            }
            result[i] = new Object[] { list, element };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).contains((int) s[1]);

    public Contains() {
        super(provider, runner);
    }
}
