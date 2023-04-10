package com.desafio.taxajuros.controller;

import com.desafio.taxajuros.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/onboarding")
public class OnboardingController {

    private OnboardingService service;

    @Autowired
    public OnboardingController(OnboardingService service) {
        this.service = service;
    }

    @PostMapping("/{top}")
    public ResponseEntity save(@PathVariable("top") Long top) {
        return new ResponseEntity(service.save(service.getInterests(top)), HttpStatus.CREATED);
    }

}
