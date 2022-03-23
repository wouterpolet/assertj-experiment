package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class AllSatisfy extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> list = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            list.add(r.nextInt(Integer.MAX_VALUE));
        }

        Consumer<Integer> consumer = s -> assertThat(s).isGreaterThanOrEqualTo(0);

        return new Object[]{list, consumer};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).allSatisfy((Consumer<Integer>) s[1]);

    public AllSatisfy() {
        super(provider, runner);
    }
}
