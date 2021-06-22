package com.cash.app.user.service;

import com.cash.app.loan.service.LoanDeleteService;
import com.cash.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validations.ApiPreconditions;

@Service
public class    UserDeleteService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanDeleteService loanDeleteService;

    public void deleteUser(Long userId) {
        ApiPreconditions.checkNotNull(userId, "User ID");
        loanDeleteService.deleteByUserId(userId);
        userRepository.deleteById(userId);
    }
}
