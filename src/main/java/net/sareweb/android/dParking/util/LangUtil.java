package net.sareweb.android.dParking.util;

import java.util.Locale;

import android.content.Context;
import android.content.res.Configuration;

public class LangUtil {
	
	public static void updateLanguage(Context context, String language) {
		Locale locale = new Locale(language);
		updateLanguage(context,locale);
	}

	public static void updateLanguage(Context context, Locale locale) {
		Configuration config = new Configuration();
		config.locale = locale;
		context.getResources().updateConfiguration(config, null);
	}

}
