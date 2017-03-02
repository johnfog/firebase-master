package com.gnz.firebasemaster.utils;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

public class DataValidator {

    private static final String PASS_PATTERN = "^(?=.*?\\p{Lu})(?=.*?[\\p{L}&&[^\\p{Lu}]])(?=.*?\\d)" +
            ".*$";

    private final int passLength;
    private final Pattern passPattern;

    public DataValidator(int passLength) {
        this.passLength = passLength;
        this.passPattern = Pattern.compile(PASS_PATTERN);
    }

    public boolean isEmpty(String field) {
        return field == null || TextUtils.isEmpty(field.trim());
    }

    public boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isPassValid(String input) {
        return input != null && passPattern.matcher(input).matches();
    }

    public boolean isPassLengthValid(String input) {
        return input != null && input.length() >= passLength;
    }
}
