package jp.ac.it_college.std.s20008.bottonnavigation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.constraintlayout.motion.widget.MotionLayout
import jp.ac.it_college.std.s20008.bottonnavigation.databinding.ActivitySplashBinding
import kotlinx.android.synthetic.main.activity_splash.*

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//
//        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)


        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.windowInsetsController?.hide(
            WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars()
        )

        supportActionBar?.hide()


        binding.mainImage2.post{
            binding.mainImage2.performClick()
        }
        binding.splashMotion.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {

            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {

            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                this@SplashActivity.finish()
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {

            }
        })
    }
}