package sa.assertj.iterables;

import org.assertj.core.api.Condition;
import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class HaveAtMost extends Experiment {
    static DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            Random random = new Random();
            int negative_times = 0;
            for (int j = 0; j < size; j++) {
                if (random.nextBoolean()) {
                    list.add(-(random.nextInt(Integer.MAX_VALUE) + 1));
                    negative_times++;
                } else {
                    list.add(random.nextInt(Integer.MAX_VALUE));
                }
            }

            Predicate<Integer> predicate = (s -> s < 0);
            Condition<Integer> condition = new Condition<Integer>(predicate, "less than zero");

            result[i] = new Object[] { list, negative_times + random.nextInt(size - negative_times), condition };
        }
        return result;
    };

    static AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).haveAtMost((int) s[1], (Condition<Integer>) s[2]);

    public HaveAtMost() {
        super(provider, runner);
    }
}
