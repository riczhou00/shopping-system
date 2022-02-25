package ipbeja.pdm1.shopping_cart.view;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.List;

import ipbeja.pdm1.shopping_cart.R;
import ipbeja.pdm1.shopping_cart.model.Product;
import ipbeja.pdm1.shopping_cart.model.User;
import ipbeja.pdm1.shopping_cart.viewmodel.MainFragmentViewModel;

public class MainFragment extends Fragment {

    private ProductsAdapter adapter;
    private MainFragmentViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainFragmentViewModel.class);

        ImageButton imageButton = view.findViewById(R.id.imageButton);
        Button btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.clearSession();
                NavDirections action = MainFragmentDirections.actionMainFragmentToLoginFragment();
                Navigation.findNavController(MainFragment.this.getView()).navigate(action);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = MainFragmentDirections.actionMainFragmentToCartFragment(viewModel.getActiveSession().getId());
                Navigation.findNavController(MainFragment.this.getView()).navigate(action);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        this.adapter = new ProductsAdapter(this.getContext(), new ProductsAdapter.OnProductsClickedListener() {
            @Override
            public void onProductsClicked(long id) {
                NavDirections action = MainFragmentDirections.actionMainFragmentToProductDetailsFragment(id);
                Navigation.findNavController(MainFragment.this.getView()).navigate(action);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(this.adapter);

        this.viewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                MainFragment.this.adapter.updateList(products);
            }
        });
        this.viewModel.updateList();
    }
    }
