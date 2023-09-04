package whetherAsssistent;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

//receive the longitute and latitute from GeoLocation
//request weather info and sent to sd2_chatbot to show user weather info (temperature, wind, etc)
public class WeatherApiInfo {
	GeoLocation geo = new GeoLocation();
	String lat;
	String log;
	double temp;
	double tempMax;
	double tempMin;	
	double wind;
	String date;
	
	
	public WeatherApiInfo() {
		
	}
		

	public WeatherApiInfo(String lat, String log) {
		super();
		this.lat = lat;
		this.log = log;
	}



	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}
	
	void FindWeatherInfo(String lat, String log){
		  String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + lat + "&longitude=" + log + "&hourly=temperature_2m,apparent_temperature,rain,showers&daily=temperature_2m_max,temperature_2m_min,windspeed_10m_max&current_weather=true&timezone=GMT&forecast_days=1";


	        HttpClient httpClient = HttpClient.newHttpClient();


	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(apiUrl))
	                .build();

	        try {
	            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
	                     
	                String responseBody = response.body();
	                
	                
	                //System.out.println(responseBody);  //show all json content
	             // Parse o JSON
	                JSONObject jsonObject = new JSONObject(responseBody);

	                // Extrair "current_weather" e "daily"
	                JSONObject currentWeather = jsonObject.getJSONObject("current_weather");
	                JSONObject dailyData = jsonObject.getJSONObject("daily");
	                
	                WeatherApiInfo sendTemperature = new WeatherApiInfo();
	                
	                temp = currentWeather.getDouble("temperature");
	                sendTemperature.setTemp(temp);
	                	                
	                wind = currentWeather.getDouble("windspeed");	   
	                
	               
	                date = dailyData.getJSONArray("time").getString(0);
	                tempMax = dailyData.getJSONArray("temperature_2m_max").getDouble(0); 
	                tempMin = dailyData.getJSONArray("temperature_2m_min").getDouble(0);	                	              
	                
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    	        
	}//end FindWeatherInfo	
	
	public double getTemp() {
		return temp;
	}


	public void setTemp(double temp) {
		this.temp = temp;
	}


	@Override
    public String toString() {               
		return "Weather in degree: \ntemperature=" + temp + 
        		"\nmax temperature="  + tempMax +
        		"\nmin temperature=" + tempMin +        		      	
        		"\n" + "wind=" + wind +
        		"\ndate: " + date +" ";        	   
    }
}
