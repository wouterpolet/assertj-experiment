package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainsAnyOf extends Experiment{
    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list1 = new ArrayList<>();
            ArrayList<Integer> list2 = new ArrayList<>();
            Random random = new Random();
            int element = random.nextInt(Integer.MAX_VALUE);
            list1.add(element);
            list2.add(element);
            for (int j = 1; j < size; j++) {
                list1.add(random.nextInt(Integer.MAX_VALUE));
                if (random.nextBoolean()) list2.add(random.nextInt(Integer.MAX_VALUE));
            }
            Collections.shuffle(list1);
            Collections.shuffle(list2);
            result[i] = new Object[] { list1, list2.toArray(Integer[]::new) };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).containsAnyOf((Integer[]) s[1]);

    public ContainsAnyOf() {
        super(provider, runner);
    }
}
