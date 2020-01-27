package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public ComboBox<String> comboBox;
    public ComboBox <String> cmbCity;

    List countries;
    List cities;

    public Controller() throws Exception {
       /* Database database = new Database();
        database.getCountries();*/
    }


    public void showCountry() {
        Database database = new Database();
        countries = database.getCountries();
        comboBox.getItems().setAll(countries);
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


    public void getCity(){


        Database database = new Database();
        String city = comboBox.getValue();

        cities = database.getCities(city);

        cmbCity.getItems().setAll(cities);
    }



}
