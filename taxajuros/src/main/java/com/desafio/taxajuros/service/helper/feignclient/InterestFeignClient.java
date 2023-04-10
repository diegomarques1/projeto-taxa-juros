package com.desafio.taxajuros.service.helper.feignclient;

import com.desafio.taxajuros.model.dto.OnboardingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "taxajuros", url = "https://olinda.bcb.gov.br/olinda/servico/taxaJuros/versao/v2/odata/TaxasJurosMensalPorMes?$format=json&")
public interface InterestFeignClient {

    @GetMapping()
    OnboardingDTO getInterests(@RequestParam("$top") Long top);

}