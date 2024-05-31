package br.com.rafaelcavalcante.gerenciadortarefas.repository;

import br.com.rafaelcavalcante.gerenciadortarefas.model.Departamento;
import br.com.rafaelcavalcante.gerenciadortarefas.model.dto.DepartamentoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    @Query("SELECT new br.com.rafaelcavalcante.gerenciadortarefas.model.dto.DepartamentoDTO(d.id, d.nome, COUNT(DISTINCT p.id), COUNT(DISTINCT t.id)) " +
            "FROM Departamento d " +
            "LEFT JOIN Pessoa p ON d.id = p.departamento.id " +
            "LEFT JOIN Tarefa t ON d.id = t.departamento.id " +
            "GROUP BY d.id, d.nome")
    List<DepartamentoDTO> findDepartamentoStats();
}
