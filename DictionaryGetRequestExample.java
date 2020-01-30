package sample;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/*
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
*/

public class DictionaryGetRequestExample {

/*
    public static Map<String, Object> jsonToMap(String string) {
        Map<String, Object> map = new Gson().fromJson(
                str, new TypeToken<HashMap<String, Object>>() {}.getType()
        );

        return map;
    }
*/


    public static void main(String[] args)
            throws ParserConfigurationException, SAXException,
            IOException, XPathExpressionException {

        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=Kosice,sk&units=metric&appid=cf1af4b3cf65717722c6c9d37cee1441";

        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();

            System.out.println(result);


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}