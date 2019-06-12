package br.edu.utfpr.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaisDTO {
    private int id;
    private String nome;
    private String sigla;
    private int codigoTelefone;
    
}