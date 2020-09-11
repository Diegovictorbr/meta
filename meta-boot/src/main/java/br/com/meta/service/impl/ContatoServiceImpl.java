package br.com.meta.service.impl;

import br.com.meta.dto.ContatoCreate;
import br.com.meta.dto.ContatoUpdate;
import br.com.meta.entity.Contato;
import br.com.meta.exception.ResourceNotFoundException;
import br.com.meta.repository.ContatoRepository;
import br.com.meta.service.ContatoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContatoServiceImpl implements ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public Contato findById(String id) {
        Contato contato = this.contatoRepository.findById(id).orElse(null);

        if (contato == null)
            throw new ResourceNotFoundException("Contato não localizado");

        return contato;
    }

    public Page<Contato> findAll(Pageable pageable) {
        return this.contatoRepository.findAll(pageable);
    }

    public Contato post(ContatoCreate contatoCreate) {
        Contato contato = new Contato();
        BeanUtils.copyProperties(contatoCreate, contato);
        return this.contatoRepository.save(contato);
    }

    public Contato put(ContatoUpdate contatoUpdate, String id) {
        Contato contato = this.contatoRepository.findById(id).orElse(null);

        if (contato == null)
            throw new ResourceNotFoundException("Contato não localizado");

        try {
            BeanUtils.copyProperties(contatoUpdate, contato);
            return this.contatoRepository.save(contato);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Erro ao tentar atualizar o contato com os dados informados");
        }
    }

    public Contato delete(String id) {
        Contato contato = this.contatoRepository.findById(id).orElse(null);

        if (contato == null)
            throw new ResourceNotFoundException("Contato não localizado");

        this.contatoRepository.deleteById(id);
        return contato;
    }
}
