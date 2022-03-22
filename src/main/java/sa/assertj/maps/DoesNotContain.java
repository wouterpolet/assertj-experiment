package sa.assertj.maps;

import sa.assertj.Experiment;
import sa.assertj.Util;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DoesNotContain extends Experiment {

    static DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int s=0; s < numOfSamples; s++) {
            Map<String, String> actual = new LinkedHashMap<>();
            Map.Entry[] expected = new Map.Entry[size];
            for (int i=0; i < size; i++) {
                String key = Util.randomString(10);

                if (actual.containsKey(key) || Arrays.stream(expected).anyMatch(x -> x.getKey().equals(key))) {
                    i--;
                    continue;
                }

                String value = Util.randomString(10);

                actual.put(key, value);

                String randomKey = Util.randomString(10);
                String randomValue = Util.randomString(10);
                while(actual.containsKey(randomKey)) {
                    randomKey = Util.randomString(10);
                }
                expected[i] = Map.entry(randomKey, randomValue);
            }
            result[s] = new Object[] {actual, expected};
        }
        return result;
    };

    static AssertionRunner runner = s -> assertThat((Map) s[0]).doesNotContain((Map.Entry[]) s[1]);

    public DoesNotContain() {
        super(provider, runner);
    }
}
