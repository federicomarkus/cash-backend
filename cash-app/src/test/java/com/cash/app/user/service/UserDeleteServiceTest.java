package com.cash.app.user.service;

import com.cash.app.loan.service.LoanDeleteService;
import com.cash.app.user.repository.UserRepository;
import exceptions.BadRequestException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UserDeleteServiceTest {

    @InjectMocks
    private UserDeleteService userDeleteService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private LoanDeleteService loanDeleteService;

    @Before
    public void setUp() {
        userDeleteService = new UserDeleteService();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_deleteUser() {
        Long userId = 1L;
        //
        userDeleteService.deleteUser(userId);
        //
        Mockito.verify(loanDeleteService).deleteByUserId(userId);
        Mockito.verify(userRepository).deleteById(userId);
    }

    @Test(expected = BadRequestException.class)
    public void test_deleteUser_userId_Null() {
        Long userId = null;
        //
        userDeleteService.deleteUser(userId);
        //
        Mockito.verify(loanDeleteService, Mockito.never()).deleteByUserId(Mockito.anyLong());
        Mockito.verify(userRepository, Mockito.never()).deleteById(Mockito.anyLong());
    }

}
