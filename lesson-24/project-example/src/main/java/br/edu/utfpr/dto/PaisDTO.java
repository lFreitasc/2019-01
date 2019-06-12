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

    public static Pais convervFromDTO(PaisDTO pais){
        Pais entidade = Pais.builder().id(new Long(pais.getId())).nome(pais.getNome()).sigla(pais.getSilga()).codigoTelefone(pais.getCodigoTelefone()).build();
        return entidade;
    }

    public static Pais convervToDTO(PaisDTO pais){
        // Pais entidade = Pais.builder().id(new Long(pais.getId())).nome(pais.getNome()).sigla(pais.getSilga()).codigoTelefone(pais.getCodigoTelefone()).build();
        // return entidade;
        PaisDTO dto = PaisDTO.builder().id(Integer.parseInt(String.valueOf(pais.getId())).nome(pais.getNome()).sigla(pais.getSilga()).codigoTelefone(pais.getCodigoTelefone()).build();
        return dto;
    }
}