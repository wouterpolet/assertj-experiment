package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class SatisfiesExactly extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> iterable_list = new ArrayList<>();
        ArrayList<Consumer<Integer>> consumer_list = new ArrayList<>();
        Consumer<Integer> positive_consumer = s -> assertThat(s).isGreaterThanOrEqualTo(0);
        Consumer<Integer> negative_consumer = s -> assertThat(s).isLessThan(0);
        for (int j = 0; j < size; j++) {
            if (r.nextBoolean()) {
                iterable_list.add(r.nextInt(Integer.MAX_VALUE));
                consumer_list.add(positive_consumer);
            } else {
                iterable_list.add(-(r.nextInt(Integer.MAX_VALUE) + 1));
                consumer_list.add(negative_consumer);
            }
        }
        return new Object[]{iterable_list, consumer_list.toArray(Consumer[]::new)};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).satisfiesExactly((Consumer<Integer>[]) s[1]);

    public SatisfiesExactly() {
        super(provider, runner);
    }
}
