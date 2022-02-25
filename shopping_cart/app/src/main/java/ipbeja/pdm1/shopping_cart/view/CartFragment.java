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
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import ipbeja.pdm1.shopping_cart.model.Cart;
import ipbeja.pdm1.shopping_cart.model.Product;
import ipbeja.pdm1.shopping_cart.model.database.AppDatabase;
import ipbeja.pdm1.shopping_cart.viewmodel.CartViewModel;
import ipbeja.pdm1.shopping_cart.R;

public class CartFragment extends Fragment {

    private CartAdapter adapter;
    private CartViewModel mViewModel;
    private float conta = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cart_fragment, container, false);
    }

    @Override
    public void onViewCreated(@Nullable View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        long id = CartFragmentArgs.fromBundle(getArguments()).getUserId();

        TextView price = view.findViewById(R.id.textViewPrice);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        this.adapter = new CartAdapter(this.getContext(), requireActivity().getApplication(),getViewLifecycleOwner());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(this.adapter);

        List<Cart> cartList = AppDatabase.getInstance(CartFragment.this.getContext()).getCartDao().getAllCart();

        for (int i = 0; i < cartList.size(); i++) {
            Product product = AppDatabase.getInstance(this.getContext()).getProductDao().getProductP(cartList.get(i).getProductId());
            conta += product.getPrice();
            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.setMaximumFractionDigits(2);
            price.setText(decimalFormat.format(conta) + "€");
        }
        this.adapter.updateList(cartList);

        Button finishPayment = view.findViewById(R.id.btnPayment);
        finishPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase.getInstance(CartFragment.this.getContext()).getCartDao().deleteAll();
                Toast toast = Toast.makeText(CartFragment.this.getContext(), R.string.PAYMENT_FINISH , Toast.LENGTH_SHORT);
                toast.show();
                NavDirections action = CartFragmentDirections.actionCartFragmentToMainFragment();
                Navigation.findNavController(CartFragment.this.getView()).navigate(action);
            }
        });
    }

}

