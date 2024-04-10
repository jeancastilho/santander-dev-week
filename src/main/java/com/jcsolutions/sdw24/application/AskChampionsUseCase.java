package com.jcsolutions.sdw24.application;

import com.jcsolutions.sdw24.domain.exception.ChampionsNotFoundException;
import com.jcsolutions.sdw24.domain.model.Champions;
import com.jcsolutions.sdw24.domain.ports.ChampionsRepository;
import com.jcsolutions.sdw24.domain.ports.GenerativeAiApiService;

import java.util.List;

public record AskChampionsUseCase(ChampionsRepository championsRepository, GenerativeAiApiService genAiApi) {

    public String askChampion(Long championId, String question){
       Champions champions =  championsRepository.findById(championId)
                .orElseThrow(() -> new ChampionsNotFoundException(championId));

        String championContext = champions.generateContextByQuestion(question);
        String objective = """
                Atue como uma assistente com a habilidade de se comportar como os Campeões do League of Legends (LOL).
                Responda perguntas incorporando a personalidade e estilo de um determinado campeão.
                Segue a pergunta, o nome do Campeão e sua respectiva lore (história):
                """;

        genAiApi.generateContent(objective, championContext);
        return championContext;
    }

}
