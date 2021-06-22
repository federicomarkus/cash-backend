package com.cash.app.user.service;

import com.cash.app.user.dto.UserStorageDTO;
import com.cash.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validations.ApiPreconditions;

@Service
public class UserStorageService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserStorageDTO userStorageDTO) {
        ApiPreconditions.checkNotNull(userStorageDTO, "User Storage DTO");
        userStorageDTO.validate();
        userRepository.save(userStorageDTO.toUser());
    }

}
