package ipbeja.pdm1.shopping_cart.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import ipbeja.pdm1.shopping_cart.R;
import ipbeja.pdm1.shopping_cart.model.Product;
import ipbeja.pdm1.shopping_cart.viewmodel.ProductDetailsActivityViewModel;

public class ProductDetailsActivity extends AppCompatActivity {

    private static final String KEY_ID = "id";

    public static void startActivity(Context context, long id) {
        Intent intent = new Intent(context, ProductDetailsActivity.class);
        intent.putExtra(KEY_ID, id);
        context.startActivity(intent);
    }


    private TextView textViewName;
    private ImageView imageView;
    private TextView textViewDescription;

    private ProductDetailsActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        this.textViewName = findViewById(R.id.textViewName);
        this.imageView = findViewById(R.id.imageView);
        this.textViewDescription = findViewById(R.id.textViewDescription);

        this.viewModel = new ViewModelProvider(this).get(ProductDetailsActivityViewModel.class);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(KEY_ID)) {
            long id = getIntent().getExtras().getLong(KEY_ID);
            this.viewModel.getProduct(id).observe(this, new Observer<Product>() {
                @Override
                public void onChanged(Product product) {
                    updateUI(product);
                }
            });
        }
    }

    private void updateUI(Product product) {
        this.textViewName.setText(product.getName());
        Glide.with(this).load(product.getImageURL()).into(this.imageView);
        this.textViewDescription.setText(product.getDescription());
    }

}
