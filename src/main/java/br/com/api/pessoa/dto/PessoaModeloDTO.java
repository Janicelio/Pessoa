package br.com.api.pessoa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PessoaModeloDTO {
 
    private String nome;
    private Integer idade;
}
