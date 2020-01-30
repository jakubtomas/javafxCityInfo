package sample;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class WebWeather {


    private  String name ;
    private String country;
    private double temp;
    private long humidity;
    private double lon = 0;
    private double lat = 0;
    private double lst = 0;
    //private Object Weather;

    public Weather getData(String city, String code2){
       // WebWeather  weather = null;


        try{

            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+city+","+code2+"&units=metric&appid=cf1af4b3cf65717722c6c9d37cee1441");


            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if(conn.getResponseCode() == 200){
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String output = br.readLine(); // vysledok dat
                //System.out.println(output);

                JSONObject jsonObject = new JSONObject(output);

                name = jsonObject.getString("name");
                country = jsonObject.getJSONObject("sys").getString("country");

                temp = jsonObject.getJSONObject("main").getDouble("temp");
                humidity = jsonObject.getJSONObject("main").getInt("humidity");



                lon = jsonObject.getJSONObject("coord").getDouble("lon");
                 lat = jsonObject.getJSONObject("coord").getDouble("lat");

                System.out.println("Name " + name + " country " + country + "\n" +
                        "temp " + humidity + " lon " +"\n" + lon + "  lat " + lat);

               return  new Weather(name, country, temp, humidity, lon, lat);


            }

            conn.disconnect();
        } catch (IOException e ){
            e.printStackTrace();
        }
        return null;
    }
}
/* vychod zapad slnka
*
*  defoltne nic nevydno
*   */