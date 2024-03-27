package com.jcsolutions.sdw24.domain.ports;

import com.jcsolutions.sdw24.domain.model.Champions;

import java.util.List;
import java.util.Optional;

public interface ChampionsRepository {
    List<Champions>findAll();

    Optional<Champions> findById(Long id);

}
