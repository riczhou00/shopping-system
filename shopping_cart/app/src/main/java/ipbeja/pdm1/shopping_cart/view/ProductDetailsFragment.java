package ipbeja.pdm1.shopping_cart.view;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ipbeja.pdm1.shopping_cart.model.Cart;
import ipbeja.pdm1.shopping_cart.model.Product;
import ipbeja.pdm1.shopping_cart.model.User;
import ipbeja.pdm1.shopping_cart.model.database.AppDatabase;
import ipbeja.pdm1.shopping_cart.viewmodel.ProductDetailsViewModel;
import ipbeja.pdm1.shopping_cart.R;

public class ProductDetailsFragment extends Fragment {

    private ProductDetailsViewModel mViewModel;
    private TextView textViewName;
    private ImageView imageView;
    private TextView textViewDescription;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.product_details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(ProductDetailsViewModel.class);
        long id = ProductDetailsFragmentArgs.fromBundle(getArguments()).getId();

        this.textViewName = view.findViewById(R.id.textViewName);
        this.imageView = view.findViewById(R.id.imageView);
        this.textViewDescription =view.findViewById(R.id.textViewDescription);
        Button button = view.findViewById(R.id.btnAddToCart);


        this.mViewModel.getProduct(id).observe(getViewLifecycleOwner(), new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
               ProductDetailsFragment.this.textViewName.setText(product.getName());
               Glide.with(ProductDetailsFragment.this).load(product.getImageURL()).into(ProductDetailsFragment.this.imageView);
               ProductDetailsFragment.this.textViewDescription.setText(product.getDescription());

               button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cart cart = new Cart(0,(int)mViewModel.getActiveSession().getId(),(int)product.getId());
                        AppDatabase.getInstance(ProductDetailsFragment.this.getContext()).getCartDao().add(cart);
                        Toast toast = Toast.makeText(ProductDetailsFragment.this.getContext(), R.string.PRODUCT_ADD , Toast.LENGTH_SHORT);
                        toast.show();
                        NavDirections action = ProductDetailsFragmentDirections.actionProductDetailsFragmentToMainFragment();
                        Navigation.findNavController(ProductDetailsFragment.this.getView()).navigate(action);
                    }
               });
            }
        });


    }
}