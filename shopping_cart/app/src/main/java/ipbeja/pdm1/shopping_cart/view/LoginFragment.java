package ipbeja.pdm1.shopping_cart.view;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ipbeja.pdm1.shopping_cart.model.User;
import ipbeja.pdm1.shopping_cart.viewmodel.LoginViewModel;
import ipbeja.pdm1.shopping_cart.R;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        User user = mViewModel.getActiveSession();
        if (user != null) {
            NavDirections action = LoginFragmentDirections.actionLoginFragmentToMainFragment();
            NavHostFragment.findNavController(LoginFragment.this).navigate(action);
        }

        EditText username = view.findViewById(R.id.editTextUsername);
        EditText password = view.findViewById(R.id.editTextPassword);
        Button login = view.findViewById(R.id.btnLogin);
        Button register = view.findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringUsername = username.getText().toString();
                String stringPassword = password.getText().toString();

                if(!stringUsername.isEmpty() && !stringPassword.isEmpty()){
                    User user = mViewModel.isUserExist(stringUsername,stringPassword);
                    if (user == null) {
                        User finalUser = User.newUser(stringUsername,stringPassword);
                        mViewModel.createUser(finalUser).observe(getViewLifecycleOwner(), new Observer<User>() {
                            @Override
                            public void onChanged(User user) {
                                if(user != null){
                                    mViewModel.saveSession(user);
                                    Toast toast = Toast.makeText(LoginFragment.this.getContext(), R.string.REGISTER , Toast.LENGTH_SHORT);
                                    toast.show();
                                    NavDirections action = LoginFragmentDirections.actionLoginFragmentToMainFragment();
                                    NavHostFragment.findNavController(LoginFragment.this).navigate(action);
                                }
                            }
                        });
                    } else{
                        Toast toast = Toast.makeText(LoginFragment.this.getContext(), R.string.EXISTUSERNAME , Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    Toast toast = Toast.makeText(LoginFragment.this.getContext(), R.string.EMPTY , Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.getUser(LoginFragment.this.getContext(),username.getText().toString(), password.getText().toString()).observe(getViewLifecycleOwner(), new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        if (user != null) {
                            mViewModel.saveSession(user);
                            NavDirections action = LoginFragmentDirections.actionLoginFragmentToMainFragment();
                            NavHostFragment.findNavController(LoginFragment.this).navigate(action);
                        }
                    }
                });
            }
        });
    }
}