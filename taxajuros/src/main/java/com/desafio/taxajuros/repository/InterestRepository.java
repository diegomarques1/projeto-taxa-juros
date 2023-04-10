package com.desafio.taxajuros.repository;

import com.desafio.taxajuros.model.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestRepository extends JpaRepository<Interest,Long> {

    List<Interest> findAllByAnoMes(String anoMes);

}
