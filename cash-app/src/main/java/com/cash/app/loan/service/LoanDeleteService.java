package com.cash.app.loan.service;

import com.cash.app.loan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validations.ApiPreconditions;

@Service
public class LoanDeleteService {

    @Autowired
    private LoanRepository loanRepository;

    public void deleteByUserId(Long userId) {
        ApiPreconditions.checkNotNull(userId, "User ID");
        loanRepository.deleteByUserId(userId);
    }
}
