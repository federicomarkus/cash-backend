package com.cash.app.loan.repository;

import com.cash.app.loan.model.Loan;
import com.cash.app.user.model.User;
import com.cash.app.user.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LoanRepositoryImplTest {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private LoanRepositoryImpl loanRepositoryImpl;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void test() {
        Integer page = 1;
        Integer size = 2;
        User user1 = userRepository.save(User.builder()
                .email("federicomarkus@gmail.com")
                .firstName("Federico")
                .lastName("Markus")
                .build());
        User user2 = userRepository.save(User.builder()
                .email("test@gmail.com")
                .firstName("test")
                .lastName("test")
                .build());
        List<Loan> loans = new ArrayList<>();
        loans.add(Loan.builder()
                .userId(user1.getId())
                .total(BigDecimal.valueOf(200.30D))
                .build());
        loans.add(Loan.builder()
                .userId(user1.getId())
                .total(BigDecimal.valueOf(200.30D))
                .build());
        loans.add(Loan.builder()
                .userId(user1.getId())
                .total(BigDecimal.valueOf(200.30D))
                .build());
        loans.add(Loan.builder()
                .userId(user2.getId())
                .total(BigDecimal.valueOf(200.30D))
                .build());
        loans.add(Loan.builder()
                .userId(user2.getId())
                .total(BigDecimal.valueOf(200.30D))
                .build());
        loanRepository.saveAll(loans);
        //
        Page<Loan> result = loanRepositoryImpl.getLoansByPage(page, size, user1.getId());
        //
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getTotalElements(), 3);
        Assert.assertFalse(result.getContent().isEmpty());
        Assert.assertEquals(result.getContent().size(), (int) size);
        //
        result = loanRepositoryImpl.getLoansByPage(page, size, user2.getId());
        //
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getTotalElements(), 2);
        Assert.assertFalse(result.getContent().isEmpty());
        Assert.assertEquals(result.getContent().size(), (int) size);
        //
        result = loanRepositoryImpl.getLoansByPage(page, size, null);
        //
        Assert.assertNotNull(result);
        Assert.assertTrue(result.getTotalElements() >= loans.size());
        Assert.assertFalse(result.getContent().isEmpty());
        Assert.assertEquals(result.getContent().size(), (int) size);
        //
        result = loanRepositoryImpl.getLoansByPage(null, null, null);
        //
        Assert.assertNotNull(result);
        Assert.assertTrue(result.getTotalElements() >= loans.size());
        Assert.assertFalse(result.getContent().isEmpty());
    }

}
