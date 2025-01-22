import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class E_PracticeHW {

    public static void main(String[] args) throws ParseException {
        E_PracticeHW pokemon = new E_PracticeHW();
    }

    public E_PracticeHW() throws ParseException {
        pull();
    }

    public void pull() throws ParseException {
        String output = "";
        String jsonString = "";
        try {

            URL url = new URL("https://pokeapi.co/api/v2/pokemon/pikachu/"); /** Your API's URL goes here */
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                jsonString += output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // turn your string into a JSON object using a parser
        JSONParser parser = new JSONParser();
        JSONObject pikachu = (JSONObject) parser.parse(jsonString);
        System.out.println("Pikachu's JSON: " + pikachu);


        /* TODO : print out the name of each ability that Pikachu has */

        JSONArray abilities = (JSONArray) pikachu.get("abilities");

        for (int x = 0; x < 2; x = x + 1) {
            JSONObject power = (JSONObject) abilities.get(x);
            JSONObject ability = (JSONObject) power.get("ability");// ability is the key to access power// power represents all the information in static
            String name = (String) ability.get("name"); // this is used to access the name of the ability
            System.out.println(name);// this prints out the name of the ability
        }

//        JSONArray power= (JSONArray) name.get("abilities");
//
//        System.out.println("name of"+ name.get("abilities") + ";" + power);


//        JSONArray ability  = (JSONArray) name.get ("ability");
//        System.out.println("name of"+ name.get("ability") + ";" + ability);


            // feel free to paste this URL into your browser to explore the JSON: https://pokeapi.co/api/v2/pokemon/pikachu/


    }
}

