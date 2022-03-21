package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class DoesNotHaveAnyElementsOfTypes extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list1 = new ArrayList<>();
            ArrayList<Integer> list2 = new ArrayList<>();
            Random rand = new Random();
            for (int j = 0; j < size; j++) {
                int element = rand.nextInt();
                list1.add(element);
                while (list1.contains(element)) {
                    element = rand.nextInt();
                }
                list2.add(element);
            }
            result[i] = new Object[] { list1, String.class};
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).doesNotHaveAnyElementsOfTypes((Class) s[1]);

    public DoesNotHaveAnyElementsOfTypes() {
        super(provider, runner);
    }
}
