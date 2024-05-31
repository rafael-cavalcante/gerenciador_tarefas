package br.com.rafaelcavalcante.gerenciadortarefas.controller;

import br.com.rafaelcavalcante.gerenciadortarefas.model.Pessoa;
import br.com.rafaelcavalcante.gerenciadortarefas.model.dto.PessoaDTO;
import br.com.rafaelcavalcante.gerenciadortarefas.repository.PessoaRepository;
import br.com.rafaelcavalcante.gerenciadortarefas.service.PessoaService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private PessoaRepository pessoaRepository;
    private PessoaService pessoaService;

    public PessoaController(PessoaRepository pessoaRepository, PessoaService pessoaService) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaService = pessoaService;
    }

    @PostMapping
    @Transactional
    public Pessoa adicionarPessoa(@RequestBody Pessoa pessoa) {
        return this.pessoaRepository.save(pessoa);
    }

    @GetMapping
    public List<PessoaDTO> listarPessoas() {
        return this.pessoaService.listarPessoa();
    }
}
