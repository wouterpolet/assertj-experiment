package sa.assertj.iterables;

import sa.assertj.Experiment;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class IsEmpty extends Experiment {
    static DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int i = 0; i < numOfSamples; i++) {
            result[i] = new Object[] { new ArrayList() };
        }
        return result;
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList) s[0]).isEmpty();

    public IsEmpty() {
        super(provider, runner);
    }
}
