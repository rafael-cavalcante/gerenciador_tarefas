package br.com.rafaelcavalcante.gerenciadortarefas.controller;

import br.com.rafaelcavalcante.gerenciadortarefas.model.Pessoa;
import br.com.rafaelcavalcante.gerenciadortarefas.model.dto.PessoaDTO;
import br.com.rafaelcavalcante.gerenciadortarefas.model.dto.PessoaHorasDTO;
import br.com.rafaelcavalcante.gerenciadortarefas.repository.PessoaRepository;
import br.com.rafaelcavalcante.gerenciadortarefas.service.PessoaService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @PutMapping("{id}")
    @Transactional
    public Pessoa atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Optional<Pessoa> optionalPessoa = this.pessoaRepository.findById(id);

        if (optionalPessoa.isPresent()) {
            Pessoa pessoaExistente = optionalPessoa.get();
            pessoaExistente.setNome(pessoa.getNome());
            pessoaExistente.setDepartamento(pessoa.getDepartamento());
            pessoaExistente.setTarefas(pessoa.getTarefas());

            return this.pessoaRepository.save(pessoaExistente);
        } else {
            throw new RuntimeException("Pessoa não encontrada com id " + id);
        }
    }

    @DeleteMapping("{id}")
    @Transactional
    public Optional<Pessoa> deletarPessoa(@PathVariable Long id) {
        Optional<Pessoa> optionalPessoa = this.pessoaRepository.findById(id);

        if (optionalPessoa.isPresent()) {
            this.pessoaRepository.deleteById(id);

            return optionalPessoa;
        } else {
            throw new RuntimeException("Pessoa não encontrada com id " + id);
        }
    }

    @GetMapping("/gastos")
    public List<PessoaHorasDTO> listarPessoasGastos(
            @RequestParam String nome,
            @RequestParam String dataInicio,
            @RequestParam String dataFim) {

        LocalDate inicio = LocalDate.parse(dataInicio);
        LocalDate fim = LocalDate.parse(dataFim);

        return this.pessoaRepository.findPessoaHorasByNomeAndPeriodo(nome, inicio, fim);
    }
}
