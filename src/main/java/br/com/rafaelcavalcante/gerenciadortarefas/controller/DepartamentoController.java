package br.com.rafaelcavalcante.gerenciadortarefas.controller;

import br.com.rafaelcavalcante.gerenciadortarefas.model.Departamento;
import br.com.rafaelcavalcante.gerenciadortarefas.model.dto.DepartamentoDTO;
import br.com.rafaelcavalcante.gerenciadortarefas.repository.DepartamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {
    private DepartamentoRepository departamentoRepository;

    public DepartamentoController(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @PostMapping
    @Transactional
    public Departamento adicionarDepartamento(@RequestBody Departamento departamento) {
        return this.departamentoRepository.save(departamento);
    }

    @GetMapping
    public List<DepartamentoDTO> listarDepartamento(){
        return this.departamentoRepository.findDepartamentoStats();
    }

    @PutMapping("{id}")
    @Transactional
    public Departamento atualizarDepartamento(@PathVariable Long id, @RequestBody Departamento departamento) {
        Optional<Departamento> optionalDepartamento = this.departamentoRepository.findById(id);

        if (optionalDepartamento.isPresent()) {
            Departamento departamentoExistente = optionalDepartamento.get();
            departamentoExistente.setNome(departamento.getNome());

            return this.departamentoRepository.save(departamentoExistente);
        } else {
            throw new RuntimeException("Departamento não encontrada com id " + id);
        }
    }

    @DeleteMapping("{id}")
    @Transactional
    public Optional<Departamento> deletarDepartamento(@PathVariable Long id) {
        Optional<Departamento> optionalDepartamento = this.departamentoRepository.findById(id);

        if (optionalDepartamento.isPresent()) {
            this.departamentoRepository.deleteById(id);

            return optionalDepartamento;
        } else {
            throw new RuntimeException("Departamento não encontrada com id " + id);
        }
    }
}