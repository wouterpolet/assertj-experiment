package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class Contains extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> list = new ArrayList<>();
        int element = -(r.nextInt(Integer.MAX_VALUE) + 1);
        int random_index = r.nextInt(size);
        for (int j = 0; j < size; j++) {
            if (j == random_index) {
                list.add(element);
            } else {
                list.add(r.nextInt(Integer.MAX_VALUE));
            }
        }
        return new Object[]{list, element};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).contains((int) s[1]);

    public Contains() {
        super(provider, runner);
    }
}
