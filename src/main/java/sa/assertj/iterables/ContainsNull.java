package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainsNull extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                list.add(null);
            }
            result[i] = new Object[] { list };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).containsNull();

    public ContainsNull() {
        super(provider, runner);
    }
}
