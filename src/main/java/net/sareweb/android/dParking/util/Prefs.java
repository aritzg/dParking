package net.sareweb.android.dParking.util;

import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref;
import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref.Scope;

@SharedPref(value = Scope.UNIQUE)
public interface Prefs {
	long counterTimeStamp();
}
