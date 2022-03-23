package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class StartsWith extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> list = new ArrayList<>();
        int element = r.nextInt();
        list.add(element);
        for (int j = 1; j < size; j++) {
            list.add(r.nextInt(Integer.MAX_VALUE));
        }
        return new Object[]{list, element};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).startsWith((Integer) s[1]);

    public StartsWith() {
        super(provider, runner);
    }
}
