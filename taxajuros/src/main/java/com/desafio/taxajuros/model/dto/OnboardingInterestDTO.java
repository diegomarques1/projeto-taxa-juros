package com.desafio.taxajuros.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OnboardingInterestDTO {
    @JsonProperty("Mes")
    private String mes;
    @JsonProperty("Modalidade")
    private String modalidade;
    @JsonProperty("Posicao")
    private Integer posicao;
    @JsonProperty("InstituicaoFinanceira")
    private String instituicaoFinanceira;
    @JsonProperty("TaxaJurosAoMes")
    private Double taxaJurosAoMes;
    @JsonProperty("TaxaJurosAoAno")
    private Double taxaJurosAoAno;
    @JsonProperty("cnpj8")
    private Integer cnpj;

    private String anoMes;

}
