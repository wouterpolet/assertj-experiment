package sa.assertj.strings;

import sa.assertj.Experiment;
import sa.assertj.Util;

import static org.assertj.core.api.Assertions.assertThat;

public class IsGreaterThanOrEqualTo extends Experiment {

    static DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int s=0; s < numOfSamples; s++) {
            String one = Util.randomString(size);
            String two = Util.randomString(size);
            if (one.compareTo(two) < 0)
                result[s] = new Object[] {two, one};
            else result[s] = new Object[] {one, two};
        }
        return result;
    };

    static AssertionRunner runner = s -> assertThat((String) s[0]).isGreaterThanOrEqualTo((String) s[1]);

    public IsGreaterThanOrEqualTo() {
        super(provider, runner);
    }
}
