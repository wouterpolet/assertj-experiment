package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class StartsWith extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            Random random = new Random();
            int element = random.nextInt();
            list.add(element);
            for (int j = 1; j < size; j++) {
                list.add(random.nextInt(Integer.MAX_VALUE));
            }
            result[i] = new Object[] { list, element };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).startsWith((Integer) s[1]);

    public StartsWith() {
        super(provider, runner);
    }
}
