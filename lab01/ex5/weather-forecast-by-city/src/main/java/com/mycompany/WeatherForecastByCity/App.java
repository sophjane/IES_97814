package com.mycompany.WeatherForecastByCity;

import com.mycompany.IpmaApiClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.logging.Logger;
import java.util.Scanner;
import java.util.HashMap;

public class App {
    public static void main( String[] args ) {

        Scanner sc = new Scanner(System.in);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IpmaService service = retrofit.create(IpmaService.class);
        Call<IpmaCityForecast> callSync = service.getCityCodes();
        //Call<IpmaCityForecast> callSync = service.getForecastForACity(city_code);

        /*System.out.println("--- WEATHER FORECAST ---");
        System.out.println("City: ");
        String city = sc.nextLine();*/

        HashMap<String, Integer> cityCodes = new HashMap<>();

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            if (forecast != null) {
                logger.info(forecast.getData().
                        listIterator().next());
            } else {
                logger.info( "No results for this city!");
            }
        } catch (Exception ex) {
            logger.error("Exception occured while trying to get API's response!");
            ex.printStackTrace();
        }

    }
}
