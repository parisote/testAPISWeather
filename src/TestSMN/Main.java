package TestSMN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

            URL url = new URL("http://ws.smn.gob.ar/map_items/weather");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
            	Gson g = new Gson();
            	JsonArray a = g.fromJson(output, JsonArray.class);
            	for (JsonElement obj: a) {
					JsonObject jo = obj.getAsJsonObject();
					System.out.println(jo);
					//JsonObject j1 = jo.get("weather").getAsJsonObject();
					//String clima = j1.get("description").getAsString();
					//System.out.println(jo.get("province").toString() + " - " +jo.get("name").toString() + " - " + clima);
				}
            }
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
	}
}
