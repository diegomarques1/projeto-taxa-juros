package com.desafio.taxajuros.service;

import com.desafio.taxajuros.model.dto.OnboardingDTO;
import com.desafio.taxajuros.model.entity.Interest;
import com.desafio.taxajuros.repository.InterestRepository;
import com.desafio.taxajuros.service.helper.feignclient.InterestFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.desafio.taxajuros.constant.Constants.DEFAULT_ID;
import static com.desafio.taxajuros.constant.Constants.TOP;
import static com.desafio.taxajuros.helper.TestHelper.VALID_INTEREST_LIST;
import static com.desafio.taxajuros.helper.TestHelper.VALID_ONBOARDING_DTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class OnboardingServiceTest {

    @InjectMocks
    private OnboardingService service;

    @Mock
    private InterestRepository rep;

    @Mock
    private InterestFeignClient client;

    @Test
    public void shouldGetInterestsFromFeignClient() {
        when(client.getInterests(anyLong())).thenReturn(VALID_ONBOARDING_DTO);

        OnboardingDTO response = service.getInterests(TOP);

        assertNotNull(response);
        assertEquals(TOP, response.getValue().size());
        verify(client, times(1))
                .getInterests(anyLong());
    }

    @Test
    public void shouldSaveOnboardingDtoAsInterestObject() {
        VALID_INTEREST_LIST.get(0).setId(DEFAULT_ID);
        VALID_INTEREST_LIST.get(1).setId(DEFAULT_ID);
        when(rep.saveAll(anyList())).thenReturn(VALID_INTEREST_LIST);

        List<Interest> response = service.save(VALID_ONBOARDING_DTO);

        assertNotNull(response);
        assertEquals(DEFAULT_ID, response.get(0).getId());
        assertEquals(VALID_INTEREST_LIST.get(0).getTaxaJurosAoMes(), response.get(0).getTaxaJurosAoMes());
        verify(rep, times(1)).saveAll(anyList());
    }

}
