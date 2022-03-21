package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainsExactlyInAnyOrder extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
        ArrayList<Integer> list = new ArrayList<>();
        Random rand = new Random();
        int element = rand.nextInt();
        list.add(element);
        result[i] = new Object[] { list, element };
    }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).containsExactlyInAnyOrder((Integer) s[1]);

    public ContainsExactlyInAnyOrder() {
        super(provider, runner);
    }
}
