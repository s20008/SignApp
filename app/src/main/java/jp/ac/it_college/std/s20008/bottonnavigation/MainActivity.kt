package jp.ac.it_college.std.s20008.bottonnavigation

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import jp.ac.it_college.std.s20008.bottonnavigation.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.pic_icon_layout.*
import kotlinx.android.synthetic.main.pic_icon_layout.view.*
import kotlinx.android.synthetic.main.uranai_icon_layout.*
import kotlinx.android.synthetic.main.uranai_icon_layout.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val destinationMap = mapOf(
            R.id.navigation_home to uranaiMotionLayout,
            R.id.navigation_dashboard to picMotionLayout

        )


        navController = findNavController(R.id.fragment)


        setupActionBarWithNavController(
            navController,
            AppBarConfiguration(destinationMap.keys)
        )


        binding.linearLayout.uranaiMotionLayout.setOnClickListener {
            val action: NavDirections

            if (navController.currentDestination?.id == R.id.navigation_dashboard) { //下方导航栏切换时 D -> H
                action = DashboardFragmentDirections.actionDashboardToHome()
            }else if(navController.currentDestination?.id == R.id.book){
                action = SignBookDirections.actionSignBookToNavigationHome() //s -> H
            }else{
                //占卜画面跳回主界面时的ID
                action = FortuneFragmentDirections.actionToHome() // f -> H
            }
            navController.navigate(action)
            
        }


        binding.linearLayout.picMotionLayout.setOnClickListener {
            val action: NavDirections
            if(navController.currentDestination?.id == R.id.navigation_fortune) { //占い画面跳到dashboard界面时 f -> D
                action = FortuneFragmentDirections.actionFortuneToDashboard()
            }else if (navController.currentDestination?.id == R.id.book){
                action = SignBookDirections.actionSignBookToNavigationDashboard()
            }else{
                action = HomeFragmentDirections.actionHomeToDashboard()//発する元.行き先 H -> D
            }
//            Log.d("Tagerror", R.id.fortune.toString())
            navController.navigate(action)

        }



        navController.addOnDestinationChangedListener { controller, destination, arguments ->
//            controller.popBackStack()
            destinationMap.values.forEach { it.progress = 0.001f }
            destinationMap[destination.id]?.transitionToEnd()
        }

    }

    //戻るボタンが機能する
    override fun onSupportNavigateUp()
        = findNavController(R.id.fragment).navigateUp()
}