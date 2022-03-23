package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class EndsWith extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            Random random = new Random();
            for (int j = 0; j < size - 1; j++) {
                list.add(random.nextInt(Integer.MAX_VALUE));
            }
            int element = random.nextInt(Integer.MAX_VALUE);
            list.add(element);
            result[i] = new Object[] { list, element };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).endsWith((Integer) s[1]);

    public EndsWith() {
        super(provider, runner);
    }
}
