package ipbeja.pdm1.shopping_cart.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ipbeja.pdm1.shopping_cart.model.Cart;
import ipbeja.pdm1.shopping_cart.model.Product;

@Database(entities = {Product.class, Cart.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProductDao getProductDao();
    public abstract CartDao getCartDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "productsDB").allowMainThreadQueries().
                    build();
        }
            return INSTANCE;
        }
}
