package sa.assertj.strings;

import sa.assertj.Experiment;
import sa.assertj.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class IsStrictlyBetween extends Experiment {

    static DataProvider provider = (size) -> {
        List<String> testcase = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String gen = Util.randomString(size);
            if (testcase.contains(gen)) i--;
            else testcase.add(gen);
        }
        Collections.sort(testcase);
        return testcase.toArray();
    };

    static AssertionRunner runner = s -> assertThat((String) s[1]).isStrictlyBetween((String) s[0], (String) s[2]);

    public IsStrictlyBetween() {
        super(provider, runner);
    }
}
