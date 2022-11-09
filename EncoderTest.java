import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EncoderTest {
    private static String offsetString;
    private static final Logger logger = Logger.getLogger(EncoderTest.class.getName());
    private static final String HELLO_WORLD = "HELLO WORLD";

    @BeforeAll
    public static void setup() {

    }

    @Test
    public void checkEncodedText() {
        Encoder encoder = new Encoder();
        String result = encoder.encode("B");
        assertEquals(result, "A");
    }
}
