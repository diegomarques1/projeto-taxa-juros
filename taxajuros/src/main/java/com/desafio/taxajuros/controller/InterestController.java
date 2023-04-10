package com.desafio.taxajuros.controller;

import com.desafio.taxajuros.model.dto.InterestDTO;
import com.desafio.taxajuros.model.entity.Interest;
import com.desafio.taxajuros.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/juros")
public class InterestController {

    private InterestService service;

    @Autowired
    public InterestController(InterestService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Interest>> getInterests(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "100") Integer size) {
        return ResponseEntity.ok(service.getInterests(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Interest> getInterestById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getInterestById(id));
    }

    @GetMapping("/anoMes/{anoMes}")
    public ResponseEntity<List<Interest>> getInterestByYearMonth(@PathVariable("anoMes") String anoMes) {
        return ResponseEntity.ok(service.getInterestByYearMonth(anoMes));
    }

    @PostMapping
    public ResponseEntity<Interest> newInterest(@RequestBody @Valid InterestDTO interestDTO) {
        return new ResponseEntity(service.create(interestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Interest> updateInterest(@RequestBody @Valid InterestDTO interestDTO, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(interestDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteInterestById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
