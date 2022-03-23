package sa.assertj.iterables;

import org.assertj.core.api.Condition;
import sa.assertj.Experiment;

import java.util.ArrayList;
import java.lang.Integer;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class Are extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> list = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            list.add(r.nextInt(Integer.MAX_VALUE));
        }

        Predicate<Integer> predicate = (s -> s >= 0);
        Condition<Integer> condition = new Condition<Integer>(predicate, "greater than or equal to zero");

        return new Object[]{list, condition};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).are((Condition<Integer>) s[1]);

    public Are() {
        super(provider, runner);
    }
}
