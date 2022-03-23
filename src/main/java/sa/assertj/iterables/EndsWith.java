package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class EndsWith extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> list = new ArrayList<>();
        for (int j = 0; j < size - 1; j++) {
            list.add(r.nextInt(Integer.MAX_VALUE));
        }
        int element = r.nextInt(Integer.MAX_VALUE);
        list.add(element);
        return new Object[]{list, element};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).endsWith((Integer) s[1]);

    public EndsWith() {
        super(provider, runner);
    }
}
