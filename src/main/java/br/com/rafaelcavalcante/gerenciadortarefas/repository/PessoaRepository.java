package br.com.rafaelcavalcante.gerenciadortarefas.repository;

import br.com.rafaelcavalcante.gerenciadortarefas.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
