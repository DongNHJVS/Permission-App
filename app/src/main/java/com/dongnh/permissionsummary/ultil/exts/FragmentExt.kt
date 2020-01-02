package com.dongnh.permissionsummary.ultil.exts

import android.Manifest
import android.content.pm.PackageManager
import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

/**
 * Method to replace the fragment. The [fragment] is added to the container view with id
 * [containerViewId] and a [tag]. The operation is performed by the childFragmentManager.
 * This method checks if fragment is added.
 */
fun Fragment.replaceFragmentSafelyAndRemove(fragment: Fragment,
                                            tag: String,
                                            allowStateLoss: Boolean = false,
                                            @IdRes containerViewId: Int,
                                            @AnimRes enterAnimation: Int = 0,
                                            @AnimRes exitAnimation: Int = 0,
                                            @AnimRes popEnterAnimation: Int = 0,
                                            @AnimRes popExitAnimation: Int = 0) {
    if (childFragmentManager.backStackEntryCount >= 1) {
        childFragmentManager.popBackStack()
    }
    if (isAdded) {
        val ft = childFragmentManager.beginTransaction()
        ft.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        ft.replace(containerViewId, fragment, tag)
        if (!childFragmentManager.isStateSaved) {
            ft.commit()
        } else if (allowStateLoss) {
            ft.commitAllowingStateLoss()
        }
    }
}

/**
 * Method to replace the fragment. The [fragment] is added to the container view with id
 * [containerViewId] and a [tag]. The operation is performed by the childFragmentManager.
 * This method checks if fragment is added.
 */
fun Fragment.replaceFragmentSafely(fragment: Fragment,
                                   tag: String,
                                   allowStateLoss: Boolean = false,
                                   @IdRes containerViewId: Int,
                                   @AnimRes enterAnimation: Int = 0,
                                   @AnimRes exitAnimation: Int = 0,
                                   @AnimRes popEnterAnimation: Int = 0,
                                   @AnimRes popExitAnimation: Int = 0) {
    if (isAdded) {
        val ft = childFragmentManager.beginTransaction()
        ft.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        ft.replace(containerViewId, fragment, tag)
        if (!childFragmentManager.isStateSaved) {
            ft.commit()
        } else if (allowStateLoss) {
            ft.commitAllowingStateLoss()
        }
    }
}

/**
 * Method to add the fragment. The [fragment] is added to the container view with id
 * [containerViewId] and a [tag]. The operation is performed by the childFragmentManager.
 * This method checks if fragment exists and it is added.
 * @return the fragment added.
 */
fun <T : Fragment> Fragment.addFragmentSafely(fragment: T,
                                              tag: String,
                                              allowStateLoss: Boolean = false,
                                              @IdRes containerViewId: Int,
                                              @AnimRes enterAnimation: Int = 0,
                                              @AnimRes exitAnimation: Int = 0,
                                              @AnimRes popEnterAnimation: Int = 0,
                                              @AnimRes popExitAnimation: Int = 0): T {
    if (isAdded && !existsFragmentByTag(tag)) {
        val ft = childFragmentManager.beginTransaction()
        ft.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        ft.add(containerViewId, fragment, tag)
        if (!childFragmentManager.isStateSaved) {
            ft.commit()
        } else if (allowStateLoss) {
            ft.commitAllowingStateLoss()
        }
        return fragment
    }
    return findFragmentByTag(tag) as T
}

/**
 * Method to add the fragment. The [fragment] is added to the container view with id
 * [containerViewId] and a [tag]. The operation is performed by the childFragmentManager.
 * This method checks if fragment exists and it is added.
 * @return the fragment added.
 */
fun <T : Fragment> Fragment.addFragmentSafelyBacktrack(fragment: T,
                                                       tag: String,
                                                       allowStateLoss: Boolean = false,
                                                       @IdRes containerViewId: Int,
                                                       @AnimRes enterAnimation: Int = 0,
                                                       @AnimRes exitAnimation: Int = 0,
                                                       @AnimRes popEnterAnimation: Int = 0,
                                                       @AnimRes popExitAnimation: Int = 0): T {
    if (isAdded && !existsFragmentByTag(tag)) {
        val ft = childFragmentManager.beginTransaction()
        ft.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        ft.add(containerViewId, fragment, tag)
        ft.addToBackStack(null)
        if (!childFragmentManager.isStateSaved) {
            ft.commit()
        } else if (allowStateLoss) {
            ft.commitAllowingStateLoss()
        }
        return fragment
    }
    return findFragmentByTag(tag) as T
}

/**
 * Method to add the fragment. The [fragment] is added to the container view with id
 * [containerViewId] and a [tag]. The operation is performed by the childFragmentManager.
 * This method checks if fragment exists and it is added.
 * @return the fragment added.
 */
fun <T : Fragment> Fragment.addFragmentSafelyBacktrackRemove(fragment: T,
                                                             tag: String,
                                                             allowStateLoss: Boolean = false,
                                                             @IdRes containerViewId: Int,
                                                             @AnimRes enterAnimation: Int = 0,
                                                             @AnimRes exitAnimation: Int = 0,
                                                             @AnimRes popEnterAnimation: Int = 0,
                                                             @AnimRes popExitAnimation: Int = 0): T {
    if (childFragmentManager.backStackEntryCount >= 1) {
        for (i in 0  until childFragmentManager.backStackEntryCount) {
            childFragmentManager.popBackStack()
        }
    }
    if (isAdded && !existsFragmentByTag(tag)) {
        val ft = childFragmentManager.beginTransaction()
        ft.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        ft.add(containerViewId, fragment, tag)
        ft.addToBackStack(null)
        if (!childFragmentManager.isStateSaved) {
            ft.commit()
        } else if (allowStateLoss) {
            ft.commitAllowingStateLoss()
        }
        return fragment
    }
    return findFragmentByTag(tag) as T
}

/**
 * Method to check if fragment exists. The operation is performed by the childFragmentManager.
 */
fun Fragment.existsFragmentByTag(tag: String): Boolean {
    return childFragmentManager.findFragmentByTag(tag) != null
}

/**
 * Method to get fragment by tag. The operation is performed by the childFragmentManager.
 */
fun Fragment.findFragmentByTag(tag: String): Fragment? {
    return childFragmentManager.findFragmentByTag(tag)
}

fun Fragment.checkPermissionCall(): Boolean {
    if (ActivityCompat.checkSelfPermission(
            this.context!!,
            Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        return true
    }
    return false
}
