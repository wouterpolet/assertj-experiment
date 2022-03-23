package sa.assertj.maps;

import sa.assertj.Experiment;
import sa.assertj.Util;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class ContainsKey extends Experiment {

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
            List<String> keys = new ArrayList<>(actual.keySet());
            result[s] = new Object[] {actual, keys.get(r.nextInt(keys.size()))};
        }
        return result;
    };

    static AssertionRunner runner = s -> assertThat((Map) s[0]).containsKey(s[1]);

    public ContainsKey() {
        super(provider, runner);
    }
}
