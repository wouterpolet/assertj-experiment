package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class HasExactlyElementsOfTypes extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        Class[] types = new Class[size];
        Arrays.fill(types, Integer.class);
        ArrayList<Integer> list = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            list.add(r.nextInt(Integer.MAX_VALUE));
        }
        return new Object[]{list, types};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).hasExactlyElementsOfTypes((Class[]) s[1]);

    public HasExactlyElementsOfTypes() {
        super(provider, runner);
    }
}
