package com.desafio.taxajuros.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OnboardingDTO {

    @JsonProperty("@odata.context")
    private String oDataContext;
    @JsonProperty("value")
    private List<OnboardingInterestDTO> value;

}
