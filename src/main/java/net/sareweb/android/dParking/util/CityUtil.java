package net.sareweb.android.dParking.util;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import net.sareweb.android.dParking.model.City;
import net.sareweb.android.dParking.model.Parking;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class CityUtil {

	public static City initCity(String idioma) {

		String serviceURL = composeServiceURL(idioma);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(serviceURL);
		try {
			HttpResponse response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(response.getEntity());

				List<Parking> parkings = getParkingsFromJson(result);
				return new City(DParkingConstants.DPARKING_CITY_NAME, parkings);
			} else {
				Log.e(TAG, "Not successful getting data");
				return null;
			}
		} catch (Exception e) {
			Log.e(TAG, "Error requesting data", e);
			return null;
		}
	}

	private static String composeServiceURL(String idioma) {
		return "http://" + DParkingConstants.SERVER + DParkingConstants.SERVICE + "&"
				+ DParkingConstants.PARAM_IDIOMA + "=" + idioma;
	}
	
	private static List<Parking> getParkingsFromJson(String json){
		GsonBuilder gsonBuilder = new GsonBuilder();

		Type collectionType = new TypeToken<Collection<Parking>>() {
		}.getType();
		return gsonBuilder.create().fromJson(json,
				collectionType);
	}

	private static String TAG = "CityUtils";

}
