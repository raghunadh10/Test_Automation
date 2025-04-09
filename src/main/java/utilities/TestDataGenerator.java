package utilities;

import java.util.Random;

/**
 * Utility class for generating random test data such as emails, passwords, and alphanumeric strings.
 */
public class TestDataGenerator {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String EMAIL_DOMAIN = "@example.com";

    /**
     * Generates a random email ID.
     *
     * @return A randomly generated email address.
     */
    public static String generateRandomEmail() {
        return "testuser" + generateRandomString(5) + EMAIL_DOMAIN;
    }

    /**
     * Generates a strong random password.
     *
     * @return A randomly generated strong password.
     */
    public static String generateRandomPassword() {
        return generateRandomString(8) + "@" + generateRandomNumber(2);
    }

    /**
     * Generates a random alphanumeric string of a given length.
     *
     * @param length The desired length of the generated string.
     * @return A randomly generated alphanumeric string.
     */
    public static String generateRandomString(int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(index));
        }

        return builder.toString();
    }

    /**
     * Generates a random numeric string of a given length.
     *
     * @param length The desired length of the generated numeric string.
     * @return A randomly generated numeric string.
     */
    public static String generateRandomNumber(int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            builder.append(random.nextInt(10)); // Generates a digit between 0 and 9
        }

        return builder.toString();
    }
}
