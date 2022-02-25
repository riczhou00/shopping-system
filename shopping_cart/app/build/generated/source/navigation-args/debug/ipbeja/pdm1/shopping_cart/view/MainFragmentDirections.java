package ipbeja.pdm1.shopping_cart.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import ipbeja.pdm1.shopping_cart.R;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class MainFragmentDirections {
  private MainFragmentDirections() {
  }

  @NonNull
  public static ActionMainFragmentToProductDetailsFragment actionMainFragmentToProductDetailsFragment(
      long id) {
    return new ActionMainFragmentToProductDetailsFragment(id);
  }

  @NonNull
  public static ActionMainFragmentToCartFragment actionMainFragmentToCartFragment(long userId) {
    return new ActionMainFragmentToCartFragment(userId);
  }

  @NonNull
  public static NavDirections actionMainFragmentToLoginFragment() {
    return new ActionOnlyNavDirections(R.id.action_main_fragment_to_loginFragment);
  }

  public static class ActionMainFragmentToProductDetailsFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionMainFragmentToProductDetailsFragment(long id) {
      this.arguments.put("id", id);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionMainFragmentToProductDetailsFragment setId(long id) {
      this.arguments.put("id", id);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("id")) {
        long id = (long) arguments.get("id");
        __result.putLong("id", id);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_main_fragment_to_productDetailsFragment;
    }

    @SuppressWarnings("unchecked")
    public long getId() {
      return (long) arguments.get("id");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionMainFragmentToProductDetailsFragment that = (ActionMainFragmentToProductDetailsFragment) object;
      if (arguments.containsKey("id") != that.arguments.containsKey("id")) {
        return false;
      }
      if (getId() != that.getId()) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (int)(getId() ^ (getId() >>> 32));
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionMainFragmentToProductDetailsFragment(actionId=" + getActionId() + "){"
          + "id=" + getId()
          + "}";
    }
  }

  public static class ActionMainFragmentToCartFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionMainFragmentToCartFragment(long userId) {
      this.arguments.put("userId", userId);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionMainFragmentToCartFragment setUserId(long userId) {
      this.arguments.put("userId", userId);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("userId")) {
        long userId = (long) arguments.get("userId");
        __result.putLong("userId", userId);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_main_fragment_to_cartFragment;
    }

    @SuppressWarnings("unchecked")
    public long getUserId() {
      return (long) arguments.get("userId");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionMainFragmentToCartFragment that = (ActionMainFragmentToCartFragment) object;
      if (arguments.containsKey("userId") != that.arguments.containsKey("userId")) {
        return false;
      }
      if (getUserId() != that.getUserId()) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (int)(getUserId() ^ (getUserId() >>> 32));
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionMainFragmentToCartFragment(actionId=" + getActionId() + "){"
          + "userId=" + getUserId()
          + "}";
    }
  }
}
