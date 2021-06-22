package com.cash.app.user.api;

import advice.BaseApiExceptionAdvice;
import com.cash.app.user.dto.UserDTO;
import com.cash.app.user.dto.UserStorageDTO;
import com.cash.app.user.service.UserDeleteService;
import com.cash.app.user.service.UserFinderService;
import com.cash.app.user.service.UserStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
public class UserApi extends BaseApiExceptionAdvice {

    @Autowired
    private UserFinderService userFinderService;
    @Autowired
    private UserStorageService userStorageService;
    @Autowired
    private UserDeleteService userDeleteService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long userId) {
        UserDTO user = userFinderService.getUserData(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping()
    public ResponseEntity saveUser(@RequestBody UserStorageDTO userStorageDTO) {
        userStorageService.saveUser(userStorageDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long userId) {
        userDeleteService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
