package com.dongnh.permissionsummary.viewmodel

import android.content.Context
import android.content.Intent
import android.content.pm.*
import android.content.res.Resources.NotFoundException
import android.graphics.drawable.Drawable
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.dongnh.permissionsummary.R
import com.dongnh.permissionsummary.adapter.AdapterAppPermission
import com.dongnh.permissionsummary.base.BaseViewModel
import com.dongnh.permissionsummary.const.ANDROID
import com.dongnh.permissionsummary.const.INTERNET
import com.dongnh.permissionsummary.model.AppPermission
import com.dongnh.permissionsummary.model.PermissionItem
import com.dongnh.permissionsummary.ultil.exts.capitalizeWords
import com.dongnh.permissionsummary.ultil.exts.lowCase
import com.dongnh.permissionsummary.ultil.exts.lowCaseViewName
import com.dongnh.permissionsummary.ultil.exts.toNamePermission
import timber.log.Timber


@Suppress("CAST_NEVER_SUCCEEDS", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "DEPRECATION")
class ListPermissionViewModel(var context: Context) : BaseViewModel() {
    val adapterApp: AdapterAppPermission = AdapterAppPermission()
    val viewList: MutableLiveData<Int> = MutableLiveData()
    val viewNull: MutableLiveData<Int> = MutableLiveData()

    val disableSwipe: MutableLiveData<Boolean> = MutableLiveData()

    init {
        viewList.value = View.VISIBLE
        viewNull.value = View.GONE
        disableSwipe.value = false
    }

    /**
     * Get all app of phone
     */
    fun getAllApp() {
        startWorking()
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        val pkgAppsList: List<*> =
            context.packageManager.queryIntentActivities(mainIntent, PackageManager.GET_RESOLVED_FILTER)

        val arrayListApp = arrayListOf<AppPermission>()

        for (obj in pkgAppsList) {
            val resolveInfo = obj as ResolveInfo
            var packageInfo: PackageInfo? = null
            try {
                packageInfo = context.packageManager.getPackageInfo(
                    resolveInfo.activityInfo.packageName,
                    PackageManager.GET_PERMISSIONS
                )
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            var ai: ApplicationInfo?
            ai = try {
                context.packageManager.getApplicationInfo(packageInfo?.packageName, 0)
            } catch (e: PackageManager.NameNotFoundException) {
                null
            }
            val icon: Drawable =
                context.packageManager.getApplicationIcon(packageInfo?.packageName)
            val applicationName =
                (if (ai != null) context.packageManager.getApplicationLabel(ai) else "(unknown)")

            val requestedPermissions = packageInfo!!.requestedPermissions
            val permissionGrants =  packageInfo.requestedPermissionsFlags

            val listPermission = arrayListOf<PermissionItem>()

            if (requestedPermissions != null) {
                for (stringPermission in requestedPermissions) {
                    var drawable: Drawable? = null
                    try {
                        val permissionInfo: PermissionInfo =
                            context.packageManager.getPermissionInfo(stringPermission, 0)
                        val groupInfo: PermissionGroupInfo =
                            context.packageManager.getPermissionGroupInfo(permissionInfo.group, 0)
                        drawable = context.packageManager.getResourcesForApplication(ANDROID)
                            .getDrawable(groupInfo.icon)
                    } catch (e: PackageManager.NameNotFoundException) {
                        Timber.e(e)
                    } catch (e: NotFoundException) {
                        Timber.e(e)
                    }

                    val permissionItem = PermissionItem(permissionName = stringPermission, drawableIcon = drawable)
                    listPermission.add(permissionItem)
                }

                // Add flag
                for (i in permissionGrants.indices) {
                    listPermission.get(i).granted = permissionGrants.get(i)
                }

                // Remove not know permission
                for (i in 0 until listPermission.size) {
                    if (listPermission.get(i).permissionName.equals(INTERNET)) {
                        listPermission.get(i).drawableIcon = context.getDrawable(R.drawable.internet)
                    }

                    if (listPermission.get(i).drawableIcon != null) {
                        listPermission.get(i).permissionName =
                            listPermission.get(i).permissionName?.toNamePermission()?.lowCase()?.capitalizeWords()?.lowCaseViewName()
                    }
                }

                listPermission.removeAll() { it.drawableIcon == null }
            }

            if (listPermission.size > 0) {
                // new item for view
                val appPermission = AppPermission(name = applicationName.toString(),
                    drawable = icon,
                    packagesName = packageInfo.packageName,
                    permissions = listPermission,
                    versionName = packageInfo.versionName)

                arrayListApp.add(appPermission)
            }
        }

        disableSwipe.value = true
        adapterApp.addDataList(arrayListApp)
        stopWorking()
    }
}
