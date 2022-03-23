package sa.assertj.strings;

import sa.assertj.Experiment;
import sa.assertj.Util;

import static org.assertj.core.api.Assertions.assertThat;

public class IsLessThan extends Experiment {

    static DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int s=0; s < numOfSamples; s++) {
            String one = Util.randomString(size);
            String two = Util.randomString(size);
            if (one.compareTo(two) < 0)
                result[s] = new Object[] {one, two};
            else result[s] = new Object[] {two, one};
        }
        return result;
    };

    static AssertionRunner runner = s -> assertThat((String) s[0]).isLessThan((String) s[1]);

    public IsLessThan() {
        super(provider, runner);
    }
}