package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class HasOnlyElementsOfTypes extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list1 = new ArrayList<>();
            Random rand = new Random();
            for (int j = 0; j < size; j++) {
                int element = rand.nextInt();
                list1.add(element);
            }
            result[i] = new Object[] { list1, Integer.class};
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).hasOnlyElementsOfTypes((Class) s[1]);

    public HasOnlyElementsOfTypes() {
        super(provider, runner);
    }
}
