package com.jcsolutions.sdw24.adapters.out;

import com.jcsolutions.sdw24.domain.ports.GenerativeAiApiService;
import feign.FeignException;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@ConditionalOnProperty(name = "generative-ai.provider", havingValue = "GEMINI")
@FeignClient(name = "geminiApi", url = "${gemini.base-url}", configuration = GoogleGeminiService.Config.class)
public interface GoogleGeminiService extends GenerativeAiApiService {

    @PostMapping("/v1beta/models/gemini-pro:generateContent")
    GoogleGeminiResp TextOnlyInput(GoogleGeminiReq req);

    @Override
    default String generateContent(String objective, String context){
        String prompt = """ 
                %s
                %s
                """.formatted(objective, context);

        GoogleGeminiReq req = new GoogleGeminiReq(
                List.of(new Content(List.of(new Part(prompt))))
        );
        GoogleGeminiResp resp = TextOnlyInput(req);
        return resp.candidates().get(0).content().parts().get(0).text();

//        try {
//            GoogleGeminiResp resp = TextOnlyInput(req);
//            return resp.candidates().get(0).content().parts().get(0).text();
//        } catch (FeignException httpErrors){
//            return "Foi mal! Erro de comunicação com a API do Google Gemini.";
//        }catch (Exception unexpectedErroo){
//            return "Foi mal! O retorno da API do Google Gemini não contem os dados esperados.";
//        }

    }

     record GoogleGeminiReq(List<Content> contents){}
    record Content(List<Part> parts){}
    record Part(String text){}
    record GoogleGeminiResp(List<Candidate> candidates){}
    record Candidate(Content content){}

    class Config {
        @Bean
        public RequestInterceptor apiKeyRequestInteceptor(@Value("${GEMINI_API_KEY}") String apiKey){
            return requestTemplate -> requestTemplate.query("key",apiKey);

        }
    }
}
