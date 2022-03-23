package sa.assertj.iterables;

import sa.assertj.Experiment;

import java.util.ArrayList;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;
import static sa.assertj.Util.r;

public class ZipSatisfy extends Experiment {
    static Experiment.DataProvider provider = (size) -> {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        int element = r.nextInt();
        for (int j = 0; j < size; j++) {
            list1.add(element);
            list2.add(element);
        }

        BiConsumer<Integer, Integer> bi_consumer = Integer::equals;

        return new Object[]{list1, list2, bi_consumer};
    };

    static Experiment.AssertionRunner runner = s -> assertThat((ArrayList<Integer>) s[0])
            .zipSatisfy((ArrayList<Integer>) s[1], (BiConsumer<Integer, Integer>) s[2]);

    public ZipSatisfy() {
        super(provider, runner);
    }
}
