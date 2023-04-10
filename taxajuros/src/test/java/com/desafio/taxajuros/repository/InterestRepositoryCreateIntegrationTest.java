package com.desafio.taxajuros.repository;

import com.desafio.taxajuros.model.dto.InterestDTO;
import com.desafio.taxajuros.model.entity.Interest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.desafio.taxajuros.helper.TestHelper.VALID_INTEREST_DTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InterestRepositoryCreateIntegrationTest {

    @Autowired
    private InterestRepository rep;

    @BeforeEach
    public void setup() {
        rep.save(new Interest(VALID_INTEREST_DTO));
        rep.save(new Interest(VALID_INTEREST_DTO));
    }

    @AfterEach
    public void tearDown() {
        rep.deleteAll();
    }

    @Test
    public void shouldCreateInterestInRepository() {
        InterestDTO interestDTO = InterestDTO.builder()
                .mes("Fev-2023")
                .modalidade("FINANCIAMENTO IMOBILIÁRIO COM TAXAS REGULADAS - PRÉ-FIXADO")
                .posicao(2)
                .instituicaoFinanceira("ITAÚ UNIBANCO S.A.")
                .taxaJurosAoMes(0.41)
                .taxaJurosAoAno(4.23)
                .cnpj(87654321)
                .anoMes("2023-02")
                .build();

        rep.save(new Interest(interestDTO));

        Optional<Interest> optional = rep.findById(3L);

        assertTrue(optional.isPresent());
        assertEquals(3, rep.findAll().size());
        assertEquals(3L, optional.get().getId());
        assertEquals(87654321, optional.get().getCnpj());
    }

}
