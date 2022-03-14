package weather.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import weather.model.Temperatura;
import weather.service.ITimeService;


@RestController
@RequestMapping("weather")
public class TimeController {
    Logger logger = LoggerFactory.getLogger(TimeController.class);

    @Autowired
    private ITimeService timeService;

    //Devuelve la temperatura de la ciudad especificada en la URL
    @GetMapping("/{cityname}")
    Temperatura temp(@PathVariable("cityname") String city) {
        logger.info("he entrado por weather/{cityname}");

        return timeService.getByCity(city);
    }
}
