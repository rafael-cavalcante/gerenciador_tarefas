package br.com.rafaelcavalcante.gerenciadortarefas.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PessoaHorasDTO {
    private Long id;
    private String nome;
    private Double mediaHorasGastas;
}
