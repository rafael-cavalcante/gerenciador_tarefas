package br.com.rafaelcavalcante.gerenciadortarefas.controller;

import br.com.rafaelcavalcante.gerenciadortarefas.model.Pessoa;
import br.com.rafaelcavalcante.gerenciadortarefas.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private PessoaRepository pessoaRepository;

    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @PostMapping
    @Transactional
    public Pessoa adicionarPessoa(@RequestBody Pessoa pessoa) {
        return this.pessoaRepository.save(pessoa);
    }
}
