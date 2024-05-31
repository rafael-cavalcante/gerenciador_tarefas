package br.com.rafaelcavalcante.gerenciadortarefas.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DepartamentoDTO {
    private Long id;
    private String nome;
    private Long quantidadePessoas;
    private Long quantidadeTarefas;
}
