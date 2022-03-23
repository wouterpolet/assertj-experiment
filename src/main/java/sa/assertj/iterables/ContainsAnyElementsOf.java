package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class ContainsAnyElementsOf extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        int element = r.nextInt();
        list1.add(element);
        list2.add(element);
        for (int j = 1; j < size; j++) {
            list1.add(r.nextInt());
            list2.add(r.nextInt());
        }

        Collections.shuffle(list1);
        Collections.shuffle(list2);
        return new Object[]{list1, list2};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).containsAnyElementsOf((ArrayList<Integer>) s[1]);

    public ContainsAnyElementsOf() {
        super(provider, runner);
    }
}
