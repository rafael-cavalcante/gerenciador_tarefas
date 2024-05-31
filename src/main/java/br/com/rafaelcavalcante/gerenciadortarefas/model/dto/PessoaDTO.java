package br.com.rafaelcavalcante.gerenciadortarefas.model.dto;

import br.com.rafaelcavalcante.gerenciadortarefas.model.Departamento;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PessoaDTO {
    private String nome;
    private Departamento departamento;
    private int totalHoras;
}
