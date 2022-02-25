package ipbeja.pdm1.shopping_cart.view;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import ipbeja.pdm1.shopping_cart.R;

public class LoginFragmentDirections {
  private LoginFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionLoginFragmentToMainFragment() {
    return new ActionOnlyNavDirections(R.id.action_loginFragment_to_main_fragment);
  }
}
