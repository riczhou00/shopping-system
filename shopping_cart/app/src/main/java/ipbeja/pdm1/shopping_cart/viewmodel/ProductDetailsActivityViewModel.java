package ipbeja.pdm1.shopping_cart.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import ipbeja.pdm1.shopping_cart.model.Product;
import ipbeja.pdm1.shopping_cart.model.ProductRepository;

public class ProductDetailsActivityViewModel extends AndroidViewModel {

    private ProductRepository productRepository;

    public ProductDetailsActivityViewModel(@NonNull Application application) {
        super(application);
        this.productRepository = new ProductRepository(application);
    }

    public LiveData<Product> getProduct(long productId) {
        return this.productRepository.getProduct(productId);
    }
}
