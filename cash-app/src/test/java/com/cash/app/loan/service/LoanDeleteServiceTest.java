package com.cash.app.loan.service;

import com.cash.app.loan.repository.LoanRepository;
import exceptions.BadRequestException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class LoanDeleteServiceTest {

    @InjectMocks
    private LoanDeleteService loanDeleteService;
    @Mock
    private LoanRepository loanRepository;

    @Before
    public void setUp() {
        loanDeleteService = new LoanDeleteService();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_deleteByUserId() {
        Long userId = 1L;
        //
        loanDeleteService.deleteByUserId(userId);
        //
        Mockito.verify(loanRepository).deleteByUserId(userId);
    }

    @Test(expected = BadRequestException.class)
    public void test_deleteUser_userId_Null() {
        Long userId = null;
        //
        loanDeleteService.deleteByUserId(userId);
        //
        Mockito.verify(loanRepository, Mockito.never()).deleteByUserId(Mockito.anyLong());
    }

}
