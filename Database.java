package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final String JDBC = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://itsovy.sk:3306/world_x?autoReconnect=true&useSSL=false";
    //  private static final String URL = "jdbc:mysql://itsovy.sk:3306/chat1n?autoReconnect=true&useSSL=false";
    // jdbc:mysql://localhost:3306/world_x?autoReconnect=true&useSSL=false
    private static Connection connection;


    public static Connection getConnection() throws Exception, SQLException {
        Class.forName(JDBC);
        connection = DriverManager.getConnection(URL, "student", "kosice2019");
        return connection;
    }

    public List getListOfCountries() {
        try {
            Connection con = getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("select  * from country limit 10 ");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(1);
                String a = resultSet.getString(2);
                String b = resultSet.getString(3);
                String c = resultSet.getString(4);
                // String d = resultSet.getString(5);
                System.out.println(name  + "  " + a  + "  "+ b + " " + "  " +  c +"  " );

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



    /*public List getCountries() throws Exception {
        String countries="SELECT country.name FROM country limit 1";
        PreparedStatement stmt=getConnection().prepareStatement(countries);
        ResultSet rs=stmt.executeQuery();
        String country;
        List<String> list=new ArrayList<>();
        while (rs.next()){
            country=rs.getString("Name");
            System.out.println(country);
            list.add(rs.getString("Name"));
        }

        return null;

    }*/

    public List getCountries() {
        try {
            Connection connection = getConnection(); // create connection
            List<String> country = new ArrayList<>();

            String select = "Select name from country";

            PreparedStatement statement = connection.prepareStatement(select);

            ResultSet resultSet = statement.executeQuery();

            /**/

            while (resultSet.next()) {
                country.add(resultSet.getString(1));
            }


            return country;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public List getCities(String country) {

        try {
            Connection connection = getConnection();
            List<City> cities = new ArrayList<>();

/*
            String select = "select city.name,city.CountryCode, json_extract(Info, '$.Population') AS Info, country.Code2,  " +
                    "from country " +
                    "inner join city on country.code = city.CountryCode " +
                    "where country.name like ? ";
*/


            String select_city =  "SELECT city.name, city.CountryCode, country.Code2, json_extract(Info, '$.Population') AS Info " +
                    "FROM country JOIN city ON country.code = city.countrycode where country.name like ? order by city.name ASC";
            //String select = "select city.name from country inner join city on country.code = city.countrycode where country.name like ? ";



            PreparedStatement statement = connection.prepareStatement(select_city);
            statement.setString(1, country);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String city_name = resultSet.getString("city.Name");
                String code3 = resultSet.getString("city.CountryCode");
                String code2 = resultSet.getString("country.Code2");
                int population = resultSet.getInt("Info");


                System.out.println("vypis    "+city_name + "   " + code2 + " " + code3 + " " + population);

                City newCity = new City(city_name,population,code2,code3);

                cities.add(newCity); // add to arraylist new value


            }

            System.out.println(cities);
            return cities;

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }


}
