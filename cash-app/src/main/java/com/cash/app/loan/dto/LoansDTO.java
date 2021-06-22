package com.cash.app.loan.dto;

import com.cash.app.loan.model.Loan;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import pagination.PageData;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoansDTO {

    @JsonProperty("items")
    private List<Loan> loans;
    @JsonProperty("paging")
    private PageData pageData;

    public LoansDTO(Page<Loan> loans, Integer page) {
        this.loans = loans.getContent();
        this.pageData = PageData.builder()
                .page(page)
                .size(loans.getSize())
                .total(loans.getTotalElements())
                .build();
    }
}
