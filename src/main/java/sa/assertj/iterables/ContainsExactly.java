package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainsExactly extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list1 = new ArrayList<>();
            ArrayList<Integer> list2 = new ArrayList<>();
            Random random = new Random();
            for (int j = 0; j < size; j++) {
                int element = random.nextInt(Integer.MAX_VALUE);
                list1.add(element);
                list2.add(element);
            }
            result[i] = new Object[] { list1, list2.toArray(Integer[]::new) };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).containsExactly((Integer[]) s[1]);

    public ContainsExactly() {
        super(provider, runner);
    }
}
