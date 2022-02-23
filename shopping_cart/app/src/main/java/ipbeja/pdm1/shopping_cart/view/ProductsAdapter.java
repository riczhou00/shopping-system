package ipbeja.pdm1.shopping_cart.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ipbeja.pdm1.shopping_cart.R;
import ipbeja.pdm1.shopping_cart.model.Product;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder>{
    private final Context context;
    private List<Product> productList = new ArrayList<>();

    public ProductsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.product_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = this.productList.get(position);
        holder.txtName.setText(product.getName());

        holder.txtIsAvailable.setText(product.getIsAvailable());
        Glide.with(this.context).load(product.getImageURL()).into(holder.imageView);
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDetailsActivity.startActivity(context, product.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.productList.size();
    }

    public void updateList(List<Product> newList) {
        this.productList = newList;
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

            this.txtIsAvailable = this.root.findViewById(R.id.txtIsAvailable);
            this.imageView = this.root.findViewById(R.id.imageView);
        }
    }

}
