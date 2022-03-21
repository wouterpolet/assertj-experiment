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
            Random rand = new Random();
            int element;
            list.add(0);
            for (int j = 1; j < size; j++) {
                element = rand.nextInt();
                if (element == 0) element++;
                list.add(element);
            }
            result[i] = new Object[] { list, 0 };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).containsOnlyOnce((Integer) s[1]);

    public ContainsOnlyOnce() {
        super(provider, runner);
    }
}
