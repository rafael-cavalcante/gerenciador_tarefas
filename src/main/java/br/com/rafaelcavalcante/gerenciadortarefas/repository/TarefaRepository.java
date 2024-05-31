package br.com.rafaelcavalcante.gerenciadortarefas.repository;

import br.com.rafaelcavalcante.gerenciadortarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findTop3ByPessoaIsNullOrderByPrazoAsc();
}
