package com.usfbank.app.service.util;

import java.math.BigDecimal;

public final class InputValidation {
    private InputValidation(){}

    public static boolean isValidUsername(String username) {
        return username != null && username.matches("[a-zA-Z0-9]{3,20}");
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.matches("[a-zA-Z0-9!#$%&()]{3,20}");
    }

    //determines if the given amount is valid for deposits, withdrawals, and transfers
    public static boolean isValidAmount(BigDecimal amount) {
        return amount.compareTo(new BigDecimal(0)) == 1;
    }
}