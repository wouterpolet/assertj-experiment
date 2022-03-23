package sa.assertj.strings;

import sa.assertj.Experiment;
import sa.assertj.Util;

import static org.assertj.core.api.Assertions.assertThat;

public class IsEqualTo extends Experiment {

    static DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int s=0; s < numOfSamples; s++) {
            String actual = Util.randomString(size);
            result[s] = new Object[] {actual, new String(actual)};
        }
        return result;
    };

    static AssertionRunner runner = s -> assertThat((String) s[0]).isEqualTo((String) s[1]);

    public IsEqualTo() {
        super(provider, runner);
    }
}
