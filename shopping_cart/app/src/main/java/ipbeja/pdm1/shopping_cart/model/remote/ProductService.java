package ipbeja.pdm1.shopping_cart.model.remote;

import java.util.List;

import ipbeja.pdm1.shopping_cart.model.Product;
import ipbeja.pdm1.shopping_cart.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductService {
    @GET("shopping-system/Products/")
    Call<List<Product>> getProducts();

    @GET("shopping-system/Product/{id}")
    Call<Product> getProductDetails(@Path("id") long id);

    @GET("shopping-system/Users/")
    Call<List<User>> getUsers(@Query(value = "username",encoded = true) String username,@Query(value = "password",encoded = true) String password);

    @GET("shopping-system/Users/")
    Call<List<User>> getUserByUsername(@Query(value = "username",encoded = true) String username);

    @POST("shopping-system/Users/")
    Call<User> newUser(@Body User user);
}
