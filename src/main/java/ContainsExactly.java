import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainsExactly extends Experiment {

    static DataProvider provider = (size, numOfSamples) -> {
        Object[][] result = new Object[numOfSamples][];
        for (int s=0; s < numOfSamples; s++) {
            Map<String, String> actual = new LinkedHashMap<>();
            Map.Entry[] expected = new Map.Entry[size];
            for (int i=0; i < size; i++) {
                // From: https://www.baeldung.com/java-random-string
                byte[] array = new byte[10]; // string will have size 10
                new Random().nextBytes(array);
                String key = new String(array, Charset.forName("UTF-8"));

                if (actual.containsKey(key)) {
                    i--;
                    continue;
                }

                new Random().nextBytes(array);
                String value = new String(array, Charset.forName("UTF-8"));

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
