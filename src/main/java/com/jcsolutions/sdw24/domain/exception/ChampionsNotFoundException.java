package com.jcsolutions.sdw24.domain.exception;

public class ChampionsNotFoundException extends RuntimeException{
    public ChampionsNotFoundException(Long championId){
        super("Champion %d not found.".formatted(championId));
    }
}
