package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;

public class NoneSatisfy extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            Random rand = new Random();
            int element = 1;
            for (int j = 0; j < size; j++) {
                element = rand.nextInt();
                if (element == 0) element++;
                list.add(element);
            }
            Consumer<Integer> consumer = s -> assertThat(s).isEqualTo(0);
            result[i] = new Object[] { list, consumer };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).noneSatisfy((Consumer<Integer>) s[1]);

    public NoneSatisfy() {
        super(provider, runner);
    }
}
