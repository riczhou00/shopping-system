package ipbeja.pdm1.shopping_cart.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import ipbeja.pdm1.shopping_cart.R;
import ipbeja.pdm1.shopping_cart.model.Product;
import ipbeja.pdm1.shopping_cart.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private ProductsAdapter adapter;
    private MainActivityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        this.adapter = new ProductsAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.adapter);

        this.viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        this.viewModel.getProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                MainActivity.this.adapter.updateList(products);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.viewModel.updateList();
    }
    }
