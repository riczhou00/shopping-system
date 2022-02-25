package ipbeja.pdm1.shopping_cart.model;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ipbeja.pdm1.shopping_cart.R;
import ipbeja.pdm1.shopping_cart.model.database.AppDatabase;
import ipbeja.pdm1.shopping_cart.model.database.ProductDao;
import ipbeja.pdm1.shopping_cart.model.remote.DataSource;
import ipbeja.pdm1.shopping_cart.model.remote.ProductService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {

    private ProductDao productDao;
    private User user;

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

    public LiveData<User> getUser(Context context, String username, String password) {
        MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
        ProductService service = DataSource.getProductService();
        service.getUsers(username,password).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        userMutableLiveData.postValue(response.body().get(0));
                    }else {
                        Toast toast = Toast.makeText(context, R.string.ERROR,Toast.LENGTH_SHORT);
                        toast.show();
                        userMutableLiveData.postValue(null);
                    }
                }else {
                    userMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
                userMutableLiveData.postValue(null);
            }
        });

        return userMutableLiveData;
    }

    public User isUserExist(String stringUsername, String stringPassword) {
        ProductService service = DataSource.getProductService();
        service.getUserByUsername(stringUsername).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    if (response.body().size() > 0) {
                        user = null;
                    }else {
                        User thisUser = User.newUser(stringUsername,stringPassword);
                        user = thisUser;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                user = null;
            }
        });

        return user;
    }

    public LiveData<User> createUser(User user) {
        MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
        ProductService service = DataSource.getProductService();
        service.newUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    userMutableLiveData.postValue(response.body());
                }else {
                    userMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                userMutableLiveData.postValue(null);
            }
        });

        return userMutableLiveData;
    }
}
