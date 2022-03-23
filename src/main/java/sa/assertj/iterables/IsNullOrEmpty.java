package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class IsNullOrEmpty extends Experiment {
    static DataProvider provider = (size) -> {
        if (r.nextBoolean()) {
            return new Object[]{new ArrayList<Integer>()};
        } else {
            return new Object[]{null};
        }
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList) s[0]).isNullOrEmpty();

    public IsNullOrEmpty() {
        super(provider, runner);
    }
}