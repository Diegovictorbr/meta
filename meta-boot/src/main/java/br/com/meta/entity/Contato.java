package br.com.meta.entity;

import br.com.meta.enums.TipoCanal;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contatos")
public class Contato {

    // Identificador único
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false)
    private String id;

    // Nome que descreva o contato
    @NotNull(message = "O nome do contato deve ser informado")
    private String nome;

    // Tipo de canal de contato, podendo ser email, celular ou fixo
    @NotNull(message = "Necessário informar o tipo de contato (email, telefone ou fixo)")
    private TipoCanal tipoCanal;

    // Valor para o canal de contato
    @NotNull(message = "É obrigatório informar um número ou email de contato")
    private String valor;

    // Qualquer observação que seja pertinente
    private String obs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
