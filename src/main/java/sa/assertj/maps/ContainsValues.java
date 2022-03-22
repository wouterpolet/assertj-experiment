package sa.assertj.maps;

import sa.assertj.Experiment;
import sa.assertj.Util;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class ContainsValues extends Experiment {

    static DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int s=0; s < numOfSamples; s++) {
            Map<String, String> actual = new LinkedHashMap<>();
            List<String> expected = new ArrayList<>();
            for (int i=0; i < size; i++) {
                String key = Util.randomString(Util.STRING_LENGTH);

                if (actual.containsKey(key)) {
                    i--;
                    continue;
                }

                String value = Util.randomString(Util.STRING_LENGTH);

                actual.put(key, value);
                if (r.nextInt(10) >= Util.RANDOM_CHANCE) {
                    expected.add(value);
                }
            }
            Collections.shuffle(expected);
            result[s] = new Object[] {actual, expected.toArray(new String[0])};
        }
        return result;
    };

    static AssertionRunner runner = s -> assertThat((Map) s[0]).containsValues((String[]) s[1]);

    public ContainsValues() {
        super(provider, runner);
    }
}
