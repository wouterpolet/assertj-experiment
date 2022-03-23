package sa.assertj.maps;

import sa.assertj.Experiment;
import sa.assertj.Util;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainsExactlyInAnyOrderEntriesOf extends Experiment {

    static DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int s=0; s < numOfSamples; s++) {
            Map<String, String> actual = new LinkedHashMap<>();
            Map<String, String> expected = new LinkedHashMap<>();
            for (int i=0; i < size; i++) {
                String key = Util.randomString(Util.STRING_LENGTH);

                if (actual.containsKey(key)) {
                    i--;
                    continue;
                }

                String value = Util.randomString(Util.STRING_LENGTH);

                actual.put(key, value);
                expected.put(key, value);
            }
            List<Map.Entry<String, String>> entries = new ArrayList<>(expected.entrySet());
            Collections.shuffle(entries);
            Map<String, String> e = new LinkedHashMap<>();
            for (Map.Entry<String, String> entry : entries) {
                e.put(entry.getKey(), entry.getValue());
            }
            result[s] = new Object[] {actual, e};
        }
        return result;
    };

    static AssertionRunner runner = s -> assertThat((Map) s[0]).containsExactlyInAnyOrderEntriesOf((Map) s[1]);

    public ContainsExactlyInAnyOrderEntriesOf() {
        super(provider, runner);
    }
}
