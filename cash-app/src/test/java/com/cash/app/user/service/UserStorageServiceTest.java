package com.cash.app.user.service;

import com.cash.app.user.dto.UserStorageDTO;
import com.cash.app.user.repository.UserRepository;
import exceptions.ConstraintViolationException;
import exceptions.InvalidEmailException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UserStorageServiceTest {

    @InjectMocks
    private UserStorageService userStorageService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userStorageService = new UserStorageService();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_saveUser() {
        UserStorageDTO userStorageDTO = UserStorageDTO.builder()
                .email("federicomarkus@gmail.com")
                .firstName("Federico")
                .lastName("Markus")
                .build();
        //
        userStorageService.saveUser(userStorageDTO);
        //
        Mockito.verify(userRepository).save(userStorageDTO.toUser());
    }

    @Test(expected = ConstraintViolationException.class)
    public void test_saveUser_Email_Null() {
        UserStorageDTO userStorageDTO = UserStorageDTO.builder()
                .firstName("Federico")
                .lastName("Markus")
                .build();
        //
        userStorageService.saveUser(userStorageDTO);
        //
        Mockito.verify(userRepository, Mockito.never()).save(userStorageDTO.toUser());
    }

    @Test(expected = InvalidEmailException.class)
    public void test_saveUser_Email_Invalid() {
        UserStorageDTO userStorageDTO = UserStorageDTO.builder()
                .email("federicomarkus")
                .firstName("Federico")
                .lastName("Markus")
                .build();
        //
        userStorageService.saveUser(userStorageDTO);
        //
        Mockito.verify(userRepository, Mockito.never()).save(userStorageDTO.toUser());
    }
}
