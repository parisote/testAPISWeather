package TestOWM;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import TestOWM.Domain.WeatherResponse;

public class Main {

	//SACO LA KEY POR COMMIT
	final static String API_KEY = "";
	
	//PODRIA HACER UN CLASS PROPERTIES PERO MAS FACIL EL READLINE PARA ESTO
	final static String FILE = "./config/key.properties";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BufferedReader br;
		Client client = ClientBuilder.newClient();
		try {
			br = new BufferedReader(new FileReader(FILE));

			String key; 
			key = br.readLine();
				
			WebTarget target = client.target("http://api.openweathermap.org/data/2.5/weather")
					.queryParam("units", "metric");

			WeatherResponse w = target.queryParam("q", "Buenos Aires")
					.queryParam("appid", key)
					.request(MediaType.APPLICATION_JSON)
					.get(WeatherResponse.class);

			System.out.println(w.getMain().getTemp());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
