package sa.assertj.maps;

import sa.assertj.Experiment;
import sa.assertj.Util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class ContainsAnyOf extends Experiment {

    static DataProvider provider = (size) -> {
        Map<String, String> actual = new LinkedHashMap<>();
        Map.Entry[] expected = new Map.Entry[size];
        for (int i = 0; i < size; i++) {
            String key = Util.randomString(Util.STRING_LENGTH);

            if (actual.containsKey(key)) {
                i--;
                continue;
            }

            String value = Util.randomString(Util.STRING_LENGTH);

            actual.put(key, value);

            key = Util.randomString(Util.STRING_LENGTH);
            value = Util.randomString(Util.STRING_LENGTH);
            expected[i] = Map.entry(key, value);
        }
        // Get random entry
        List<Map.Entry<String, String>> entries = new ArrayList<>(actual.entrySet());
        int sample = r.nextInt(entries.size());
        Map.Entry<String, String> entry = entries.get(sample);
        // Put at random place in expected
        int index = r.nextInt(expected.length);
        expected[index] = Map.entry(entry.getKey(), entry.getValue());
        return new Object[]{actual, expected};
    };

    static AssertionRunner runner = s -> assertThat((Map) s[0]).containsAnyOf((Map.Entry[]) s[1]);

    public ContainsAnyOf() {
        super(provider, runner);
    }
}
