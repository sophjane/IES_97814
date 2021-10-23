package com.mycompany.IpmaApiClient;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.logging.Logger;
import java.util.HashMap;
import java.util.List;

public class APIHandler {

    private static final Logger logger = Logger.getLogger(APIHandler.class.getName());

    public static String getCityWeatherForecast(String cityName) {

        String cityWeatherForecast = "";

        HashMap<String, Integer> cityCodes = new HashMap<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IpmaService service = retrofit.create(IpmaService.class);
        Call<IpmaCityForecast> callSync1 = service.getCityCodes();

        try {
            Response<IpmaCityForecast> apiResponse1 = callSync1.execute();
            IpmaCityForecast citiesInfo = apiResponse1.body();

            if (citiesInfo != null) {
                List<CityForecast> citiesList = citiesInfo.getData();

                for(CityForecast c : citiesList) {
                    cityCodes.put(c.getLocal(), c.getGlobalIdLocal());
                }

                if(cityCodes.containsKey(cityName)) {
                    Call<IpmaCityForecast> callSync2 = service.getForecastForACity(cityCodes.get(cityName));

                    try {
                        Response<IpmaCityForecast> apiResponse2 = callSync2.execute();
                        IpmaCityForecast forecast = apiResponse2.body();

                        if(forecast != null) {
                            cityWeatherForecast = "Date: " + forecast.getData().listIterator().next().getForecastDate()
                                    + "\nMax. Temperature: " + forecast.getData().listIterator().next().getTMax()
                                    + "\nMin. Temperature: " + forecast.getData().listIterator().next().getTMin()
                                    + "\nPrecipitation Prob.: " + forecast.getData().listIterator().next().getPrecipitaProb()
                                    + "\nWind Direction: " + forecast.getData().listIterator().next().getPredWindDir();
                        } else {
                            logger.info("No city forecast!");
                        }
                    } catch(Exception ex) {
                        ex.printStackTrace();
                        logger.info("Exception occurred while accessing API with city forecast!");
                    }

                } else {
                    logger.info("City not found!");
                    System.exit(-1);
                }

            } else {
                logger.info( "No information of cities!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Exception occurred while accessing API with city information!");
        }

        return cityWeatherForecast;
    }
}