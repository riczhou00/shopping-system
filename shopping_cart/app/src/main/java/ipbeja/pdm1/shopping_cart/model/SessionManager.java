package ipbeja.pdm1.shopping_cart.model;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String KEY_ID = "ID";
    private static final String KEY_USERNAME = "USERNAME";
    private static final String KEY_PASSWORD = "PASSWORD";
    private static SharedPreferences sharedPreferences;

    private static SharedPreferences getInstance(Context context){
        if (sharedPreferences == null) {
            sharedPreferences = context.getApplicationContext().getSharedPreferences("Pref",0);
        }
        return sharedPreferences;
    }

    public static  User getActiveSession(Context context){
        if (getInstance(context).contains(KEY_USERNAME) && getInstance(context).contains(KEY_PASSWORD)) {
            return new User(getInstance(context).getLong(KEY_ID,0),getInstance(context).getString(KEY_USERNAME,""),getInstance(context).getString(KEY_PASSWORD,""));
        }
        return null;
    }

    public static void saveSession(Context context, User user){
        getInstance(context).edit().putLong(KEY_ID,user.getId()).apply();
        getInstance(context).edit().putString(KEY_USERNAME,user.getUsername()).apply();
        getInstance(context).edit().putString(KEY_PASSWORD,user.getPassword()).apply();
    }

    public static void clearSession(Context context){
        getInstance(context).edit().clear().apply();
    }
}
