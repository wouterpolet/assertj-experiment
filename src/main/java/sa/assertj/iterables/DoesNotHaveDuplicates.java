package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class DoesNotHaveDuplicates extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> list = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            int element = r.nextInt();
            while (list.contains(element)) {
                element = r.nextInt();
            }
            list.add(element);
        }
        return new Object[]{list};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).doesNotHaveDuplicates();

    public DoesNotHaveDuplicates() {
        super(provider, runner);
    }
}
