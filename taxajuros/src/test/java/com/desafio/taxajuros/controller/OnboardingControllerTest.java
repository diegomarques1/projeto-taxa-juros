package com.desafio.taxajuros.controller;

import com.desafio.taxajuros.model.dto.OnboardingDTO;
import com.desafio.taxajuros.model.entity.Interest;
import com.desafio.taxajuros.service.OnboardingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.desafio.taxajuros.constant.Constants.TAXA_JUROS_AO_MES;
import static com.desafio.taxajuros.constant.Constants.TOP;
import static com.desafio.taxajuros.helper.TestHelper.VALID_INTEREST_LIST;
import static com.desafio.taxajuros.helper.TestHelper.VALID_ONBOARDING_DTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class OnboardingControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @InjectMocks
    private OnboardingController controller;

    @Mock
    private OnboardingService service;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void shouldSaveInterestsWhenPostRequestHasResponseOk() throws Exception {
        when(service.getInterests(TOP)).thenReturn(VALID_ONBOARDING_DTO);
        when(service.save(any(OnboardingDTO.class))).thenReturn(VALID_INTEREST_LIST);

        MvcResult response = mockMvc.perform(post("/onboarding/" + TOP))
                .andExpect(status().isCreated())
                .andReturn();

        List<Interest> interestList = List.of(objectMapper.readValue(
                response.getResponse().getContentAsString(), Interest[].class));

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED.value(), response.getResponse().getStatus());
        assertEquals(TOP, interestList.size());
        assertEquals(TAXA_JUROS_AO_MES, interestList.get(0).getTaxaJurosAoMes());
        verify(service, times(1))
                .save(any(OnboardingDTO.class));

    }

}
