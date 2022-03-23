package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class HasSizeLessThanOrEqualTo extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> list = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            list.add(r.nextInt(Integer.MAX_VALUE));
        }
        return new Object[]{list, r.nextBoolean() ? size : size + 1};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).hasSizeLessThanOrEqualTo((int) s[1]);

    public HasSizeLessThanOrEqualTo() {
        super(provider, runner);
    }
}
