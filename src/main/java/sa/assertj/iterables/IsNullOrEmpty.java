package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class IsNullOrEmpty extends Experiment {
    static DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        Random random = new Random();
        for (int i = 0; i < numOfSamples; i++) {
            if (random.nextBoolean()) {
                result[i] = new Object[] { new ArrayList<Integer>() };
            } else {
                result[i] = new Object[] { null };
            }
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList) s[0]).isNullOrEmpty();

    public IsNullOrEmpty() {
        super(provider, runner);
    }
}