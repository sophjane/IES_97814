package com.mycompany.WeatherForecastByCity;

import com.mycompany.IpmaApiClient.APIHandler;

public class App {
    public static void main( String[] args ) {

        String cityName = "";
        try {
            cityName = String.join(" ", args);
            System.out.println("City: " + cityName);
            System.out.println(APIHandler.getCityWeatherForecast(cityName));
        } catch (Exception e) {
            System.out.println("No Arguments Found");
        }
    }
}
