package com.cash.app.loan.api;

import advice.BaseApiExceptionAdvice;
import com.cash.app.loan.dto.LoansDTO;
import com.cash.app.loan.service.LoanFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/loans")
public class LoanApi extends BaseApiExceptionAdvice {

    @Autowired
    private LoanFinderService loanFinderService;

    @GetMapping(path = "")
    public ResponseEntity<LoansDTO> getUser(@RequestParam(name = "page") int page,
                                            @RequestParam(name = "size") int size,
                                            @RequestParam(name = "user_id", required = false) Long userId) {
        LoansDTO loans = loanFinderService.getLoans(page, size, userId);
        return ResponseEntity.status(HttpStatus.OK).body(loans);
    }

}
