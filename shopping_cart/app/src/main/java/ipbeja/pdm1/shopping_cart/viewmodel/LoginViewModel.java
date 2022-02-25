package ipbeja.pdm1.shopping_cart.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import ipbeja.pdm1.shopping_cart.model.ProductRepository;
import ipbeja.pdm1.shopping_cart.model.SessionRepository;
import ipbeja.pdm1.shopping_cart.model.User;

public class LoginViewModel extends AndroidViewModel {
    private final SessionRepository sessionRepository;
    private ProductRepository productRepository;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.productRepository = new ProductRepository(application.getApplicationContext());
        this.sessionRepository = new SessionRepository(application.getApplicationContext());
    }

    public User getActiveSession(){
        return sessionRepository.getActiveSession();
    }

    public void saveSession(User user){
        this.sessionRepository.saveSession(user);
    }

    public LiveData<User> getUser(Context context,String username, String password){
        return productRepository.getUser(context,username,password);
    }

    public User isUserExist(String stringUsername, String stringPassword) {
        return productRepository.isUserExist(stringUsername,stringPassword);
    }

    public LiveData<User> createUser(User user) {
        return this.productRepository.createUser(user);
    }
}