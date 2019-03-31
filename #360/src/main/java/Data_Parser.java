import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.*;

/** 
 * TBH I honestly have no idea what I'm doing...I'm still trying to figure things out
 * Aaand my searching is going pretty terribly...
 * this is all really confusing...fek...
 * Buuuut I did say that I'd do as many challenges as possible..soo...yeah...
 * RIP me this will probably take me a bit to figure out
*/
public class Data_Parser {
     public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        int time = 0;
        try {
            URL openSky = new URL("https://opensky-network.org/api/states/all");
            URLConnection con = openSky.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            while((inputLine = in.readLine()) != null) {
                JSONArray array = (JSONArray) parser.parse(inputLine);

                for(Object o : array) {
                    JSONObject obj = (JSONObject) o;

                    time = (Integer) obj.get("time");
                }
                System.out.println(time);
            }

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ParseException e) {
            e.printStackTrace();
        }
    }
}