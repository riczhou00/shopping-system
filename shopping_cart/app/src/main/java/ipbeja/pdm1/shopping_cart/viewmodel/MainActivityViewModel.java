package ipbeja.pdm1.shopping_cart.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ipbeja.pdm1.shopping_cart.model.Product;
import ipbeja.pdm1.shopping_cart.model.ProductRepository;

public class MainActivityViewModel extends AndroidViewModel {
    private ProductRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ProductRepository(application);
    }

    public LiveData<List<Product>> getProducts() {
        return this.repository.getAllProducts();
    }

    public void updateList() {
        this.repository.updateList();
    }

   
}
