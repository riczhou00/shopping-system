package ipbeja.pdm1.shopping_cart.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ipbeja.pdm1.shopping_cart.model.Product;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM Product")
    LiveData<List<Product>> getAllProducts();

    @Query("SELECT * FROM PRODUCT WHERE id = :idProduct")
    LiveData<Product> getProduct(long idProduct);

    @Update
    void update(List<Product> productList);

    @Update
    void update(Product product);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(List<Product> productList);
}
