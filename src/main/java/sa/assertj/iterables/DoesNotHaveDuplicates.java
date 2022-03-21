package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class DoesNotHaveDuplicates extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            Random rand = new Random();
            for (int j = 0; j < size; j++) {
                int element = rand.nextInt();
                while (list.contains(element)) {
                    element = rand.nextInt();
                }
                list.add(element);
            }
            result[i] = new Object[] { list };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).doesNotHaveDuplicates();

    public DoesNotHaveDuplicates() {
        super(provider, runner);
    }
}
