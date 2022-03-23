package sa.assertj.strings;

import sa.assertj.Experiment;
import sa.assertj.Util;

import static org.assertj.core.api.Assertions.assertThat;

public class IsEqualTo extends Experiment {

    static DataProvider provider = (size) -> {
        String actual = Util.randomString(size);
        return new Object[]{actual, new String(actual)};
    };

    static AssertionRunner runner = s -> assertThat((String) s[0]).isEqualTo((String) s[1]);

    public IsEqualTo() {
        super(provider, runner);
    }
}
