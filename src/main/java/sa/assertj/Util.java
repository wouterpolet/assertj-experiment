package sa.assertj;

import java.nio.charset.Charset;
import java.util.Random;

public class Util {
    public static int RANDOM_CHANCE = 8;
    public static double RANDOM_SIZE_DIFF = 0.25;
    public static int STRING_LENGTH = 10;

    public static Random r = new Random();

    public static String randomString(int size) {
        // From: https://www.baeldung.com/java-random-string
        byte[] array = new byte[size]; // string will have size `size`
        r.nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
