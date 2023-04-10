package com.desafio.taxajuros.model.entity;

import com.desafio.taxajuros.model.dto.InterestDTO;
import com.desafio.taxajuros.model.dto.OnboardingInterestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tb_interest")
public class Interest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("mes")
    private String mes;
    @JsonProperty("modalidade")
    private String modalidade;
    @JsonProperty("posicao")
    private Integer posicao;
    @JsonProperty("instituicao_financeira")
    private String instituicaoFinanceira;
    @JsonProperty("taxa_juros_ao_mes")
    private Double taxaJurosAoMes;
    @JsonProperty("taxa_juros_ao_ano")
    private Double taxaJurosAoAno;
    @JsonProperty("cnpj8")
    private Integer cnpj;
    @JsonProperty("ano_mes")
    private String anoMes;

    public Interest(InterestDTO interestDTO) {
        mes = interestDTO.getMes();
        modalidade = interestDTO.getModalidade();
        posicao = interestDTO.getPosicao();
        instituicaoFinanceira = interestDTO.getInstituicaoFinanceira();
        taxaJurosAoMes = interestDTO.getTaxaJurosAoMes();
        taxaJurosAoAno = interestDTO.getTaxaJurosAoAno();
        cnpj = interestDTO.getCnpj();
        anoMes = interestDTO.getAnoMes();
    }

    public Interest(OnboardingInterestDTO onboardingInterestDTO) {
        mes = onboardingInterestDTO.getMes();
        modalidade = onboardingInterestDTO.getModalidade();
        posicao = onboardingInterestDTO.getPosicao();
        instituicaoFinanceira = onboardingInterestDTO.getInstituicaoFinanceira();
        taxaJurosAoMes = onboardingInterestDTO.getTaxaJurosAoMes();
        taxaJurosAoAno = onboardingInterestDTO.getTaxaJurosAoAno();
        cnpj = onboardingInterestDTO.getCnpj();
        anoMes = onboardingInterestDTO.getAnoMes();
    }

}
