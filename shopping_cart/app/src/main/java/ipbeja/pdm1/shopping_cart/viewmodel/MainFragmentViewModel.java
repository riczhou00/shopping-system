package ipbeja.pdm1.shopping_cart.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ipbeja.pdm1.shopping_cart.model.Product;
import ipbeja.pdm1.shopping_cart.model.ProductRepository;
import ipbeja.pdm1.shopping_cart.model.SessionRepository;
import ipbeja.pdm1.shopping_cart.model.User;

public class MainFragmentViewModel extends AndroidViewModel {
    private final SessionRepository sessionRepository;
    private ProductRepository productRepository;

    public MainFragmentViewModel(@NonNull Application application) {
        super(application);
        this.productRepository = new ProductRepository(application.getApplicationContext());
        this.sessionRepository = new SessionRepository(application.getApplicationContext());
    }

    public LiveData<List<Product>> getProducts() {
        return this.productRepository.getAllProducts();
    }


    public User getActiveSession(){
        return sessionRepository.getActiveSession();
    }

    public void updateList() {
        this.productRepository.updateList();
    }

    public void clearSession(){
        this.sessionRepository.clearSession();
    }
}