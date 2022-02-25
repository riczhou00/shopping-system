package ipbeja.pdm1.shopping_cart.view;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ipbeja.pdm1.shopping_cart.R;
import ipbeja.pdm1.shopping_cart.model.Cart;
import ipbeja.pdm1.shopping_cart.model.Product;
import ipbeja.pdm1.shopping_cart.model.ProductRepository;
import ipbeja.pdm1.shopping_cart.model.database.AppDatabase;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private final Context context;
    private List<Cart> cartList = new ArrayList<>();
    private LifecycleOwner getViewLifecycleOwner;
    private ProductRepository productRepository;


    public CartAdapter(Context context, @NonNull Application application, LifecycleOwner getViewLifecycleOwner) {
        this.context = context;
        this.getViewLifecycleOwner = getViewLifecycleOwner;
        this.productRepository = new ProductRepository(application.getApplicationContext());
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.product_list_item, parent, false);
        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart cart = this.cartList.get(position);
        productRepository.getProduct(cart.getProductId()).observe(getViewLifecycleOwner, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                holder.txtName.setText(product.getName());
                holder.txtPrice.setText( product.getPrice()+"€");
                holder.txtIsAvailable.setText(product.getIsAvailable());
                Glide.with(CartAdapter.this.context).load(product.getImageURL()).into(holder.imageView);
                holder.root.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CartAdapter.this.context);
                        builder.setTitle("Eliminar Pedido");
                        builder.setMessage("Tem a certeza que pretende eliminar este pedido " + product.getName() + "?");
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AppDatabase.getInstance(context).getCartDao().delete(cart.getProductId());
                                cartList.remove(cart);
                                notifyDataSetChanged();
                                dialogInterface.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        return true;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.cartList.size();
    }
    public void updateList(List<Cart> newList) {
        this.cartList = newList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View root;
        TextView txtName;
        TextView txtPrice;
        TextView txtIsAvailable;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.root = itemView;
            this.txtName = this.root.findViewById(R.id.txtName);
            this.txtPrice = this.root.findViewById(R.id.txtPrice);
            this.txtIsAvailable = this.root.findViewById(R.id.txtIsAvailable);
            this.imageView = this.root.findViewById(R.id.imageView);
        }
    }
}
