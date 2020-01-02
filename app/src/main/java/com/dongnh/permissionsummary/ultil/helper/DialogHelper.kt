package com.dongnh.permissionsummary.ultil.helper

import android.content.Context
import android.os.Looper
import androidx.annotation.IntRange
import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.dongnh.permissionsummary.ultil.interfaces.LifecycleBehaviour
import com.kaopiz.kprogresshud.KProgressHUD

class DialogHelper(private val context: Context) : LifecycleBehaviour {

    override var lifecycle: Lifecycle? = null
    private var progressHUD: KProgressHUD? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun lifecycleOnDestroy() {
        super.lifecycleOnDestroy()
        this@DialogHelper.releaseProgressHUDIfNeeded()
    }

    /**
     * display hud progress
     *
     * @param message String bottom text need to display as status
     * @param percent Int? progress need to display, set to null (default) mean show progress as indeterminate
     */
    fun progressShow(message: String, @IntRange(from = 0, to = 100) percent: Int? = null) {
        if (Looper.getMainLooper() != Looper.myLooper()) return

        if (this@DialogHelper.progressHUD == null) {
            this@DialogHelper.progressHUD =
                KProgressHUD.create(this@DialogHelper.context, KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(false)
        }

        this@DialogHelper.progressHUD?.setLabel(message)

        if (percent != null && percent in 0..100) {
            this@DialogHelper.progressHUD?.setStyle(KProgressHUD.Style.PIE_DETERMINATE)
            this@DialogHelper.progressHUD?.setProgress(percent)
        } else {
            this@DialogHelper.progressHUD?.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
        }

        if (this@DialogHelper.progressHUD?.isShowing != true) {
            try {
                this@DialogHelper.progressHUD?.show()
            } catch (exception: Exception) {
            }
        }
    }

    /**
     * display hud progress
     * @see progressShow(String, Int?)
     *
     * @param messageResId Int bottom string resource id need to display as status
     * @param percent Int? progress need to display, set to null (default) mean show progress as indeterminate
     */
    fun progressShow(@StringRes messageResId: Int, @IntRange(from = 0, to = 100) percent: Int? = null) {
        if (Looper.getMainLooper() != Looper.myLooper()) return

        this@DialogHelper.progressShow(this@DialogHelper.context.getString(messageResId), percent)
    }

    /**
     * hide progress if it shown
     *
     * @see progressShow
     */
    fun progressDismiss() {
        if (Looper.getMainLooper() != Looper.myLooper()) return

        this@DialogHelper.releaseProgressHUDIfNeeded()
    }

    private fun releaseProgressHUDIfNeeded() {
        if (Looper.getMainLooper() != Looper.myLooper()) return

        this@DialogHelper.progressHUD?.let { unwrapped ->
            if (unwrapped.isShowing) {
                unwrapped.dismiss()
            }
        }
        this@DialogHelper.progressHUD = null
    }
}