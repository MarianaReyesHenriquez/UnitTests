package notes.service;

import notes.model.Temperatura;

public interface ITimeService {
    Temperatura getByCity(String city);
}
