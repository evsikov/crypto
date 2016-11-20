package info.evsikov.cryptography.verrnam;

import info.evsikov.cryptography.api.KeyEncryptionAlgorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Verrnam implements KeyEncryptionAlgorithm {
    private final Map<Character, Byte> charToByte;
    private final Map<Byte, Character> byteToChar;

    /**
     * Creates a new instance of Verrnam cipher encryptor with the provided alphabet.
     * @param alphabet should contain all symbols that can appear in open text or key.
     *                 It should have the length that's a power of 2.
     *                 It should not be longer than 128 characters (this is a quick implementation, thus the constraint)
     * @throws IllegalArgumentException if alphabet constraints are not respected.
     */
    private Verrnam(final String alphabet) {
        if (Objects.requireNonNull(alphabet, "Alphabet can't be null").length() % 2 != 0) {
            throw new IllegalArgumentException("Alphabet length must be a power of 2");
        } else if (alphabet.length() > 128) {
            throw new IllegalArgumentException("Alphabet length can't be more than 128");
        }

        final Map<Character, Byte> encodeMap = new HashMap<>();
        final Map<Byte, Character> backMap = new HashMap<>();

        byte b = 0;
        for (char c : alphabet.toCharArray()) {
            encodeMap.put(c, b);
            backMap.put(b, c);
            b++;
        }

        charToByte = encodeMap;
        byteToChar = backMap;
    }

    public Verrnam() {
        this("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789; ");
    }

    /**
     * Encrypts Verrnam message using key provided.
     * Message and key should be of same length when using this implementation.
     * If passing the encrypted message with the proper key,
     * then the message will be decrypted and original text returned.
     * @param openText text to be encrypted.
     * @param key key to encrypt the open message.
     * @return encrypted string.
     */
    @Override
    public String encrypt(final String openText, final String key) {
        if (Objects.requireNonNull(openText, "Open text can't be null").length() !=
                Objects.requireNonNull(key, "Key can't be null").length()) {
            throw new IllegalArgumentException("The lengths of open text and keys must equal.");
        }

        final char[] openTextChars = openText.toCharArray();
        final char[] keyChars = key.toCharArray();

        final int numberOfBytes = openTextChars.length;

        final char[] encryptedText = new char[numberOfBytes];

        for (int i = 0; i < numberOfBytes; i++) {
            final Byte openTextByte = charToByte.get(openTextChars[i]);
            final Byte keyByte = charToByte.get(keyChars[i]);

            final byte product = (byte)
                    (Objects.requireNonNull(openTextByte, "openText contains illegal character" + openTextChars[i]) ^
                    Objects.requireNonNull(keyByte, "Key contains illegal character" + keyChars[i]));

            final Character encryptedChar = byteToChar.get(product);

            encryptedText[i] = Objects.requireNonNull(encryptedChar, "Character must belong to the alphabet");
        }

        return new String(encryptedText);
    }
}
