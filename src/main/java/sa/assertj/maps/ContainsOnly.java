package sa.assertj.maps;

import sa.assertj.Experiment;
import sa.assertj.Util;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainsOnly extends Experiment {

    static DataProvider provider = (size) -> {
        Map<String, String> actual = new LinkedHashMap<>();
        List<Map.Entry<String, String>> entries = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String key = Util.randomString(Util.STRING_LENGTH);

            if (actual.containsKey(key)) {
                i--;
                continue;
            }

            String value = Util.randomString(Util.STRING_LENGTH);

            actual.put(key, value);
            entries.add(Map.entry(key, value));
        }
        Collections.shuffle(entries);
        return new Object[]{actual, entries.toArray(new Map.Entry[0])};
    };

    static AssertionRunner runner = s -> assertThat((Map) s[0]).containsOnly((Map.Entry[]) s[1]);

    public ContainsOnly() {
        super(provider, runner);
    }
}
