package br.com.meta.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TipoCanal {
    @JsonProperty("Email")
    EMAIL,
    @JsonProperty("Celular")
    CELULAR,
    @JsonProperty("Fixo")
    FIXO;
}