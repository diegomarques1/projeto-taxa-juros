package com.desafio.taxajuros.service;

import com.desafio.taxajuros.exception.ObjectEmptyException;
import com.desafio.taxajuros.exception.ObjectNotFoundException;
import com.desafio.taxajuros.model.dto.InterestDTO;
import com.desafio.taxajuros.model.entity.Interest;
import com.desafio.taxajuros.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InterestService {

    private InterestRepository rep;

    @Autowired
    public InterestService(InterestRepository rep) {
        this.rep = rep;
    }

    public List<Interest> getInterests(Pageable pageable) {
        Page<Interest> interestPage = rep.findAll(pageable);
        if (!interestPage.hasContent()) throw new ObjectEmptyException();

        return interestPage.stream().collect(Collectors.toList());
    }

    public Interest getInterestById(Long id) {
        return rep.findById(id).orElseThrow(
            () -> new ObjectNotFoundException("Interest rate of id " + id + " not found"));
    }

    public List<Interest> getInterestByYearMonth(String anoMes) {
        List<Interest> listYearMonth = rep.findAllByAnoMes(anoMes);
        if (listYearMonth.isEmpty())
            throw new ObjectNotFoundException("Interest rates of anoMes " + anoMes + " not found");

        return listYearMonth;
    }

    public Interest create(InterestDTO interestDTO) {
        return rep.save(new Interest(interestDTO));
    }

    public Interest update(InterestDTO interestDTO, Long id) {
        Optional<Interest> optionalInterest = rep.findById(id);

        if (!optionalInterest.isPresent())
            throw new ObjectNotFoundException("Interest rate of id " + id + " not found");

        Interest interest = Interest.builder()
                .id(id)
                .anoMes(interestDTO.getAnoMes())
                .mes(interestDTO.getMes())
                .modalidade(interestDTO.getModalidade())
                .posicao(interestDTO.getPosicao())
                .instituicaoFinanceira(interestDTO.getInstituicaoFinanceira())
                .taxaJurosAoMes(interestDTO.getTaxaJurosAoMes())
                .taxaJurosAoAno(interestDTO.getTaxaJurosAoAno())
                .cnpj(interestDTO.getCnpj())
                .build();

        return rep.save(interest);
    }

    public void delete(Long id) {
        getInterestById(id);
        rep.deleteById(id);
    }

}