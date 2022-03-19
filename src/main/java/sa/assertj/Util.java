package sa.assertj;

import java.nio.charset.Charset;
import java.util.Random;

public class Util {
    public static String randomString(int size) {
        // From: https://www.baeldung.com/java-random-string
        byte[] array = new byte[size]; // string will have size 10
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
