package ipbeja.pdm1.shopping_cart.model.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataSource {
    private static final String BASE_URL = "https://my-json-server.typicode.com/riczhou00/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ProductService getProductService() {
        return retrofit.create(ProductService.class);
    }
}
