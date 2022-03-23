package sa.assertj.iterables;

import org.assertj.core.api.Condition;
import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class AreAtLeastOne extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> list = new ArrayList<>();
        int random_index = r.nextInt(size);
        for (int j = 0; j < size; j++) {
            if (j == random_index) {
                list.add(-(r.nextInt(Integer.MAX_VALUE) + 1));
            } else {
                list.add(r.nextInt(Integer.MAX_VALUE));
            }
        }

        Predicate<Integer> predicate = (s -> s < 0);
        Condition<Integer> condition = new Condition<Integer>(predicate, "less than zero");

        return new Object[]{list, condition};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).areAtLeastOne((Condition<Integer>) s[1]);

    public AreAtLeastOne() {
        super(provider, runner);
    }
}
