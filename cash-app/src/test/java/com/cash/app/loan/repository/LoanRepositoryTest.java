package com.cash.app.loan.repository;

import com.cash.app.loan.model.Loan;
import com.cash.app.user.model.User;
import com.cash.app.user.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LoanRepositoryTest {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void test() {
        User user = userRepository.save(User.builder()
                .email("federicomarkus@gmail.com")
                .firstName("Federico")
                .lastName("Markus")
                .build());
        Loan loan = loanRepository.save(Loan.builder()
                .userId(user.getId())
                .total(BigDecimal.valueOf(200.30D))
                .build());
        //
        Loan result = loanRepository.getById(loan.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result, loan);
        loanRepository.deleteByUserId(user.getId());
        //
        Optional<Loan> result2 = loanRepository.findById(loan.getId());
        Assert.assertTrue(result2.isEmpty());
    }

}
