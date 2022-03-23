package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class HasSize extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> list = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            list.add(r.nextInt(Integer.MAX_VALUE));
        }
        return new Object[]{list, size};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList) s[0]).hasSize((int) s[1]);

    public HasSize() {
        super(provider, runner);
    }
}
