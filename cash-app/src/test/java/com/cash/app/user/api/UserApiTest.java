package com.cash.app.user.api;

import com.cash.app.user.dto.UserDTO;
import com.cash.app.user.dto.UserStorageDTO;
import com.cash.app.user.model.User;
import com.cash.app.user.service.UserDeleteService;
import com.cash.app.user.service.UserFinderService;
import com.cash.app.user.service.UserStorageService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public class UserApiTest {

    @InjectMocks
    private UserApi userApi;

    @Mock
    private UserFinderService userFinderService;
    @Mock
    private UserStorageService userStorageService;
    @Mock
    private UserDeleteService userDeleteService;

    @Before
    public void setUp() {
        userApi = new UserApi();
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
        UserDTO userDTO = new UserDTO(user, new ArrayList<>());
        //
        Mockito.when(userFinderService.getUserData(userId)).thenReturn(userDTO);
        ResponseEntity<UserDTO> result = userApi.getUser(userId);
        //
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getBody(), userDTO);
        Mockito.verify(userFinderService).getUserData(userId);
    }

    @Test
    public void test_saveUser() {
        UserStorageDTO user = UserStorageDTO.builder()
                .email("federicomarkus@gmail.com")
                .firstName("Federico")
                .lastName("Markus")
                .build();
        //
        ResponseEntity result = userApi.saveUser(user);
        //
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getStatusCode(), HttpStatus.OK);
        Mockito.verify(userStorageService).saveUser(user);
    }

    @Test
    public void test_deleteUser() {
        Long userId = 1L;
        //
        ResponseEntity result = userApi.deleteUser(userId);
        //
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getStatusCode(), HttpStatus.OK);
        Mockito.verify(userDeleteService).deleteUser(userId);
    }
}
