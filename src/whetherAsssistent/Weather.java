package whetherAsssistent;


public class Weather {
	private double temp,tempMax,tempMin, feelsLike, humidity,wind;
	private String description;

	public Weather() {
	}
	
	public Weather(double temp, double tempMax, double tempMin, double feelsLike, double humidity, double wind,
			String description) {
		super();
		this.temp = formatTemperature(temp);
		this.tempMax = formatTemperature(tempMax);
		this.tempMin = formatTemperature(tempMin);
		this.feelsLike = formatTemperature(feelsLike);
		this.humidity = formatTemperature(humidity);
		this.wind = formatTemperature(wind);
		this.description = description;
	}

 

    public double getTemp() {
		return temp;
	}




	public void setTemp(double temp) {		
		this.temp = temp;
	}




	public double getTempMax() {
		return tempMax;
	}




	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}




	public double getTempMin() {
		return tempMin;
	}




	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}




	public double getFeelsLike() {
		return feelsLike;
	}




	public void setFeelsLike(double feelsLike) {
		this.feelsLike = feelsLike;
	}




	public double getHumidity() {
		return humidity;
	}




	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}




	public double getWind() {
		return wind;
	}




	public void setWind(double wind) {
		this.wind = wind;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}	
	
	//data received in json its on wrong formact, this function fix it
	public double formatTemperature(double temperature) {		
	    return (int)(temperature / 10.0);
	}



	@Override
    public String toString() {       
        return "Weather in degree: temperature=" + temp + 
        		"\n max temperature="  + tempMax +
        		"\n min temperature=" + tempMin +
        		"\n" + " feels Like=" + feelsLike +
        		"\n humidity=" + humidity +
        		"\n " + " wind=" + wind +
        		"\n description: " + description +" ";
        	   
    }
}

