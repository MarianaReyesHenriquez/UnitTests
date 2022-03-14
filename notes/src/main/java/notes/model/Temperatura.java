package notes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperatura {

    private String city;
    private String temp;

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

    public void setCity(String city) {
        this.city = city;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

}