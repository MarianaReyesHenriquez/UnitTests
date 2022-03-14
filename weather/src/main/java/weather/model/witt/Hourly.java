package weather.model.witt; 
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
public class Hourly{
    @JsonProperty("DewPointC") 
    public String dewPointC;
    @JsonProperty("DewPointF") 
    public String dewPointF;
    @JsonProperty("FeelsLikeC") 
    public String feelsLikeC;
    @JsonProperty("FeelsLikeF") 
    public String feelsLikeF;
    @JsonProperty("HeatIndexC") 
    public String heatIndexC;
    @JsonProperty("HeatIndexF") 
    public String heatIndexF;
    @JsonProperty("WindChillC") 
    public String windChillC;
    @JsonProperty("WindChillF") 
    public String windChillF;
    @JsonProperty("WindGustKmph") 
    public String windGustKmph;
    @JsonProperty("WindGustMiles") 
    public String windGustMiles;
    public String chanceoffog;
    public String chanceoffrost;
    public String chanceofhightemp;
    public String chanceofovercast;
    public String chanceofrain;
    public String chanceofremdry;
    public String chanceofsnow;
    public String chanceofsunshine;
    public String chanceofthunder;
    public String chanceofwindy;
    public String cloudcover;
    public String humidity;
    public String precipInches;
    public String precipMM;
    public String pressure;
    public String pressureInches;
    public String tempC;
    public String tempF;
    public String time;
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
