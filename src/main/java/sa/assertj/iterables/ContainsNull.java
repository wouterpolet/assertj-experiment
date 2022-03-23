package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class ContainsNull extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> list = new ArrayList<>();
        int random_index = r.nextInt(size);
        for (int j = 0; j < size; j++) {
            if (j == random_index) {
                list.add(null);
            } else {
                list.add(r.nextInt(Integer.MAX_VALUE));
            }
        }
        return new Object[]{list};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).containsNull();

    public ContainsNull() {
        super(provider, runner);
    }
}
