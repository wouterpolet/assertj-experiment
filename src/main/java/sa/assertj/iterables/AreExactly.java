package sa.assertj.iterables;

import org.assertj.core.api.Condition;
import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class AreExactly extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> list = new ArrayList<>();
        int negative_times = 0;
        for (int j = 0; j < size; j++) {
            if (r.nextBoolean()) {
                list.add(-(r.nextInt(Integer.MAX_VALUE) + 1));
                negative_times++;
            } else {
                list.add(r.nextInt(Integer.MAX_VALUE));
            }
        }

        Predicate<Integer> predicate = (s -> s < 0);
        Condition<Integer> condition = new Condition<Integer>(predicate, "less than zero");

        return new Object[]{list, negative_times, condition};

    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).areExactly((int) s[1], (Condition<Integer>) s[2]);

    public AreExactly() {
        super(provider, runner);
    }
}
