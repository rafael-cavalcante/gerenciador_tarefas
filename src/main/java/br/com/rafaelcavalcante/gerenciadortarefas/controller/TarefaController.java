package br.com.rafaelcavalcante.gerenciadortarefas.controller;

import br.com.rafaelcavalcante.gerenciadortarefas.model.Tarefa;
import br.com.rafaelcavalcante.gerenciadortarefas.repository.TarefaRepository;
import br.com.rafaelcavalcante.gerenciadortarefas.service.TarefaService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private TarefaRepository terefaRepository;
    private TarefaService terefaService;

    public TarefaController(TarefaRepository terefaRepository, TarefaService terefaService){
        this.terefaRepository = terefaRepository;
        this.terefaService = terefaService;
    }

    @PostMapping
    @Transactional
    public Tarefa adicionarTarefa(@RequestBody Tarefa tarefa) {
        return this.terefaRepository.save(tarefa);
    }

    @GetMapping("/pendentes")
    public List<Tarefa> listarTarefasPendentes() {
        return this.terefaRepository.findTop3ByPessoaIsNullOrderByPrazoAsc();
    }

    @PutMapping("/finalizar/{id}")
    @Transactional
    public Tarefa finalizarTarefa(@PathVariable Long id) {
        Optional<Tarefa> optionalTarefa = this.terefaRepository.findById(id);

        if (optionalTarefa.isPresent()) {
            Tarefa tarefaExistente = optionalTarefa.get();
            tarefaExistente.setFinalizada(true);

            return this.terefaRepository.save(tarefaExistente);
        } else {
            throw new RuntimeException("Tarefa não encontrada com id " + id);
        }
    }

    @PutMapping("/alocar/{tarefaId}")
    public Tarefa alocarPessoaNaTarefa(@PathVariable Long tarefaId, @RequestParam Long pessoaId) {
        Tarefa tarefaAlocada = this.terefaService.alocarPessoaNaTarefa(tarefaId, pessoaId);
        return tarefaAlocada;
    }
}
