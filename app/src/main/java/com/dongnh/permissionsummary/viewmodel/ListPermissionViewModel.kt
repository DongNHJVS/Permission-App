package com.dongnh.permissionsummary.viewmodel

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.dongnh.permissionsummary.adapter.AdapterAppPermission
import com.dongnh.permissionsummary.base.BaseViewModel
import com.dongnh.permissionsummary.model.AppPermission
import com.dongnh.permissionsummary.model.PermissionItem


@Suppress("CAST_NEVER_SUCCEEDS", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
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
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        val pkgAppsList: List<*> =
            context.packageManager.queryIntentActivities(mainIntent, 0)

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
                    val permissionItem = PermissionItem(permissionName = stringPermission)
                    listPermission.add(permissionItem)
                }

                for (i in permissionGrants.indices) {
                    listPermission.get(i).granted = permissionGrants.get(i)
                }
            }

            val appPermission = AppPermission(name = applicationName.toString(),
                drawable = icon,
                packagesName = packageInfo.packageName,
                permissions = listPermission,
                versionName = packageInfo.versionName)

            arrayListApp.add(appPermission)
        }

        disableSwipe.value = true
        adapterApp.addDataList(arrayListApp)
    }
}
