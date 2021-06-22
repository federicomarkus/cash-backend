package com.cash.app.user.service;

import com.cash.app.loan.model.Loan;
import com.cash.app.loan.service.LoanFinderService;
import com.cash.app.user.dto.UserDTO;
import com.cash.app.user.exceptions.UserNotFoundException;
import com.cash.app.user.model.User;
import com.cash.app.user.repository.UserRepository;
import exceptions.BadRequestException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserFinderServiceTest {

    @InjectMocks
    private UserFinderService userFinderService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private LoanFinderService loanFinderService;


    @Before
    public void setUp() {
        userFinderService = new UserFinderService();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_getUser() {
        Long userId = 1L;
        User user = User.builder()
                .id(userId)
                .email("federicomarkus@gmail.com")
                .firstName("Federico")
                .lastName("Markus")
                .build();
        //
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        User result = userFinderService.getUser(userId);
        //
        Assert.assertNotNull(result);
        Assert.assertEquals(result, user);
        Mockito.verify(userRepository).findById(userId);
    }

    @Test(expected = UserNotFoundException.class)
    public void test_getUser_NotFound() {
        Long userId = 1L;
        //
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());
        userFinderService.getUser(userId);
        //
        Mockito.verify(userRepository).findById(userId);
    }

    @Test(expected = BadRequestException.class)
    public void test_getUser_UserId_Null() {
        Long userId = null;
        //
        userFinderService.getUser(userId);
        //
        Mockito.verify(userRepository, Mockito.never()).findById(Mockito.anyLong());
    }

    @Test
    public void test_getUserData() {
        Long userId = 1L;
        User user = User.builder()
                .id(userId)
                .email("federicomarkus@gmail.com")
                .firstName("Federico")
                .lastName("Markus")
                .build();
        List<Loan> loans = new ArrayList<>();
        loans.add(Loan.builder()
                .id(1L)
                .userId(userId)
                .total(BigDecimal.valueOf(200.30D))
                .build());
        //
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(loanFinderService.getAllLoansByUser(userId)).thenReturn(loans);
        UserDTO result = userFinderService.getUserData(userId);
        //
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), user.getId());
        Assert.assertEquals(result.getEmail(), user.getEmail());
        Assert.assertEquals(result.getFirstName(), user.getFirstName());
        Assert.assertEquals(result.getLastName(), user.getLastName());
        Assert.assertEquals(result.getLoans(), loans);
        Mockito.verify(userRepository).findById(userId);
        Mockito.verify(loanFinderService).getAllLoansByUser(userId);
    }

    @Test(expected = UserNotFoundException.class)
    public void test_getUserData_NotFound() {
        Long userId = 1L;
        //
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());
        userFinderService.getUser(userId);
        //
        Mockito.verify(userRepository).findById(userId);
        Mockito.verify(loanFinderService, Mockito.never()).getAllLoansByUser(userId);
    }


}
