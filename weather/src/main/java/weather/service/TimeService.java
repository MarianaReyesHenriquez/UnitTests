package weather.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import weather.exception.ApiWebClientException;
import weather.model.Temperatura;
import weather.model.witt.Root;

@Service
public class TimeService implements ITimeService {

    Logger logger = LoggerFactory.getLogger(TimeService.class);

    private final WebClient client;

    public TimeService(WebClient.Builder builder) {
        this.client = builder.baseUrl("https://wttr.in").build();
    }

    public Temperatura getByCity(String city) {
        

        Root response = client.get()
            .uri("/{city}?format=j1", city)
            .retrieve()
            .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                logger.error("Error endpoint with status code {}", clientResponse.statusCode());
                throw new ApiWebClientException("HTTP Status 500 error");
            })
            .bodyToMono(Root.class)
            .block();

        return new Temperatura(response.nearest_area.get(0).areaName.get(0).value, response.current_condition.get(0).temp_C);
    }  
    
}
