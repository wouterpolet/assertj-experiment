package sa.assertj.maps;

import sa.assertj.Experiment;
import sa.assertj.Util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class ContainsEntry extends Experiment {

    static DataProvider provider = (size) -> {
        Map<String, String> actual = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            String key = Util.randomString(Util.STRING_LENGTH);

            if (actual.containsKey(key)) {
                i--;
                continue;
            }

            String value = Util.randomString(Util.STRING_LENGTH);

            actual.put(key, value);
        }
        List<Map.Entry<String, String>> entries = new ArrayList<>(actual.entrySet());
        Map.Entry<String, String> entry = entries.get(r.nextInt(entries.size()));
        return new Object[]{actual, entry.getKey(), entry.getValue()};
    };

    static AssertionRunner runner = s -> assertThat((Map) s[0]).containsEntry(s[1], s[2]);

    public ContainsEntry() {
        super(provider, runner);
    }
}
