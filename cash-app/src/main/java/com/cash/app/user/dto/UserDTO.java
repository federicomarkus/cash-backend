package com.cash.app.user.dto;

import com.cash.app.loan.model.Loan;
import com.cash.app.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO extends User implements Serializable {

    private List<Loan> loans;

    public UserDTO(User user, List<Loan> loans) {
        super(user);
        this.loans = loans;
    }
}
