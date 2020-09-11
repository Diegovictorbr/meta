package br.com.meta.dto;

import br.com.meta.enums.TipoCanal;

import javax.validation.constraints.NotNull;

public class ContatoUpdate {
    @NotNull(message = "O nome do contato deve ser informado")
    private String nome;

    @NotNull(message = "Necessário informar o tipo de contato (email, telefone ou fixo)")
    private TipoCanal tipoCanal;

    @NotNull(message = "É obrigatório informar um número ou email de contato")
    private String valor;

    private String obs;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoCanal getTipoCanal() {
        return tipoCanal;
    }

    public void setTipoCanal(TipoCanal tipoCanal) {
        this.tipoCanal = tipoCanal;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
