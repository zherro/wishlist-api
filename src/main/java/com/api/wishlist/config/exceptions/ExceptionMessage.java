package com.api.wishlist.config.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    ERROR_VALIDATION_MESSAGE("Dados enviados estão incompletos ou invalidos."),
    ERROR_EMAIL_VALIDATION_MESSAGE("Email invalido."),
    ERROR_MAX_WISHLIST_LIMIT_MESSAGE("É permitido um maximo de 20 itens na lista de desejos, vc pode remover um item para adicionar outro."),
    ERROR_NOT_FOUND_MESSAGE("Conteudo não encontrado."),
    MAX_RANGE_ID("Os campos userId e productId tem tamanho máximo de 50 caracteres");

    private final String message;
}
