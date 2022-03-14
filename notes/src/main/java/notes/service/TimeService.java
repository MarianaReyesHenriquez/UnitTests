package notes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import notes.exception.ApiWebClientException;
import notes.model.Temperatura;

@Service
public class TimeService implements ITimeService {

    Logger logger = LoggerFactory.getLogger(TimeService.class);

    private final WebClient client;


    public TimeService(WebClient.Builder builder) {
        this.client = builder.build();
    }

    @Value("${WEATHER_URL}")
    private String url;
    public Temperatura getByCity(String city) {
        logger.info(url);
        Temperatura response = client.get()
            .uri(url + "/{city}", city)
            .retrieve()
            .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                logger.error("Error endpoint with status code {}", clientResponse.statusCode());
                throw new ApiWebClientException("HTTP Status 500 error");
            })
            .bodyToMono(Temperatura.class)
            .block();

        return response;
    }  
    
}