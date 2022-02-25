package ipbeja.pdm1.shopping_cart.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class CartFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private CartFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private CartFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static CartFragmentArgs fromBundle(@NonNull Bundle bundle) {
    CartFragmentArgs __result = new CartFragmentArgs();
    bundle.setClassLoader(CartFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("userId")) {
      long userId;
      userId = bundle.getLong("userId");
      __result.arguments.put("userId", userId);
    } else {
      throw new IllegalArgumentException("Required argument \"userId\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static CartFragmentArgs fromSavedStateHandle(@NonNull SavedStateHandle savedStateHandle) {
    CartFragmentArgs __result = new CartFragmentArgs();
    if (savedStateHandle.contains("userId")) {
      long userId;
      userId = savedStateHandle.get("userId");
      __result.arguments.put("userId", userId);
    } else {
      throw new IllegalArgumentException("Required argument \"userId\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  public long getUserId() {
    return (long) arguments.get("userId");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("userId")) {
      long userId = (long) arguments.get("userId");
      __result.putLong("userId", userId);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("userId")) {
      long userId = (long) arguments.get("userId");
      __result.set("userId", userId);
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    CartFragmentArgs that = (CartFragmentArgs) object;
    if (arguments.containsKey("userId") != that.arguments.containsKey("userId")) {
      return false;
    }
    if (getUserId() != that.getUserId()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (int)(getUserId() ^ (getUserId() >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "CartFragmentArgs{"
        + "userId=" + getUserId()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull CartFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(long userId) {
      this.arguments.put("userId", userId);
    }

    @NonNull
    public CartFragmentArgs build() {
      CartFragmentArgs result = new CartFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setUserId(long userId) {
      this.arguments.put("userId", userId);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public long getUserId() {
      return (long) arguments.get("userId");
    }
  }
}
