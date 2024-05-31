package br.com.rafaelcavalcante.gerenciadortarefas.repository;

import br.com.rafaelcavalcante.gerenciadortarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
