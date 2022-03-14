package weather.service;

import weather.model.Temperatura;

public interface ITimeService {
    Temperatura getByCity(String city);
}
