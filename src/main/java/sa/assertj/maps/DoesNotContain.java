package sa.assertj.maps;

import sa.assertj.Experiment;
import sa.assertj.Util;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class DoesNotContain extends Experiment {

    static DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int s=0; s < numOfSamples; s++) {
            Map<String, String> actual = new LinkedHashMap<>();

            for (int i=0; i < size; i++) {
                String key = Util.randomString(Util.STRING_LENGTH);

                if (actual.containsKey(key)) {
                    i--;
                    continue;
                }

                String value = Util.randomString(Util.STRING_LENGTH);

                actual.put(key, value);
            }
            int expectedLength = (int) (size * (1 - Util.RANDOM_SIZE_DIFF)) + r.nextInt((int) (Util.RANDOM_SIZE_DIFF * 2 * size));
            Map.Entry[] expected = new Map.Entry[expectedLength];
            for (int i=0; i < expectedLength; i++) {
                String key = Util.randomString(Util.STRING_LENGTH);

                if (actual.containsKey(key)) {
                    i--;
                    continue;
                }

                String value = Util.randomString(Util.STRING_LENGTH);

                expected[i] = Map.entry(key, value);
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
