package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class DoesNotContainSequence extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list1 = new ArrayList<>();
            ArrayList<Integer> list2 = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                list1.add(j);
                list2.add(-j);
            }
            result[i] = new Object[] { list1, list2 };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).doesNotContainSequence((ArrayList<Integer>) s[1]);

    public DoesNotContainSequence() {
        super(provider, runner);
    }
}
