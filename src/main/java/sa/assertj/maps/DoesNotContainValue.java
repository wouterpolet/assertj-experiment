package sa.assertj.maps;

import sa.assertj.Experiment;
import sa.assertj.Util;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DoesNotContainValue extends Experiment {

    static DataProvider provider = (size) -> {
        Map<String, String> actual = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            String key = Util.randomString(Util.STRING_LENGTH);

            if (actual.containsKey(key)) {
                i--;
                continue;
            }

            String value = Util.randomString(Util.STRING_LENGTH);

            actual.put(key, value);
        }
        String value;
        do {
            value = Util.randomString(Util.STRING_LENGTH);
        } while (actual.containsValue(value));
        return new Object[]{actual, value};
    };

    static AssertionRunner runner = s -> assertThat((Map) s[0]).doesNotContainValue(s[1]);

    public DoesNotContainValue() {
        super(provider, runner);
    }
}
