package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public ComboBox<String> comboBox;
    public ComboBox <String> cmbCity;
    public javafx.scene.control.Button Button;
    public Label lblCity;
    public Label lblCountry;
    public Label lblPopulation;
    public Label lblTemperature;
    public Label lblLong;
    public Label lblLat;
    public CheckBox checkDetails;

    private List countries;
    private List <City> cities;

   // private String cityName = "";

    private Weather weather;
    //private Object Weather;

    //  private List cities;


    public Controller() throws Exception {
       /* Database database = new Database();
        database.getCountries();*/
    }


    public void showCountry() {
        Database database = new Database();
        countries = database.getCountries();
        comboBox.getItems().setAll(countries);
    }




    public void getCity(){


        String city = comboBox.getValue();

        System.out.println("You select " + city);

      //  List<City> cities = new ArrayList<>();

        Database database = new Database();
        cities = database.getCities(city);

        cmbCity.getItems().clear();

        for (City s : cities) {
            System.out.println(s.getName());
            cmbCity.getItems().add(s.getName());


            System.out.println("Hello world");


/*

            if (s.getName().equalsIgnoreCase(cmbCity.getValue())) {
                System.out.println("=========");
                System.out.println(" show me the data now pleas " +"\n" + s.getName() + " " + s.getCode2());
                System.out.println("=========");
            }
*/


        }


    }

    public void showInfo(ActionEvent actionEvent) {

  //  musim dat do hodnot lblCity,lblCountry,lblPopulation


        String cityName = (String) cmbCity.getValue();


        City city = getSelectedCity(cityName);

        city.getName();
        System.out.println("show info " + city.getName() + "  " + city.getCode2());
        lblCity.setText("City " + city.getName());
        lblCountry.setText("Country "  + comboBox.getValue());
        lblPopulation.setText("Population "  + city.getPopulation());


        //for( int  i = 1 ; i < cities.lengths; i ++ )   pozri minuly iny projekt
        /*for (City c : cities) {

            if (c.getName().equalsIgnoreCase(cityName)) {
                System.out.println("show info  " +  c.getName() + "  " + c.getCode2() + "   "  + c.getCode3() );
                lblCity.setText("City " + c.getName());
                lblCountry.setText("Country "  + comboBox.getValue());
                lblPopulation.setText("Population "  + String.valueOf(c.getPopulation()));


                weather =  new WebWeather().getData(cityName,c.getCode2());

                System.out.println("Your temperature is " + weather.getTemp());


            }
        }*/

    }

    private City getSelectedCity(String cityName) { // function return the object city which equal to from label
        City city = null;

        for (City c : cities) {
            if (c.getName().equalsIgnoreCase(cityName)) {
                city =c;
            }
        }

        return city;
    }

    public void showDetails(ActionEvent actionEvent) {
        //lblLong.setVisible(false);
        String cityName = (String) cmbCity.getValue();





        if (checkDetails.isSelected()) {
            City city = getSelectedCity(cityName);


            weather = new WebWeather().getData(city.getName(), city.getCode2());
            lblTemperature.setText("Temperature" + weather.getTemp());
            lblLong.setText("Long " + weather.getLon());
            lblLat.setText("Lat " + weather.getLst());


            /* for (City c : cities) { *//* for( int  i = 1 ; i < cities.lengths; i ++ )   pozri minuly iny projekt  *//*


                if (c.getName().equalsIgnoreCase(cityName)) {
                    System.out.println("show me the information okey " + c.getName() + "  " + c.getCode2() + "   " + c.getCode3());


                    weather = new WebWeather().getData(cityName, c.getCode2());
                    lblTemperature.setText("Temperature" + weather.getTemp());
                    lblLong.setText("Long " + weather.getLon());
                    lblLat.setText("Lat " + weather.getLst());


                }
            }*/

/*notes you have to change lbl Temperature ,, change the opacity after this add in this function
* and will be better if you dont repeat some code and create function*/


            lblLong.setOpacity(1);
            lblLat.setOpacity(1);
        } else {
            lblLat.setOpacity(0);
            lblLong.setOpacity(0);

        }


    }



    public void showData(ActionEvent actionEvent) {
        String countries = "SELECT country.name FROM country limit 1";
        PreparedStatement stmt = null;
        try {
            stmt = Database.getConnection().prepareStatement(countries);
            ResultSet rs = stmt.executeQuery();
            String country;
            List<String> list = new ArrayList<>();
            while (rs.next()) {
                country = rs.getString("Name");
                System.out.println(country);
                list.add(rs.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
/* ked zaskrtnem detail  tak sa mi zobrazia dalsie info */