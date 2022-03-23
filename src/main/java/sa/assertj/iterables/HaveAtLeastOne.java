package sa.assertj.iterables;

import org.assertj.core.api.Condition;
import sa.assertj.Experiment;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class HaveAtLeastOne extends Experiment {
    static DataProvider provider = (size) -> {
        ArrayList<Integer> list = new ArrayList<>();
        int negative_element = -(r.nextInt(Integer.MAX_VALUE) + 1);
        int random_index = r.nextInt(size);
        for (int j = 0; j < size; j++) {
            if (j == random_index) {
                list.add(negative_element);
            } else {
                list.add(r.nextInt(Integer.MAX_VALUE));
            }
        }

        Condition<Integer> condition = new Condition<>(s -> s < 0, "less than zero");

        return new Object[]{list, condition};
    };

    static AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).haveAtLeastOne((Condition<Integer>) s[1]);

    public HaveAtLeastOne() {
        super(provider, runner);
    }
}
