package com.cash.app.user.service;

import com.cash.app.loan.service.LoanFinderService;
import com.cash.app.user.dto.UserDTO;
import com.cash.app.user.exceptions.UserNotFoundException;
import com.cash.app.user.model.User;
import com.cash.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validations.ApiPreconditions;

@Service
public class UserFinderService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanFinderService loanFinderService;


    public User getUser(Long userId) {
        ApiPreconditions.checkNotNull(userId, "User ID");
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public UserDTO getUserData(Long userId) {
        User user = getUser(userId);
        return new UserDTO(user, loanFinderService.getAllLoansByUser(userId));
    }
}
