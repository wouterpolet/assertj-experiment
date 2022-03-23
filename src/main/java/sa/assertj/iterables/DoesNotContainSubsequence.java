package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class DoesNotContainSubsequence extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            list1.add(r.nextInt(Integer.MAX_VALUE - 1) + 1);
            if (r.nextBoolean()) {
                list2.add(-(r.nextInt(Integer.MAX_VALUE - 1) + 1));
            }
        }
        return new Object[]{list1, list2};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).doesNotContainSubsequence((ArrayList<Integer>) s[1]);

    public DoesNotContainSubsequence() {
        super(provider, runner);
    }
}
