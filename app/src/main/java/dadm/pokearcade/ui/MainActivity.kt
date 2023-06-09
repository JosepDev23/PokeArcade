package dadm.pokearcade.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import dadm.pokearcade.R
import dadm.pokearcade.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Class main activity
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val navController =
            binding.fragmentContainerView.getFragment<NavHostFragment>().navController

        binding.bottomNavigationView as NavigationBarView
        binding.bottomNavigationView.setupWithNavController(navController)

        /**
         * The navigation bar only show on the game, profile and settings fragment.
         */
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.gamesFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.profileFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.settingsFragment -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
                /** If not, invisible */
                else -> {
                    binding.bottomNavigationView.visibility = View.INVISIBLE
                }
            }
        }

        /**
         * The configuration of the action bar
         */
        setSupportActionBar(binding.idMaterialToolBar)
        val appBarConfig = AppBarConfiguration(
            setOf(
                R.id.gamesFragment,
                R.id.profileFragment,
                R.id.settingsFragment,
                R.id.guessPokemonFragment,
                R.id.pokequizFragment,
                R.id.loginFragment,
                R.id.signupFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfig)
    }
}