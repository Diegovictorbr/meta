package br.com.meta.resource;

import br.com.meta.dto.ContatoCreate;
import br.com.meta.dto.ContatoUpdate;
import br.com.meta.entity.Contato;
import br.com.meta.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class ContatoResource {

    @Autowired
    private ContatoService contatoService;

    @GetMapping("{idContato}")
    public ResponseEntity get(@PathVariable String idContato) {
        return ResponseEntity.ok(this.contatoService.findById(idContato));
    }

    @GetMapping
    public ResponseEntity get(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<Contato> contatos = this.contatoService.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(contatos);
    }

    @PostMapping
    public ResponseEntity post(@Valid @RequestBody ContatoCreate contatoCreate) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.contatoService.post(contatoCreate));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{idContato}")
    public ResponseEntity put(@RequestBody ContatoUpdate contatoUpdate, @PathVariable String idContato) {
        this.contatoService.put(contatoUpdate, idContato);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("{idContato}")
    public ResponseEntity delete(@PathVariable String idContato) {
        Contato contato = this.contatoService.delete(idContato);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
