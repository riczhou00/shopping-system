package ipbeja.pdm1.shopping_cart.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ipbeja.pdm1.shopping_cart.model.Cart;
import ipbeja.pdm1.shopping_cart.model.Product;

@Dao
public interface CartDao {
    @Query("SELECT * FROM Cart")
    List<Cart> getAllCart();

    @Insert
    void add(Cart cart);

    @Query("DELETE FROM Cart WHERE productId = :productId")
    void delete(int productId);

    @Query("DELETE FROM Cart")
    void deleteAll();
}
