package com.dongnh.permissionsummary.viewmodel

import android.content.Context
import android.content.Intent
import android.content.pm.*
import android.content.pm.PackageManager.NameNotFoundException
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

    val listPermissionFilter = arrayListOf<String>()

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
            } catch (e: NameNotFoundException) {
                e.printStackTrace()
            }

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
                    } catch (e: NameNotFoundException) {
                        Timber.e(e)
                    } catch (e: NotFoundException) {
                        Timber.e(e)
                    }

                    val permissionItem = PermissionItem(permissionName = stringPermission, drawableIcon = drawable, packageName = stringPermission)
                    listPermission.add(permissionItem)
                }

                // Add flag
                for (i in permissionGrants.indices) {
                    listPermission.get(i).granted = permissionGrants.get(i)
                }

                // Remove not know permission
                for (i in 0 until listPermission.size) {
                    if (listPermission[i].permissionName.equals(INTERNET)) {
                        listPermission[i].drawableIcon = context.getDrawable(R.drawable.internet)
                    }

                    if (listPermission[i].drawableIcon != null) {
                        listPermission[i].permissionName =
                            listPermission[i].permissionName?.toNamePermission()?.lowCase()?.capitalizeWords()?.lowCaseViewName()
                    }
                }

                listPermission.removeAll() { it.drawableIcon == null }
            }

            if (listPermission.size > 0) {
                // Get name and icon
                val ai: ApplicationInfo? = try {
                    context.packageManager.getApplicationInfo(packageInfo.packageName, 0)
                } catch (e: NameNotFoundException) {
                    null
                }

                val icon: Drawable =
                    context.packageManager.getApplicationIcon(packageInfo.packageName)
                val applicationName =
                    (if (ai != null) context.packageManager.getApplicationLabel(ai) else "(unknown)")

                // new item for view
                val appPermission = AppPermission(name = applicationName.toString(),
                    drawable = icon,
                    packagesName = packageInfo.packageName,
                    permissions = listPermission,
                    versionName = packageInfo.versionName)

                // With filter
                if (listPermissionFilter.size > 0) {
                    for (permission: PermissionItem in listPermission) {
                        for (key: String in listPermissionFilter) {
                            if (permission.permissionName?.contains(key)!!) {
                                arrayListApp.add(appPermission)
                                break
                            }
                        }
                    }
                } else {
                    arrayListApp.add(appPermission)
                }
            }
        }

        if (arrayListApp.isEmpty()) {
            viewList.value = View.GONE
            viewNull.value = View.VISIBLE
        } else {
            viewList.value = View.VISIBLE
            viewNull.value = View.GONE
        }
        disableSwipe.value = true
        adapterApp.addDataList(arrayListApp)
        stopWorking()
    }
}
