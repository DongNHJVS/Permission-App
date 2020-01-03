package com.dongnh.permissionsummary.ultil.helper

import android.content.Context
import android.content.SharedPreferences
import com.dongnh.permissionsummary.const.AUTH_DATA_HEADER_NAME
import com.dongnh.permissionsummary.const.SHARE_PREFERENCE
import com.dongnh.permissionsummary.const.SHARE_PREFERENCE_KEY_ACCESS

class SharePreferenceHelper(val androidContext: Context) {
    fun getAuthData(): String {
        val sharedPreferences: SharedPreferences = getSharedPreferences(androidContext)
        return AUTH_DATA_HEADER_NAME + getAccessKeyInSharePreference(sharedPreferences)
    }

    fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARE_PREFERENCE, Context.MODE_PRIVATE)
    }

    // get SHARE_PREFERENCE_KEY_ACCESS
    fun getAccessKeyInSharePreference(sharePreference: SharedPreferences) : String {
        return sharePreference.getString(SHARE_PREFERENCE_KEY_ACCESS, "").toString()
    }
}