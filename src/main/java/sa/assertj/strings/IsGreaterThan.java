package sa.assertj.strings;

import sa.assertj.Experiment;
import sa.assertj.Util;

import static org.assertj.core.api.Assertions.assertThat;

public class IsGreaterThan extends Experiment {

    static DataProvider provider = (size) -> {
        String one = Util.randomString(size);
        String two = Util.randomString(size);
        if (one.compareTo(two) > 0)
            return new Object[]{one, two};
        else return new Object[]{two, one};
    };

    static AssertionRunner runner = s -> assertThat((String) s[0]).isGreaterThan((String) s[1]);

    public IsGreaterThan() {
        super(provider, runner);
    }
}
