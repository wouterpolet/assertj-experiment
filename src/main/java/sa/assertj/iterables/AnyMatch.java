package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class AnyMatch extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> list = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            list.add(r.nextBoolean() ? r.nextInt(Integer.MAX_VALUE) : -(r.nextInt(Integer.MAX_VALUE) + 1));
        }
        Predicate<Integer> predicate = s -> s >= 0;

        return new Object[]{list, predicate};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).anyMatch((Predicate<Integer>) s[1]);

    public AnyMatch() {
        super(provider, runner);
    }
}
