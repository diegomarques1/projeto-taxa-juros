package com.desafio.taxajuros.repository;

import com.desafio.taxajuros.model.entity.Interest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.desafio.taxajuros.constant.Constants.ANO_MES;
import static com.desafio.taxajuros.helper.TestHelper.VALID_INTEREST_DTO;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InterestRepositoryFindlByAnoMesIntegrationTest {

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
    public void shouldFindByYearMonthInRepository() {
        List<Interest> interestList = rep.findAllByAnoMes(ANO_MES);

        assertNotNull(interestList);
        assertFalse(interestList.isEmpty());
        assertEquals(2, interestList.size());
        assertEquals(ANO_MES, interestList.get(0).getAnoMes());
    }

}