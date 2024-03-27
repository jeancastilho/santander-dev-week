package com.jcsolutions.sdw24.application;

import com.jcsolutions.sdw24.domain.model.Champions;
import com.jcsolutions.sdw24.domain.ports.ChampionsRepository;

import java.util.List;

public record ListChampionsUseCase(ChampionsRepository championsRepository) {

    public List<Champions> findAll(){
        return championsRepository.findAll();
    }
}
