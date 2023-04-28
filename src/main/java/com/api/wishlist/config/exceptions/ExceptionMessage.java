package com.api.wishlist.config.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    ERROR_VALIDATION_MESSAGE("Dados enviados estão incompletos ou invalidos."),
    ERROR_EMAIL_VALIDATION_MESSAGE("Email invalido.");

    private final String message;
}
