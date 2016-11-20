package info.evsikov.cryptography.api;

public interface KeyEncryptionAlgorithm {
    String encrypt (String openText, String key);
}
