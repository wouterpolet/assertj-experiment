package sa.assertj.iterables;

import org.assertj.core.api.Condition;
import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class HaveAtLeast extends Experiment {
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

            Condition<Integer> condition = new Condition<>(s -> s < 0, "less than zero");

            result[i] = new Object[] { list, negative_times - random.nextInt(negative_times), condition };
        }
        return result;
    };

    static AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).haveAtLeast((int) s[1], (Condition<Integer>) s[2]);

    public HaveAtLeast() {
        super(provider, runner);
    }
}
