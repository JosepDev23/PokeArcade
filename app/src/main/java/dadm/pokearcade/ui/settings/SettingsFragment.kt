package dadm.pokearcade.ui.settings

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import dadm.pokearcade.R
import dadm.pokearcade.data.users.UsersRepository
import dadm.pokearcade.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * The setting screen with the functionality to change language.
 */
@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    @Inject lateinit var usersRepository: UsersRepository

    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PreferenceManager.getDefaultSharedPreferences(requireContext())
            .registerOnSharedPreferenceChangeListener(this)

        val user = loginViewModel.user.value
        val usernamePref = findPreference<EditTextPreference>("username")
        usernamePref?.text = user?.username
    }

    override fun onDestroy() {
        super.onDestroy()
        PreferenceManager.getDefaultSharedPreferences(requireContext())
            .unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            "language" -> {
                val language = sharedPreferences?.getString("language", "en")
                setLocale(language)
            }
            "username" -> {
                val newUsername = sharedPreferences?.getString("username", null)
                newUsername?.let {
                    var user = loginViewModel.user.value
                    user?.username = it
                    lifecycleScope.launch {
                        usersRepository.updateUser(user!!)
                    }
                }
            }
        }
    }

    private fun setLocale(language: String?) {
        language?.let {
            val locale = Locale(it)
            Locale.setDefault(locale)
            val config = Configuration()
            config.setLocale(locale)
            requireContext().resources.updateConfiguration(
                config,
                requireContext().resources.displayMetrics
            )
            requireActivity().recreate()
        }
    }
}