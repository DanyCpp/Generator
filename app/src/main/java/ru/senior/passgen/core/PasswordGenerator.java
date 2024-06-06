package ru.senior.passgen.core;

import java.util.Random;

public class PasswordGenerator {

    public static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String RUSSIAN_LETTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    public static final String RUSSIAN_UPPER_LETTERS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    public static final String NUMBERS = "0123456789";
    public static final String SYMBOLS = "!@#$%^&*()-=_+[]{}|;:,.<>?";

    public String generatePassword(int length, boolean useNumbers, boolean useLetters, boolean useSymbols, boolean useRussians) {
        StringBuilder chars = new StringBuilder();
        if (useLetters) {
            chars.append(LOWERCASE_LETTERS).append(UPPERCASE_LETTERS);
        }
        if (useNumbers) {
            chars.append(NUMBERS);
        }
        if (useSymbols) {
            chars.append(SYMBOLS);
        }
        if (useSymbols) {
            chars.append(SYMBOLS);
        }
        if (useRussians) {
            chars.append(RUSSIAN_LETTERS).append(RUSSIAN_UPPER_LETTERS);
        }


        if (chars.length() == 0) {
            throw new IllegalArgumentException("At least one character type should be selected.");
        }

        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }

}
