package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlacePojo;
import pojo.setLocation;

public class TestDataBuild {

	public AddPlacePojo addPlacePojo(double lat, double log, int accuracy, String name, String phonenumber,
			String address, String types0, String types1, String website, String language) {
		AddPlacePojo p = new AddPlacePojo();
		setLocation l = new setLocation();
		l.setLat(lat);
		l.setLng(log);
		p.setLocation(l);
		p.setAccuracy(accuracy);
		p.setName(name);
		p.setPhone_number(phonenumber);
		p.setAddress(address);
		List<String> types = new ArrayList<String>();
		types.add(types0);
		types.add(types1);
		p.setTypes(types);
		p.setWebsite(website);
		p.setLanguage(language);
		return p;
	}

	public String deletePayload(String place_id) {

		return "{\r\n" + "    \"place_id\":\"" + place_id + "\"   	 \r\n" + "}\r\n" + "".toString();

	}
	
	public static String updatePlaceJson(String placeId, String newAddress) {
		return "{\r\n" + "\"place_id\":\"" + placeId + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n" + "}";
	}
}
