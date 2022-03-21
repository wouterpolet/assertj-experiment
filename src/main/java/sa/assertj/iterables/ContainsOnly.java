package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainsOnly extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list1 = new ArrayList<>();
            Random rand = new Random();
            int element = rand.nextInt();
            for (int j = 0; j < size; j++) {
                list1.add(element);
            }
            result[i] = new Object[] { list1, element };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).contains((Integer) s[1]);

    public ContainsOnly() {
        super(provider, runner);
    }
}
