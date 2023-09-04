package whetherAsssistent;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//receive city and country to search longitude and latitude of a city
//this will be used in WeatherApiInfor.java to receive the weather info (temperature, wind, etc)
public class GeoLocation {
	String cityToSearch = ""; // received from sd2_chatbot
	String countryTOSearch = ""; // received from sd2_chatbot
	
	public GeoLocation() {};
	
	public GeoLocation(String cityToSearch, String countryTOSearch) {
		super();
		this.cityToSearch = cityToSearch;
		this.countryTOSearch = countryTOSearch;
	}

	public String getCityToSearch() {
		return cityToSearch;
	}

	public void setCityToSearch(String cityToSearch) {
		this.cityToSearch = cityToSearch;
	}

	public String getCountryTOSearch() {
		return countryTOSearch;
	}

	public void setCountryTOSearch(String countryTOSearch) {
		this.countryTOSearch = countryTOSearch;
	}

	
	String FindLatLog(String countryTOSearch, String cityTOSearch){
		// building API URL
		String apiUrl = "https://geocode.maps.co/search?country=" + countryTOSearch + "&city=" + cityTOSearch;
		// client HTTP
		HttpClient httpClient = HttpClient.newHttpClient();

		// create a request HTTP GET
		HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(apiUrl))
	                .build();
		
		// send a request and receive a JSON response
		try
		{
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() == 200) {
				String responseBody = response.body();
				//do something with JSON
				//System.out.println(responseBody);
				return responseBody;	
			} else {
				System.err.println("Erro na solicitação HTTP. Código de status: " + response.statusCode());
			}

		}catch(Exception e)
			{
				e.printStackTrace();
			}
		return "errro!!";			
	}//end FindLatLog
}
