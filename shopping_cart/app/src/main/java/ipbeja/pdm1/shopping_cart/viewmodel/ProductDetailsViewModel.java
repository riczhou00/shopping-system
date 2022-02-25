package ipbeja.pdm1.shopping_cart.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import ipbeja.pdm1.shopping_cart.model.Product;
import ipbeja.pdm1.shopping_cart.model.ProductRepository;
import ipbeja.pdm1.shopping_cart.model.SessionRepository;
import ipbeja.pdm1.shopping_cart.model.User;

public class ProductDetailsViewModel extends AndroidViewModel {

    private final SessionRepository sessionRepository;
    private ProductRepository productRepository;

    public ProductDetailsViewModel(@NonNull Application application) {
        super(application);
        this.productRepository = new ProductRepository(application.getApplicationContext());
        this.sessionRepository = new SessionRepository(application.getApplicationContext());
    }

    public LiveData<Product> getProduct(long productId) {
        return this.productRepository.getProduct(productId);
    }

    public User getActiveSession(){
        return sessionRepository.getActiveSession();
    }
}