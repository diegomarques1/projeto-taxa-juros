package com.desafio.taxajuros.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InterestDTO {

    @JsonProperty("mes")
    @NotBlank(message = "O campo mês deve ser preenchido")
    private String mes;

    @JsonProperty("modalidade")
    @NotBlank(message = "O campo modalidade deve ser preenchido")
    private String modalidade;

    @JsonProperty("posicao")
    @NotNull(message = "O campo posição não deve ser nulo")
    private Integer posicao;

    @JsonProperty("instituicao_financeira")
    @NotBlank(message = "O campo instituição financeira deve ser preenchido")
    private String instituicaoFinanceira;

    @JsonProperty("taxa_juros_ao_mes")
    @NotNull(message = "O campo taxa de juros ao mês não deve ser nulo")
    private Double taxaJurosAoMes;

    @JsonProperty("taxa_juros_ao_ano")
    @NotNull(message = "O campo taxa de juros ao ano não deve ser nulo")
    private Double taxaJurosAoAno;

    @JsonProperty("cnpj8")
    @NotNull(message = "O campo cnpj não deve ser nulo")
    private Integer cnpj;

    @JsonProperty("ano_mes")
    @NotBlank(message = "O campo ano mês deve ser preenchido")
    private String anoMes;

}
