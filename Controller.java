package sample;

import javafx.event.ActionEvent;
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

    private List countries;
    private List <City> cities;

    private City city;
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
        }

       // cities = database.getCities(city);

       // cmbCity.getItems().setAll();


    }

    public void showInfo(ActionEvent actionEvent) {

  //  musim dat do hodnot lblCity,lblCountry,lblPopulation


        String cityName = (String) cmbCity.getValue();
        System.out.println(cityName);

        for (City c : cities) {
            if (c.getName().equalsIgnoreCase(cityName)) {
                city = c;
            }
        }




        System.out.println("show info" +  city.getName() + "  " + city.getCode2() + "   "  + city.getCode3() );
        lblCity.setText(city.getName());
        lblCountry.setText(comboBox.getValue());
        lblPopulation.setText(String.valueOf(city.getPopulation()));
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
