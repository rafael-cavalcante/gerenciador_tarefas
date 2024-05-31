package br.com.rafaelcavalcante.gerenciadortarefas.service;

import br.com.rafaelcavalcante.gerenciadortarefas.model.Tarefa;
import br.com.rafaelcavalcante.gerenciadortarefas.model.dto.PessoaDTO;
import br.com.rafaelcavalcante.gerenciadortarefas.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {
    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<PessoaDTO> listarPessoa(){
        return this.pessoaRepository.findAll().stream().map(pessoa -> {
            int totalHoras = pessoa.getTarefas().stream().mapToInt(Tarefa::getDuracao).sum();
            return new PessoaDTO(pessoa.getNome(), pessoa.getDepartamento(), totalHoras);
        }).collect(Collectors.toList());
    }
}
