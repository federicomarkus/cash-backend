package com.cash.app.user.dto;

import com.cash.app.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import validations.ApiPreconditions;
import validations.EmailValidatorService;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserStorageDTO implements Serializable {

    @NotNull
    private String email;
    private String firstName;
    private String lastName;

    public void validate() {
        // Validate annotations
        ApiPreconditions.validateModel(this);
        // Validate email format
        EmailValidatorService.validateEmail(email);
    }

    public User toUser() {
        return User.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}
