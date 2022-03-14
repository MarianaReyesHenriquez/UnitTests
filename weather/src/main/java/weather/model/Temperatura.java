package weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperatura {

    private final String city;
    private final String temp;

    public Temperatura(String city, String temp){
        this.city = city;
        this.temp = temp;
    }

    @JsonProperty("city")
    public String getCity(){
        return city;
    }

    @JsonProperty("temp")
    public String getTemp() {
        return temp;
    }

}