package dadm.pokearcade.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dadm.pokearcade.R
import dadm.pokearcade.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Represents the signup functionality of the app. To create a new user you only need
 * an username and a password.
 */
@AndroidEntryPoint
class SignupFragment : Fragment(R.layout.fragment_signup) {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignupBinding.bind(view)

        binding.signupButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.signupUser(username, password)
        }

        viewModel.signupStatus.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                Snackbar.make(binding.root, "User registered successfully", Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                Snackbar.make(binding.root, "User registration failed", Snackbar.LENGTH_SHORT)
                    .show()
            }
            val action = SignupFragmentDirections.actionSignupFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}