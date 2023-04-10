package com.desafio.taxajuros.service;

import com.desafio.taxajuros.exception.ObjectEmptyException;
import com.desafio.taxajuros.exception.ObjectNotFoundException;
import com.desafio.taxajuros.model.dto.InterestDTO;
import com.desafio.taxajuros.model.entity.Interest;
import com.desafio.taxajuros.repository.InterestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.desafio.taxajuros.constant.Constants.*;
import static com.desafio.taxajuros.helper.TestHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InterestServiceTest {

    @InjectMocks
    private InterestService service;

    @Mock
    private InterestRepository rep;

    @BeforeEach
    public void setup() {
        VALID_INTEREST.setId(1L);
    }

    @Test
    public void shouldCreateInterestOfInterestDtoResponseOK() {
        when(rep.save(any(Interest.class))).thenReturn(VALID_INTEREST);

        Interest response = service.create(VALID_INTEREST_DTO);

        assertNotNull(response);
        assertEquals(VALID_INTEREST.getId(), response.getId());
        verify(rep, times(1))
                .save(any(Interest.class));
    }

    @Test
    public void shouldGetAllInterestsResponseOK() {
        when(rep.findAll(VALID_PAGEABLE)).thenReturn(VALID_INTEREST_PAGE);

        List<Interest> response = service.getInterests(VALID_PAGEABLE);

        assertNotNull(response);
        assertFalse(response.isEmpty());
        verify(rep, times(1))
                .findAll(any(Pageable.class));
    }

    @Test
    public void shouldGetAllInterestsException() {
        when(rep.findAll(any(Pageable.class))).thenReturn(Page.empty());

        try {
            List<Interest> response = service.getInterests(VALID_PAGEABLE);
        } catch (ObjectEmptyException e) {
            assertThrows(ObjectEmptyException.class, () -> service.getInterests(VALID_PAGEABLE));
        }
    }

    @Test
    public void shouldGetInterestByIdResponseOK() {
        when(rep.findById(anyLong())).thenReturn(Optional.of(VALID_INTEREST));

        Interest response = service.getInterestById(VALID_INTEREST.getId());

        assertNotNull(response);
        assertEquals(VALID_INTEREST.getId(), response.getId());
        verify(rep, times(1))
                .findById(anyLong());

    }

    @Test
    public void shouldGetInterestByIdException() {
        when(rep.findById(DEFAULT_ID)).thenReturn(Optional.empty());

        try {
            Interest response = service.getInterestById(DEFAULT_ID);
        } catch (ObjectNotFoundException e) {
            assertEquals("Interest rate of id 1 not found", e.getMessage());
        }
    }

    @Test
    public void shouldGetInterestByYearMonthResponseOK() {
        when(rep.findAllByAnoMes(anyString())).thenReturn(VALID_INTEREST_LIST);

        List<Interest> response = service.getInterestByYearMonth(VALID_INTEREST.getAnoMes());

        assertNotNull(response);
        assertFalse(response.isEmpty());
        verify(rep, times(1))
                .findAllByAnoMes(VALID_INTEREST.getAnoMes());
    }

    @Test
    public void shouldGetInterestByYearMonthException() {
        when(rep.findAllByAnoMes(ANO_MES)).thenReturn(List.of());

        try {
            List<Interest> response = service.getInterestByYearMonth(ANO_MES);
        } catch (ObjectNotFoundException e) {
            assertEquals("Interest rates of anoMes 2023-01 not found", e.getMessage());
        }
    }

    @Test
    public void shouldUpdateInterestObjectResponseOK() {
        InterestDTO interestDTO = VALID_INTEREST_DTO;
        interestDTO.setPosicao(5);
        Interest interest = new Interest(interestDTO);

        when(rep.findById(interest.getId()))
                .thenReturn(Optional.of(interest));

        when(rep.save(interest)).thenReturn(interest);

        Interest response = service.update(interestDTO, interest.getId());

        assertNotNull(response);
        assertEquals(interest.getId(), response.getId());
        assertEquals(5, response.getPosicao());
        verify(rep, times(1))
                .findById(interest.getId());
        verify(rep, times(1))
                .save(any(Interest.class));

        VALID_INTEREST_DTO.setPosicao(POSICAO);
    }

    @Test
    public void shouldUpdateInterestObjectNotFoundException() {
        when(rep.findById(DEFAULT_ID)).thenReturn(Optional.empty());

        try {
            Interest response = service.update(VALID_INTEREST_DTO, DEFAULT_ID);
        } catch (ObjectNotFoundException e) {
            assertEquals("Interest rate of id 1 not found", e.getMessage());
        }
    }

    @Test
    public void shouldDeleteInterestByIdResponseOK() {
        when(rep.findById(anyLong())).thenReturn(Optional.of(VALID_INTEREST));
        doNothing().when(rep).deleteById(anyLong());

        service.delete(DEFAULT_ID);

        verify(rep, times(1)).findById(anyLong());
        verify(rep, times(1)).deleteById(anyLong());
    }

}
