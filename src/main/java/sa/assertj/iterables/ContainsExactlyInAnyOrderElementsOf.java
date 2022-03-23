package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class ContainsExactlyInAnyOrderElementsOf extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            int element = r.nextInt();
            list1.add(element);
            list2.add(element);
        }
        Collections.shuffle(list1);
        Collections.shuffle(list1);
        return new Object[]{list1, list2};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).containsExactlyInAnyOrderElementsOf((ArrayList<Integer>) s[1]);

    public ContainsExactlyInAnyOrderElementsOf() {
        super(provider, runner);
    }
}
