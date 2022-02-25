package ipbeja.pdm1.shopping_cart.view;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import ipbeja.pdm1.shopping_cart.R;

public class ProductDetailsFragmentDirections {
  private ProductDetailsFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionProductDetailsFragmentToMainFragment() {
    return new ActionOnlyNavDirections(R.id.action_productDetailsFragment_to_main_fragment);
  }
}
