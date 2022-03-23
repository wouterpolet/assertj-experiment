package sa.assertj.maps;

import sa.assertj.Experiment;
import sa.assertj.Util;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class ContainsAllEntriesOf extends Experiment {

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
            if (r.nextInt(10) >= Util.RANDOM_CHANCE) {
                expected.put(key, value);
            }
        }
        List<Map.Entry<String, String>> entries = new ArrayList<>(expected.entrySet());
        Collections.shuffle(entries);
        Map<String, String> e = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : entries) {
            e.put(entry.getKey(), entry.getValue());
        }
        return new Object[]{actual, e};
    };

    static AssertionRunner runner = s -> assertThat((Map) s[0]).containsAllEntriesOf((Map) s[1]);

    public ContainsAllEntriesOf() {
        super(provider, runner);
    }
}
