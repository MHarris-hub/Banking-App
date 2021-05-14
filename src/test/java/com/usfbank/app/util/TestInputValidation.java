package com.usfbank.app.util;

import com.usfbank.app.service.util.InputValidation;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class TestInputValidation {

    @Test
    void testIsValidUsername() {
        //check valid inputs
        assertTrue(InputValidation.isValidUsername("Mike"));
        assertTrue(InputValidation.isValidUsername("123"));
        assertTrue(InputValidation.isValidUsername("M23"));

       //check invalid inputs
        assertFalse(InputValidation.isValidUsername("m/ik2"));
        assertFalse(InputValidation.isValidUsername("mk"));
        assertFalse(InputValidation.isValidUsername("mik2mmmmmmmmmmmmmmmmmm"));
    }

    @Test
    void testIsValidPassword() {
        //check valid inputs
        assertTrue(InputValidation.isValidPassword("abc123"));
        assertTrue(InputValidation.isValidPassword(")(2erw"));
        assertTrue(InputValidation.isValidPassword("2e&rMKE"));

        //check invalid inputs
        assertFalse(InputValidation.isValidPassword(""));
        assertFalse(InputValidation.isValidPassword("/iop"));
        assertFalse(InputValidation.isValidPassword("ppppppppppppppppppppp"));
    }

    @Test
    void testIsValidAmount() {
        //check valid inputs
        assertTrue(InputValidation.isValidAmount(new BigDecimal(".01")));
        assertTrue(InputValidation.isValidAmount(new BigDecimal(1000000)));

        //check invalid inputs
        assertFalse(InputValidation.isValidAmount(new BigDecimal(0)));
        assertFalse(InputValidation.isValidAmount(new BigDecimal(-1)));
        assertFalse(InputValidation.isValidAmount(new BigDecimal(-1000000)));
    }
}
