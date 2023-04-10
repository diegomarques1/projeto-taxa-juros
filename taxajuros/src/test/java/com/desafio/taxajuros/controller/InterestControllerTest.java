package com.desafio.taxajuros.controller;

import com.desafio.taxajuros.model.dto.InterestDTO;
import com.desafio.taxajuros.model.entity.Interest;
import com.desafio.taxajuros.service.InterestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.desafio.taxajuros.constant.Constants.*;
import static com.desafio.taxajuros.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class InterestControllerTest {

        private MockMvc mockMvc;

        private ObjectMapper objectMapper;

        @InjectMocks
        private InterestController controller;

        @Mock
        private InterestService service;

        @BeforeEach
        public void setup() {
            objectMapper = new ObjectMapper();
            mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        }

        @Test
        public void shouldGetInterestListWhenGetRequestHasResponseOK() throws Exception {
                when(service.getInterests(VALID_PAGEABLE)).thenReturn(VALID_INTEREST_LIST);

                MvcResult response = mockMvc.perform(get(
                        "/juros?size=" + PAGEABLE_SIZE + "&page=" + PAGEABLE_PAGE)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andReturn();


                List<Interest> interestList = List.of(objectMapper
                        .readValue(response.getResponse().getContentAsString(), Interest[].class));


                assertNotNull(response);
                assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());
                assertEquals(VALID_INTEREST.getId(), interestList.get(0).getId());
                verify(service, times(1)).getInterests(VALID_PAGEABLE);
        }

        @Test
        public void shouldGetInterestByIdWhenRequestHasResponseOK() throws Exception {
                when(service.getInterestById(anyLong())).thenReturn(VALID_INTEREST);

                MvcResult response = mockMvc.perform(get("/juros/" + DEFAULT_ID)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andReturn();


                Interest interest = objectMapper.readValue(
                        response.getResponse().getContentAsString(), Interest.class);


                assertNotNull(response);
                assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());
                assertEquals(VALID_INTEREST.getId(), interest.getId());
                verify(service, times(1))
                        .getInterestById(anyLong());
        }

        @Test
        public void shouldGetInterestsByYearMonthWhenGetRequestHasResponseOK() throws Exception {
                when(service.getInterestByYearMonth(ANO_MES)).thenReturn(VALID_INTEREST_LIST);

                MvcResult response = mockMvc.perform(get("/juros/anoMes/" + ANO_MES)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();


                List<Interest> interestList = List.of(objectMapper
                        .readValue(response.getResponse().getContentAsString(), Interest[].class));


                assertNotNull(response);
                assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());
                assertEquals(VALID_INTEREST.getId(), interestList.get(0).getId());
                assertEquals(VALID_INTEREST.getAnoMes(), interestList.get(0).getAnoMes());
                verify(service, times(1))
                        .getInterestByYearMonth(ANO_MES);
        }

        @Test
        public void shouldCreateInterestWhenPostRequestHasResponseOK() throws Exception {
                when(service.create(any(InterestDTO.class))).thenReturn(VALID_INTEREST);

                MvcResult response = mockMvc.perform(post("/juros")
                        .content(objectMapper.writeValueAsString(VALID_INTEREST_DTO))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andReturn();

                Interest interest = objectMapper.readValue(
                        response.getResponse().getContentAsString(), Interest.class);

                assertNotNull(response);
                assertEquals(HttpStatus.CREATED.value(), response.getResponse().getStatus());
                assertEquals(VALID_INTEREST.getId(), interest.getId());
                assertEquals(VALID_INTEREST.getTaxaJurosAoMes(), interest.getTaxaJurosAoMes());
                verify(service, times(1))
                        .create(VALID_INTEREST_DTO);
        }

        @Test
        public void shouldUpdateInterestWhenPutRequestHasResponseOK() throws Exception {
                when(service.update(any(InterestDTO.class), anyLong())).thenReturn(VALID_INTEREST);

                MvcResult response = mockMvc.perform(put("/juros/" + DEFAULT_ID)
                                .content(objectMapper.writeValueAsString(VALID_INTEREST_DTO))
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andReturn();

                Interest interest = objectMapper.readValue(
                        response.getResponse().getContentAsString(), Interest.class);

                assertNotNull(response);
                assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());
                assertEquals(VALID_INTEREST.getId(), interest.getId());
                assertEquals(VALID_INTEREST.getTaxaJurosAoMes(), interest.getTaxaJurosAoMes());
                verify(service, times(1))
                        .update(any(InterestDTO.class), anyLong());
        }

        @Test
        public void shouldDeleteInterestByIdWhenDeleteRequestHasResponseOK() throws Exception {
                doNothing().when(service).delete(anyLong());

                MvcResult response = mockMvc.perform(delete("/juros/" + DEFAULT_ID)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andReturn();

                assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());
                verify(service, times(1))
                        .delete(anyLong());
        }

}
