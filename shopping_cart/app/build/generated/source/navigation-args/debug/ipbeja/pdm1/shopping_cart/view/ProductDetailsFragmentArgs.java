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

public class ProductDetailsFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private ProductDetailsFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private ProductDetailsFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ProductDetailsFragmentArgs fromBundle(@NonNull Bundle bundle) {
    ProductDetailsFragmentArgs __result = new ProductDetailsFragmentArgs();
    bundle.setClassLoader(ProductDetailsFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("id")) {
      long id;
      id = bundle.getLong("id");
      __result.arguments.put("id", id);
    } else {
      throw new IllegalArgumentException("Required argument \"id\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ProductDetailsFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    ProductDetailsFragmentArgs __result = new ProductDetailsFragmentArgs();
    if (savedStateHandle.contains("id")) {
      long id;
      id = savedStateHandle.get("id");
      __result.arguments.put("id", id);
    } else {
      throw new IllegalArgumentException("Required argument \"id\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  public long getId() {
    return (long) arguments.get("id");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("id")) {
      long id = (long) arguments.get("id");
      __result.putLong("id", id);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("id")) {
      long id = (long) arguments.get("id");
      __result.set("id", id);
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
    ProductDetailsFragmentArgs that = (ProductDetailsFragmentArgs) object;
    if (arguments.containsKey("id") != that.arguments.containsKey("id")) {
      return false;
    }
    if (getId() != that.getId()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (int)(getId() ^ (getId() >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "ProductDetailsFragmentArgs{"
        + "id=" + getId()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull ProductDetailsFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(long id) {
      this.arguments.put("id", id);
    }

    @NonNull
    public ProductDetailsFragmentArgs build() {
      ProductDetailsFragmentArgs result = new ProductDetailsFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setId(long id) {
      this.arguments.put("id", id);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public long getId() {
      return (long) arguments.get("id");
    }
  }
}
