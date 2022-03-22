package sa.assertj.maps;

import sa.assertj.Experiment;
import sa.assertj.Util;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainsExactly extends Experiment {

    static Experiment.DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int s=0; s < numOfSamples; s++) {
            Map<String, String> actual = new LinkedHashMap<>();
            Map.Entry[] expected = new Map.Entry[size];
            for (int i=0; i < size; i++) {
                String key = Util.randomString(Util.STRING_LENGTH);

                if (actual.containsKey(key)) {
                    i--;
                    continue;
                }

                String value = Util.randomString(Util.STRING_LENGTH);

                actual.put(key, value);
                expected[i] = Map.entry(key, value);
            }
            result[s] = new Object[] {actual, expected};
        }
        return result;
    };

    static AssertionRunner runner = s -> assertThat((Map) s[0]).containsExactly((Map.Entry[]) s[1]);

    public ContainsExactly() {
        super(provider, runner);
    }
}
