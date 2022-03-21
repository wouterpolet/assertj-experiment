package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainsAnyElementsOf extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list1 = new ArrayList<>();
            ArrayList<Integer> list2 = new ArrayList<>();
            Random rand = new Random();
            int element = rand.nextInt();
            list1.add(element);
            list2.add(element);
            for (int j = 1; j < size; j++) {
                list1.add(rand.nextInt());
            }
            result[i] = new Object[] { list1, list2 };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).containsAnyElementsOf((ArrayList<Integer>) s[1]);

    public ContainsAnyElementsOf() {
        super(provider, runner);
    }
}
