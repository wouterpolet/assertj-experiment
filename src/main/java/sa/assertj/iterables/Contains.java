package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class Contains extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            Random rand = new Random();
            int element = rand.nextInt();
            list.add(element);
            for (int j = 0; j < size; j++) {
                list.add(rand.nextInt());
            }
            result[i] = new Object[] { list, element};
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).contains((int) s[1]);

    public Contains() {
        super(provider, runner);
    }
}
