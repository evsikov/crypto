package info.evsikov.cryptography.verrnam;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class VerrnamCustomAlphabetTest {
    @org.junit.Test
    public void whenGivenCorrectInput_thenRecieveCorrectOutput_thenDecodeEncoded() throws Exception {
        final String expected = "DfcsE";
        final String openText = "DearA";
        final String key = "abcde";

        final String cypher = new Verrnam().encrypt (openText, key);

        assertThat(cypher, is(expected));

        final String decrypted = new Verrnam().encrypt(cypher, key);

        assertThat(decrypted, is(openText));
    }

    @org.junit.Test
    public void whenGivenCorrectMoreComplicatedInput_thenRecieveCorrectOutput() throws Exception {
        final String expected = "zbAcp1x7kPBZ9XN;r7;U bdRiUBvbApCftCw6W9UY";
        final String openText = "Alice In Wonderland 2016; Terminator 1984";
        final String key = "dksalk121zv;;121r29rj12r2r2rqwhrfashffask";

        final String cypher = new Verrnam().encrypt (openText, key);

        assertThat(cypher, is(expected));

        final String decrypted = new Verrnam().encrypt(cypher, key);

        assertThat(decrypted, is(openText));
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void whenGivenTextLongerThanKey_thenThrowIllegalArgumentException() throws Exception {
        final String openText = "DearAB";
        final String key = "abcde";

        new Verrnam().encrypt (openText, key);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void whenGivenKeyLongerThanText_thenThrowIllegalArgumentException() throws Exception {
        final String openText = "DearA";
        final String key = "abcdef";

        new Verrnam().encrypt (openText, key);
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void whenGivenNullText_thenThrowNullPointerException() throws Exception {
        final String openText = null;
        final String key = "abcdef";

        new Verrnam().encrypt (openText, key);
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void whenGivenNullKey_thenThrowNullPointerException() throws Exception {
        final String openText = "DearA";
        final String key = null;

        new Verrnam().encrypt (openText, key);
    }
}