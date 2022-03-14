package weather.model.witt; 
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
public class CurrentCondition{
    @JsonProperty("FeelsLikeC") 
    public String feelsLikeC;
    @JsonProperty("FeelsLikeF") 
    public String feelsLikeF;
    public String cloudcover;
    public String humidity;
    public String localObsDateTime;
    public String observation_time;
    public String precipInches;
    public String precipMM;
    public String pressure;
    public String pressureInches;
    public String temp_C;
    public String temp_F;
    public String uvIndex;
    public String visibility;
    public String visibilityMiles;
    public String weatherCode;
    public ArrayList<WeatherDesc> weatherDesc;
    public ArrayList<WeatherIconUrl> weatherIconUrl;
    public String winddir16Point;
    public String winddirDegree;
    public String windspeedKmph;
    public String windspeedMiles;
}
