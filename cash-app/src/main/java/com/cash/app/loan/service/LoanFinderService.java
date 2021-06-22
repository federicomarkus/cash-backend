package com.cash.app.loan.service;

import com.cash.app.loan.dto.LoansDTO;
import com.cash.app.loan.model.Loan;
import com.cash.app.loan.repository.LoanRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanFinderService {

    @Autowired
    private LoanRepositoryImpl loanRepository;

    public LoansDTO getLoans(Integer page, Integer size, Long userId) {
        Page<Loan> loans = loanRepository.getLoansByPage(page, size, userId);
        return new LoansDTO(loans, page);
    }

    public List<Loan> getAllLoansByUser(Long userId) {
        LoansDTO loans = getLoans(null, null, userId);
        return loans.getLoans();
    }

}
