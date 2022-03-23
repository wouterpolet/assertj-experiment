package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class ContainsOnlyOnce extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> list = new ArrayList<>();
        int negative_element = -(r.nextInt(Integer.MAX_VALUE) + 1);
        int random_index = r.nextInt(size);
        for (int j = 0; j < size; j++) {
            list.add(j == random_index ? negative_element : r.nextInt(Integer.MAX_VALUE));
        }
        return new Object[]{list, negative_element};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).containsOnlyOnce((Integer) s[1]);

    public ContainsOnlyOnce() {
        super(provider, runner);
    }
}
