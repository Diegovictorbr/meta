package br.com.meta.service;

import br.com.meta.dto.ContatoCreate;
import br.com.meta.dto.ContatoUpdate;
import br.com.meta.entity.Contato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContatoService {
    Contato findById(String id);

    Page<Contato> findAll(Pageable pageable);

    Contato post(ContatoCreate contatoCreate);

    Contato put(ContatoUpdate contatoUpdate, String id);

    Contato delete(String id);
}
