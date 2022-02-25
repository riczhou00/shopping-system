package ipbeja.pdm1.shopping_cart.model;

import android.content.Context;

public class SessionRepository {
    private Context context;

    public SessionRepository(Context context){
        this.context = context;
    }
    public User getActiveSession(){
        return SessionManager.getActiveSession(this.context);
    }
    public void saveSession(User user){
        SessionManager.saveSession(this.context,user);
    }

    public void clearSession(){
        SessionManager.clearSession(this.context);
    }
}
