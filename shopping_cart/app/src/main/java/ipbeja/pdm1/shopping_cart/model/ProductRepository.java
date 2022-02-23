package ipbeja.pdm1.shopping_cart.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import ipbeja.pdm1.shopping_cart.model.database.AppDatabase;
import ipbeja.pdm1.shopping_cart.model.database.ProductDao;
import ipbeja.pdm1.shopping_cart.model.remote.DataSource;
import ipbeja.pdm1.shopping_cart.model.remote.ProductService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {

    private ProductDao productDao;

    public ProductRepository(Context context) {
        this.productDao = AppDatabase.getInstance(context).getProductDao();
    }

    public LiveData<List<Product>> getAllProducts() {
        return this.productDao.getAllProducts();
    }

    public void updateList() {
        ProductService service = DataSource.getProductService();

        service.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> newList = response.body();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            productDao.add(newList);
                        }
                    }).start();
                } else {
                    // Log error to logcat
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public LiveData<Product> getProduct(long productId) {
        this.updateProduct(productId);
        return this.productDao.getProduct(productId);
    }

    private void updateProduct(long productId) {
        DataSource.getProductService().getProductDetails(productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Product product = response.body();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            productDao.update(product);
                        }
                    }).start();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }
}
