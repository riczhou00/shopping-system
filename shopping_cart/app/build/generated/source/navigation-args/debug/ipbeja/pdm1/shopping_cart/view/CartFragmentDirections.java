package ipbeja.pdm1.shopping_cart.view;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import ipbeja.pdm1.shopping_cart.R;

public class CartFragmentDirections {
  private CartFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionCartFragmentToMainFragment() {
    return new ActionOnlyNavDirections(R.id.action_cartFragment_to_main_fragment);
  }
}
