package whetherAsssistent;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
//take all whether information about a city using latitude and longitude
//variable latitude and longitude will come from GeoLocation.java

public class Api {

    public static void main( String[] args ) throws Exception
    {
        //implementation of weather API

        /*
        *to test;
            replace text "London" with desired country
            replace text "uk" with desired city
            replace text "lon" to  desired country's abbreviation
         */

        
    	
    	//https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m,windspeed_10m&current_weather=true
    	
    	
    	/*HttpRequest request = HttpRequest.newBuilder()
    			.uri(URI.create("https://weather-api99.p.rapidapi.com/weather?city=tokyo"))
    			.header("X-RapidAPI-Key", "07125382eamsh801520c731a0a36p139578jsncf402d85f1da")
    			.header("X-RapidAPI-Host", "weather-api99.p.rapidapi.com")
    			.method("GET", HttpRequest.BodyPublishers.noBody())
    			.build();
    	HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    	System.out.println(response.body()); 
        */
        
        
    	//https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m,windspeed_10m&current_weather=true
        float latitude = (float) 53.4152268;
        float longitude = (float) -7.96655398901418;

        // Construa a URL da API
       //  String apiUrl = "https://api.open-meteo.com/v1/forecast?city=dublin&hourly=temperature_2m&current_weather=true&forecast_days=3";
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=53.4152268&longitude=-7.96655398901418&hourly=temperature_2m,apparent_temperature&daily=temperature_2m_max,temperature_2m_min,windspeed_10m_max&current_weather=true&timezone=GMT&forecast_days=1";

        // Crie um cliente HTTP
        HttpClient httpClient = HttpClient.newHttpClient();

        // Crie uma solicitação HTTP GET
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        // Envie a solicitação e obtenha a resposta JSON
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                     
                String responseBody = response.body();
                // Faça algo com a resposta JSON, como analisá-la e extrair os dados necessários.
                System.out.println(responseBody);
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        
        
    }
}
