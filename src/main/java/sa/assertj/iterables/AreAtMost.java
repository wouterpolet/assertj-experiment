package sa.assertj.iterables;

import org.assertj.core.api.Condition;
import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class AreAtMost extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            Random rand = new Random();
            list.add(1);
            for (int j = 1; j < size; j++) {
                list.add(rand.nextInt());
            }

            Condition<Integer> condition = new Condition<>(s -> s.equals(0), "equals zero");

            result[i] = new Object[] { list, size, condition};
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).areAtMost((int) s[1], (Condition<Integer>) s[2]);

    public AreAtMost() {
        super(provider, runner);
    }
}
