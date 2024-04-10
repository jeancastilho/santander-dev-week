package com.jcsolutions.sdw24;

import com.jcsolutions.sdw24.application.AskChampionsUseCase;
import com.jcsolutions.sdw24.application.ListChampionsUseCase;
import com.jcsolutions.sdw24.domain.ports.ChampionsRepository;
import com.jcsolutions.sdw24.domain.ports.GenerativeAiApiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ListChampionsUseCase provideListChampionsUseCase(ChampionsRepository championsRepository){
		return  new ListChampionsUseCase(championsRepository);
	}

	@Bean
	public AskChampionsUseCase provideAskChampionsUseCase(ChampionsRepository championsRepository, GenerativeAiApiService genAiApiService){
		return  new AskChampionsUseCase(championsRepository, genAiApiService);
	}

}
