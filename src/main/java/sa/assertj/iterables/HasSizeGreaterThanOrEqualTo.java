package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class HasSizeGreaterThanOrEqualTo extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            Random random = new Random();
            for (int j = 0; j < size; j++) {
                list.add(random.nextInt(Integer.MAX_VALUE));
            }
            result[i] = new Object[] { list, random.nextBoolean() ? size : size - 1};
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).hasSizeGreaterThanOrEqualTo((int) s[1]);

    public HasSizeGreaterThanOrEqualTo() {
        super(provider, runner);
    }
}
