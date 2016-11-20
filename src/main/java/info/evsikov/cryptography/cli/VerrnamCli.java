package info.evsikov.cryptography.cli;

import info.evsikov.cryptography.api.Command;
import info.evsikov.cryptography.verrnam.Verrnam;

import java.util.Objects;

import static org.apache.commons.lang.RandomStringUtils.randomAlphanumeric;

public class VerrnamCli {
    private static final String USAGE = "Usage:\nprogram decrypt \"<encrypted text>\" \"<key>\"\nprogram encrypt \"<text>\"";

    public static void main(String[] args) {
        if (args.length < 1 || args.length > 3) {
            System.err.println(USAGE);
            System.exit(-1);
        }

        final Command command = Command.valueOf(args[0].toUpperCase());

        switch (command) {
            case ENCRYPT: encrypt(args[1], null);
                break;
            case DECRYPT: encrypt(args[1], args[2]);
                break;
            default: throw new IllegalStateException();
        }
    }

    private static void encrypt(final String text, final String keyProvided) {
        final String key = (keyProvided == null)
                ? randomAlphanumeric(Objects.requireNonNull(text, "Text can't be null").length()) : keyProvided;

        final String encrypted = new Verrnam().encrypt(text, key);

        System.out.println(String.format("EncryptedMessage:\n%s\nKey:\n%s\nLength\n:%s",encrypted, key, key.length()));
    }
}
