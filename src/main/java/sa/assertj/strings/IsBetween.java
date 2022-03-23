package sa.assertj.strings;

import sa.assertj.Experiment;
import sa.assertj.Util;

import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

public class IsBetween extends Experiment {

    static DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int s=0; s < numOfSamples; s++) {
            String[] testcase = new String[] {Util.randomString(size), Util.randomString(size), Util.randomString(size)};
            Arrays.sort(testcase);
            result[s] = testcase;
        }
        return result;
    };

    static AssertionRunner runner = s -> assertThat((String) s[1]).isBetween((String) s[0], (String) s[2]);

    public IsBetween() {
        super(provider, runner);
    }
}
