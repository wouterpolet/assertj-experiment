package sa.assertj.maps;

import sa.assertj.Experiment;
import sa.assertj.Util;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainsAnyOf extends Experiment {

    static DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int s=0; s < numOfSamples; s++) {
            Map<String, String> actual = new LinkedHashMap<>();
            Map.Entry[] expected = new Map.Entry[size];
            for (int i=0; i < size; i++) {
                String key = Util.randomString(10);

                if (actual.containsKey(key)) {
                    i--;
                    continue;
                }

                String value = Util.randomString(10);

                actual.put(key, value);

                // Only make last one be present in map
                if (i < size - 1) {
                    key = Util.randomString(10);
                    value = Util.randomString(10);
                }
                expected[i] = Map.entry(key, value);
            }
            result[s] = new Object[] {actual, expected};
        }
        return result;
    };

    static AssertionRunner runner = s -> assertThat((Map) s[0]).containsAnyOf((Map.Entry[]) s[1]);

    public ContainsAnyOf() {
        super(provider, runner);
    }
}
