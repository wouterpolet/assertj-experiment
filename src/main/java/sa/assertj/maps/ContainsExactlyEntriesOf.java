package sa.assertj.maps;

import sa.assertj.Experiment;
import sa.assertj.Util;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainsExactlyEntriesOf extends Experiment {

    static DataProvider provider = (size) -> {
        Map<String, String> actual = new LinkedHashMap<>();
        Map<String, String> expected = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            String key = Util.randomString(Util.STRING_LENGTH);

            if (actual.containsKey(key)) {
                i--;
                continue;
            }

            String value = Util.randomString(Util.STRING_LENGTH);

            actual.put(key, value);
            expected.put(key, value);
        }
        return new Object[]{actual, expected};
    };

    static AssertionRunner runner = s -> assertThat((Map) s[0]).containsExactlyEntriesOf((Map) s[1]);

    public ContainsExactlyEntriesOf() {
        super(provider, runner);
    }
}
