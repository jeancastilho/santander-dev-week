package com.jcsolutions.sdw24.adapters.in;

import com.jcsolutions.sdw24.application.AskChampionsUseCase;
import com.jcsolutions.sdw24.application.ListChampionsUseCase;
import com.jcsolutions.sdw24.domain.model.Champions;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Campeões", description = "Endpoints do domínio de Campeões do LOL.")
@RestController
@RequestMapping("/champions")
public record AskChampionsRestController(AskChampionsUseCase UseCase) {

    @PostMapping("/ask/{championId}")
    public AskChampionResponse askChampion(@RequestBody AskChampionRequest request, @PathVariable Long championId) {

        String answer = UseCase.askChampion(championId, request.question());
        return new AskChampionResponse(answer);
    }

    public record AskChampionRequest(String question){}
    public record AskChampionResponse(String answer){}
}
