package sa.assertj.iterables;

import org.assertj.core.api.Condition;
import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class AllMatch extends Experiment {
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            Random rand = new Random();
            int element = rand.nextInt();
            for (int j = 0; j < size; j++) {
                list.add(element);
            }

            Predicate<Integer> predicate = s -> s.equals(element);

            result[i] = new Object[] { list, predicate };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).allMatch((Predicate<Integer>) s[1]);

    public AllMatch() {
        super(provider, runner);
    }
}
