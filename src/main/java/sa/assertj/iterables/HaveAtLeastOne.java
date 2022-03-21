package sa.assertj.iterables;

import org.assertj.core.api.Condition;
import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class HaveAtLeastOne extends Experiment {
    static DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            Random rand = new Random();
            int element = rand.nextInt();
            for (int j = 0; j < size; j++) {
                list.add(element);
            }

            Condition<Integer> condition = new Condition<>(s -> s.equals(element), "equals");

            result[i] = new Object[] { list, condition};
        }
        return result;
    };

    static AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).haveAtLeastOne((Condition<Integer>) s[1]);

    public HaveAtLeastOne() {
        super(provider, runner);
    }
}
