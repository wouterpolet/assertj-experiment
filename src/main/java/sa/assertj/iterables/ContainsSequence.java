package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class ContainsSequence extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            int element = r.nextInt(Integer.MAX_VALUE);
            list1.add(element);
            list2.add(element);
        }
        return new Object[]{list1, list2};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).containsSequence((ArrayList<Integer>) s[1]);

    public ContainsSequence() {
        super(provider, runner);
    }
}
