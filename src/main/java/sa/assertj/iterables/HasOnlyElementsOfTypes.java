package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class HasOnlyElementsOfTypes extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> list = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            list.add(r.nextInt());
        }
        return new Object[]{list, Integer.class};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0]).hasOnlyElementsOfTypes((Class) s[1]);

    public HasOnlyElementsOfTypes() {
        super(provider, runner);
    }
}
