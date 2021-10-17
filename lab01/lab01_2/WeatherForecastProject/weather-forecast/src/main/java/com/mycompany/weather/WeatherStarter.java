package com.mycompany.weather;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import java.util.logging.Logger;

/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class WeatherStarter {

    //private static final int CITY_ID_AVEIRO = 1010500;
    /*
    loggers provide a better alternative to System.out.println
    https://rules.sonarsource.com/java/tag/bad-practice/RSPEC-106
     */
    private static final Logger logger = Logger.getLogger(WeatherStarter.class.getName());

    public static void  main(String[] args ) {

        int cityCode = 0;
        try {
            cityCode = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("ERROR: Not a city code!");
        }
        /*
        get a retrofit instance, loaded with the GSon lib to convert JSON into objects
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IpmaService service = retrofit.create(IpmaService.class);
        Call<IpmaCityForecast> callSync = service.getForecastForACity(cityCode);

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            if (forecast != null) {
                System.out.println("COUNTRY: " + forecast.getCountry() + " " + forecast.getData().
                        listIterator().next().getLatitude() + "º lat, " + forecast.getData().
                        listIterator().next().getLongitude() + "º long");
                System.out.println("FORECAST DATE: " + forecast.getData().
                        listIterator().next().getForecastDate());
                logger.info( "Max temperature for today: " + forecast.getData().
                        listIterator().next().getTMax() + "ºC");
                // For a more user-friendly result, I used System.out.print() instead of logger.info()
                System.out.println( "Min temperature for today: " + forecast.getData().
                        listIterator().next().getTMin() + "ºC");
                System.out.println("Precipitation prob for today: " + forecast.getData().
                        listIterator().next().getPrecipitaProb());
                System.out.println("Prediction of wind direction: " + forecast.getData().
                        listIterator().next().getPredWindDir());
                System.out.println("Class wind speed: " + forecast.getData().
                        listIterator().next().getClassWindSpeed());


            } else {
                logger.info( "No results!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
