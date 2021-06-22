package com.cash.app.loan.service;

import com.cash.app.loan.dto.LoansDTO;
import com.cash.app.loan.model.Loan;
import com.cash.app.loan.repository.LoanRepositoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LoanFinderServiceTest {

    @InjectMocks
    private LoanFinderService loanFinderService;

    @Mock
    private LoanRepositoryImpl loanRepositoryImpl;

    @Before
    public void setUp() {
        loanFinderService = new LoanFinderService();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_getLoans() {
        Integer page = 1;
        Integer size = 10;
        Long total = 100L;
        Long userId = 1L;
        List<Loan> loans = new ArrayList<>();
        loans.add(Loan.builder().id(1L).userId(userId).total(BigDecimal.TEN).build());
        Page<Loan> loansPage = new PageImpl<>(loans, PageRequest.of(page, size), total);
        //
        Mockito.when(loanRepositoryImpl.getLoansByPage(page, size, userId)).thenReturn(loansPage);
        LoansDTO result = loanFinderService.getLoans(page, size, userId);
        //
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getLoans(), loans);
        Assert.assertNotNull(result.getPageData());
        Assert.assertEquals(result.getPageData().getPage(), page);
        Assert.assertEquals(result.getPageData().getSize(), size);
        Assert.assertEquals(result.getPageData().getTotal(), total);
        Mockito.verify(loanRepositoryImpl).getLoansByPage(page, size, userId);
    }

    @Test
    public void test_getLoans_UserId_Null() {
        Integer page = 1;
        Integer size = 10;
        Long total = 100L;
        Long userId = null;
        List<Loan> loans = new ArrayList<>();
        loans.add(Loan.builder().id(1L).userId(2L).total(BigDecimal.TEN).build());
        Page<Loan> loansPage = new PageImpl<>(loans, PageRequest.of(page, size), total);
        //
        Mockito.when(loanRepositoryImpl.getLoansByPage(page, size, userId)).thenReturn(loansPage);
        LoansDTO result = loanFinderService.getLoans(page, size, userId);
        //
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getLoans(), loans);
        Assert.assertNotNull(result.getPageData());
        Assert.assertEquals(result.getPageData().getPage(), page);
        Assert.assertEquals(result.getPageData().getSize(), size);
        Assert.assertEquals(result.getPageData().getTotal(), total);
        Mockito.verify(loanRepositoryImpl).getLoansByPage(page, size, userId);
    }

    @Test
    public void test_getLoans_Empty() {
        Integer page = 1;
        Integer size = 10;
        Long total = 0L;
        Long userId = 1L;
        Page<Loan> loansPage = new PageImpl<>(new ArrayList<>(), PageRequest.of(page, size), total);
        Mockito.when(loanRepositoryImpl.getLoansByPage(page, size, userId)).thenReturn(loansPage);
        LoansDTO result = loanFinderService.getLoans(page, size, userId);
        //
        Assert.assertNotNull(result);
        Assert.assertTrue(result.getLoans().isEmpty());
        Assert.assertNotNull(result.getPageData());
        Assert.assertEquals(result.getPageData().getPage(), page);
        Assert.assertEquals(result.getPageData().getSize(), size);
        Assert.assertEquals(result.getPageData().getTotal(), total);
        Mockito.verify(loanRepositoryImpl).getLoansByPage(page, size, userId);
    }

    @Test
    public void test_getAllLoansByUser() {
        Long userId = 1L;
        List<Loan> loans = new ArrayList<>();
        loans.add(Loan.builder().id(1L).userId(userId).total(BigDecimal.TEN).build());
        Page<Loan> loansPage = new PageImpl<>(loans);
        //
        Mockito.when(loanRepositoryImpl.getLoansByPage(null, null, userId)).thenReturn(loansPage);
        List<Loan> result = loanFinderService.getAllLoansByUser(userId);
        //
        Assert.assertNotNull(result);
        Assert.assertEquals(result, loans);
        Mockito.verify(loanRepositoryImpl).getLoansByPage(null, null, userId);
    }

}
