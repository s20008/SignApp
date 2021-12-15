package jp.ac.it_college.std.s20008.bottonnavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import jp.ac.it_college.std.s20008.bottonnavigation.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.pic_icon_layout.*
import kotlinx.android.synthetic.main.uranai_icon_layout.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val destinationMap = mapOf(
            R.id.navigation_home to uranaiMotionLayout,
            R.id.navigation_dashboard to picMotionLayout

        )


        navController = findNavController(R.id.fragment)

//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard
//            )
//        )

        setupActionBarWithNavController(
            navController,
            AppBarConfiguration(destinationMap.keys)
        )
        destinationMap.forEach { map ->
            map.value.setOnClickListener { navController.navigate(map.key) }
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            controller.popBackStack()
            destinationMap.values.forEach { it.progress = 0.001f }
            destinationMap[destination.id]?.transitionToEnd()
        }

    }
}