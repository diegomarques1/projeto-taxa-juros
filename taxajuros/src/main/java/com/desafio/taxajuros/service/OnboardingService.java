package com.desafio.taxajuros.service;

import com.desafio.taxajuros.model.dto.OnboardingDTO;
import com.desafio.taxajuros.model.entity.Interest;
import com.desafio.taxajuros.repository.InterestRepository;
import com.desafio.taxajuros.service.helper.feignclient.InterestFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OnboardingService {

    @Autowired
    private InterestRepository rep;
    @Autowired
    private InterestFeignClient interestClient;

    public OnboardingDTO getInterests(Long top) {
        return interestClient.getInterests(top);
    }

    public List<Interest> save(OnboardingDTO onboardingDTO) {
        return rep.saveAll(onboardingDTO.getValue().stream()
                .map(i -> new Interest(i))
                .collect(Collectors.toList()));
    }

}
