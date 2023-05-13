package dadm.pokearcade.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dadm.pokearcade.R
import dadm.pokearcade.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Represents the login functionality of the app. It contains methods for user authentication
 * and the navigation to user signup.
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.loginUser(username, password)
        }

        viewModel.loginStatus.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                val action = LoginFragmentDirections.actionLoginFragmentToGamesFragment()
                findNavController().navigate(action)
            } else {
                Snackbar.make(binding.root, "Wrong username or password", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }


        binding.registerTextView.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignupFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}