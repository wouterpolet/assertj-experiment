package sa.assertj.strings;

import sa.assertj.Experiment;
import sa.assertj.Util;

import java.util.Arrays;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

public class IsBase64 extends Experiment {

    static DataProvider provider = (size) ->
            new Object[]{
                    Base64.getEncoder().encodeToString(Util.randomString(size).getBytes())
            };

    static AssertionRunner runner = s -> assertThat((String) s[0]).isBase64();

    public IsBase64() {
        super(provider, runner);
    }
}
