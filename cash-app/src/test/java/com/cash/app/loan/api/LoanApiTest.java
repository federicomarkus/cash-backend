package com.cash.app.loan.api;

import com.cash.app.loan.dto.LoansDTO;
import com.cash.app.loan.model.Loan;
import com.cash.app.loan.service.LoanFinderService;
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
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LoanApiTest {

    @InjectMocks
    private LoanApi loanApi;

    @Mock
    private LoanFinderService loanFinderService;


    @Before
    public void setUp() {
        loanApi = new LoanApi();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_getUser() {
        Integer page = 1;
        Integer size = 10;
        Long total = 100L;
        Long userId = 1L;
        List<Loan> loans = new ArrayList<>();
        loans.add(Loan.builder().id(1L).userId(userId).total(BigDecimal.TEN).build());
        Page<Loan> loansPage = new PageImpl<>(loans, PageRequest.of(page, size), total);
        LoansDTO loansDTO = new LoansDTO(loansPage, page);
        //
        Mockito.when(loanFinderService.getLoans(page, size, userId)).thenReturn(loansDTO);
        ResponseEntity<LoansDTO> result = loanApi.getUser(page, size, userId);
        //
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getBody());
        Assert.assertEquals(result.getBody(), loansDTO);
        Mockito.verify(loanFinderService).getLoans(page, size, userId);
    }
}
