package com.cash.app.user.exceptions;


import exceptions.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    final String message = "The user with id %d was not found.";

    public UserNotFoundException(Long userId) {
        setMessage(String.format(message, userId));
    }

}
