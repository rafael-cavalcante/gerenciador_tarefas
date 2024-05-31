package br.com.rafaelcavalcante.gerenciadortarefas.repository;

import br.com.rafaelcavalcante.gerenciadortarefas.model.Pessoa;
import br.com.rafaelcavalcante.gerenciadortarefas.model.dto.PessoaHorasDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query("SELECT new br.com.rafaelcavalcante.gerenciadortarefas.model.dto.PessoaHorasDTO(p.id, p.nome, AVG(t.duracao)) " +
            "FROM Pessoa p " +
            "JOIN p.tarefas t " +
            "WHERE p.nome LIKE %:nome% " +
            "AND t.prazo >= :dataInicio " +
            "AND t.prazo <= :dataFim " +
            "GROUP BY p.id, p.nome")
    List<PessoaHorasDTO> findPessoaHorasByNomeAndPeriodo(@Param("nome") String nome,
                                                         @Param("dataInicio") LocalDate dataInicio,
                                                         @Param("dataFim") LocalDate dataFim);
}
