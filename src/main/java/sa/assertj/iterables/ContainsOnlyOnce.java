package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainsOnlyOnce extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            Random random = new Random();
            int negative_element = -(random.nextInt(Integer.MAX_VALUE) + 1);
            int random_index = random.nextInt(size);
            for (int j = 0; j < size; j++) {
                list.add(j == random_index ? negative_element : random.nextInt(Integer.MAX_VALUE));
            }
            result[i] = new Object[] { list, negative_element };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).containsOnlyOnce((Integer) s[1]);

    public ContainsOnlyOnce() {
        super(provider, runner);
    }
}
