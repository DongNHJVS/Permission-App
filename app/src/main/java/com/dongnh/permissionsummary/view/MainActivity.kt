package com.dongnh.permissionsummary.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.dongnh.permissionsummary.R
import com.dongnh.permissionsummary.base.BaseActivity
import com.dongnh.permissionsummary.base.BaseFragment
import com.dongnh.permissionsummary.databinding.ActivityMainBinding
import com.dongnh.permissionsummary.ultil.exts.addFragmentSafeLy
import com.dongnh.permissionsummary.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            // Make Clicking the back button twice to exit an activity
            if (_doubleBackToExitPressedOnce) {
                super.onBackPressed()
                finish()
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
                //exitProcess(0)
            } else {
                _toastMessage = iniToastMessage(_toastMessage, getString(R.string.app_back_press))
                _doubleBackToExitPressedOnce = true

                Handler().postDelayed({ _doubleBackToExitPressedOnce = false }, 2000)
            }
        }
    }

    override fun initCreateDone() {
        // Add fragment
        addFragmentSafeLy(
            ListPermissionFragment(),
            ListPermissionFragment::class.java.simpleName, true, R.id.main_layout, 0, 0, 0, 0
        )
    }
}
