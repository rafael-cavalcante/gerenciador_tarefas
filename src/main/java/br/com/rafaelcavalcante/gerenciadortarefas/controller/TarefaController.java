package br.com.rafaelcavalcante.gerenciadortarefas.controller;

import br.com.rafaelcavalcante.gerenciadortarefas.model.Tarefa;
import br.com.rafaelcavalcante.gerenciadortarefas.repository.TarefaRepository;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private TarefaRepository terefaRepository;

    public TarefaController(TarefaRepository terefaRepository){
        this.terefaRepository = terefaRepository;
    }

    @PostMapping
    @Transactional
    public Tarefa adicionarTarefa(@RequestBody Tarefa tarefa) {
        return this.terefaRepository.save(tarefa);
    }
}
