package com.desafio.taxajuros.helper;

import com.desafio.taxajuros.model.dto.InterestDTO;
import com.desafio.taxajuros.model.dto.OnboardingDTO;
import com.desafio.taxajuros.model.dto.OnboardingInterestDTO;
import com.desafio.taxajuros.model.entity.Interest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.desafio.taxajuros.constant.Constants.*;

public class TestHelper {

    public static InterestDTO VALID_INTEREST_DTO =
            InterestDTO.builder()
            .mes(MES)
            .modalidade(MODALIDADE)
            .posicao(POSICAO)
            .instituicaoFinanceira(INSTITUICAO_FINANCEIRA)
            .taxaJurosAoMes(TAXA_JUROS_AO_MES)
            .taxaJurosAoAno(TAXA_JUROS_AO_ANO)
            .cnpj(CNPJ)
            .anoMes(ANO_MES)
            .build();

    public static Interest VALID_INTEREST = new Interest(VALID_INTEREST_DTO);
    public static List<Interest> VALID_INTEREST_LIST = List.of(VALID_INTEREST, VALID_INTEREST);
    public static Pageable VALID_PAGEABLE = PageRequest.of(PAGEABLE_PAGE, PAGEABLE_SIZE);
    public static Page<Interest> VALID_INTEREST_PAGE = new PageImpl<>(VALID_INTEREST_LIST, VALID_PAGEABLE, 0);

    public static OnboardingInterestDTO VALID_ONBOARDING_INTEREST_DTO = new OnboardingInterestDTO(
    MES, MODALIDADE, POSICAO, INSTITUICAO_FINANCEIRA, TAXA_JUROS_AO_MES, TAXA_JUROS_AO_ANO, CNPJ, ANO_MES);
    public static List<OnboardingInterestDTO> VALID_ONBOARDING_INTEREST_DTO_LIST = List.of(
            VALID_ONBOARDING_INTEREST_DTO, VALID_ONBOARDING_INTEREST_DTO);
    public static OnboardingDTO VALID_ONBOARDING_DTO = new OnboardingDTO(
            null, VALID_ONBOARDING_INTEREST_DTO_LIST);

}
