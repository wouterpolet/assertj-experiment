package sa.assertj.iterables;

import org.assertj.core.api.Condition;
import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class AreNot extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            Random rand = new Random();
            for (int j = 0; j < size; j++) {
                int element = rand.nextInt();
                if (element == 0) {
                    element++;
                }
                list.add(element);
            }

            Condition<Integer> condition = new Condition<>(s -> s.equals(0), "equals zero");

            result[i] = new Object[] { list, condition};
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).areNot((Condition<Integer>) s[1]);

    public AreNot() {
        super(provider, runner);
    }
}
