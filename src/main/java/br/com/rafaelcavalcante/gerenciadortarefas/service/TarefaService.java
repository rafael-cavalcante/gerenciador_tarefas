package br.com.rafaelcavalcante.gerenciadortarefas.service;

import br.com.rafaelcavalcante.gerenciadortarefas.model.Pessoa;
import br.com.rafaelcavalcante.gerenciadortarefas.model.Tarefa;
import br.com.rafaelcavalcante.gerenciadortarefas.repository.PessoaRepository;
import br.com.rafaelcavalcante.gerenciadortarefas.repository.TarefaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {
    private TarefaRepository tarefaRepository;
    private PessoaRepository pessoaRepository;

    public TarefaService(TarefaRepository tarefaRepository, PessoaRepository pessoaRepository) {
        this.tarefaRepository = tarefaRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    public Tarefa alocarPessoaNaTarefa(Long tarefaId, Long pessoaId) {
        Tarefa tarefa = this.tarefaRepository.findById(tarefaId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        Pessoa pessoa = this.pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        if (!tarefa.getDepartamento().equals(pessoa.getDepartamento())) {
            throw new IllegalArgumentException("Pessoa e tarefa devem pertencer ao mesmo departamento");
        }

        tarefa.setPessoa(pessoa);
        return this.tarefaRepository.save(tarefa);
    }
}
