package ipbeja.pdm1.shopping_cart.model.remote;

import java.util.List;

import ipbeja.pdm1.shopping_cart.model.Product;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductService {
    @GET("shopping-system/Products/")
    Call<List<Product>> getProducts();

    @GET("shopping-system/ProductDetails/{id}")
    Call<Product> getProductDetails(@Path("id") long id);
}
