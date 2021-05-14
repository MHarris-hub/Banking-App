package com.usfbank.app.dao;

import com.usfbank.app.dao.impl.AccountManagementDAOImpl;
import com.usfbank.app.exception.AccountException;
import com.usfbank.app.service.AccountManagementService;
import com.usfbank.app.service.impl.AccountManagementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TestAccountManagementDAOImpl {
    @Mock //@Mock for a class that cannot easily be tested and needs to be mocked
    AccountManagementDAOImpl daoMock = Mockito.mock(AccountManagementDAOImpl.class);

    @InjectMocks //@InjectsMocks for a class instance which needs to be tested
    AccountManagementService service = new AccountManagementServiceImpl();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin() {
        //initialize mock values
        Mockito.when(daoMock.login("hello", "password", "customer")).thenReturn(true);
        Mockito.when(daoMock.login("emp", "123", "employee")).thenReturn(true);

        //check valid inputs
        assertTrue(service.login("hello", "password", "customer"));
        assertTrue(service.login("emp", "123", "employee"));

        //check invalid inputs
        assertFalse(service.login("hello00", "password", "customer"));
        assertFalse(service.login("hello", "pP{ssword", "customer"));
        assertFalse(service.login("emp", "123", "customer"));
        assertFalse(service.login("hello", "password", "employee"));
        assertFalse(service.login("emp", "123", ""));
    }

    @Test
    void testGetBalance() {
        //initialize mock values
        Mockito.when(daoMock.getBalance(1)).thenReturn(new BigDecimal(234));

        //check valid inputs
        assertEquals(new BigDecimal(234), service.getBalance(1));
    }

    @Test
    void testDeposit() throws AccountException {
        //perform mock deposit
        daoMock.deposit(1, new BigDecimal(234));

        //verify mock deposit was made
        verify(daoMock).deposit(1, new BigDecimal(234));

//      //set behavior for attempted negative deposits
//      doThrow(AccountException.class).when(daoMock.deposit(1, new BigDecimal(-234)));
//
//      //check invalid inputs
//      assertThrows(AccountException.class, () -> service.deposit(1, new BigDecimal(-234)));
    }

    @Test
    void testWithdraw() throws AccountException {
        //perform mock withdrawal
        daoMock.withdraw(1, new BigDecimal(234));

        //verify mock withdrawal was made
        verify(daoMock).withdraw(1, new BigDecimal(234));
    }

    @Test
    void testTransfer() throws AccountException {
        //perform mock transfer
        daoMock.transfer(1, 2, new BigDecimal(234));

        //verify mock transfer was made
        verify(daoMock).transfer(1, 2, new BigDecimal(234));
    }
}