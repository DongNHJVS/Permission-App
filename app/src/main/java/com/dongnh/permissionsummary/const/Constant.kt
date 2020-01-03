package com.dongnh.permissionsummary.const


// Flag permission
const val GRANTED = 3
const val NOT_GRANTED = 1
const val ALLOW = 2

// Name of provider
const val ANDROID = "android"

const val INTERNET = "INTERNET"
const val ACCESS_WIFI_STATE = "ACCESS_WIFI_STATE"
const val ACCESS_NETWORK_STATE = "ACCESS_NETWORK_STATE"
const val ACCESS_COARSE_LOCATION = "ACCESS_COARSE_LOCATION"
const val ACCESS_FINE_LOCATION = "ACCESS_FINE_LOCATION"
const val CAMERA = "CAMERA"
const val RECORD_AUDIO = "RECORD_AUDIO"

const val READ_EXTERNAL_STORAGE = "READ_EXTERNAL_STORAGE"
const val WRITE_EXTERNAL_STORAGE = "WRITE_EXTERNAL_STORAGE"

// Contact
const val GET_ACCOUNT = "GET_ACCOUNT"
const val READ_CONTACTS = "READ_CONTACTS"

const val CALL_PHONE = "CALL_PHONE"
const val READ_PHONE_STATE = "READ_PHONE_STATE"

const val AUTH_HEADER_NAME = "Authorization"
const val AUTH_DATA_HEADER_NAME = "Bearer "
const val RESPONSE_CACHE_PATH_NAME = "responses"
const val RESPONSE_CACHE_SIZE: Long = 10 * 1024 * 1024 //10Mb

// Enum of Token
const val SHARE_PREFERENCE = "CiaoSharePrefence"
const val SHARE_PREFERENCE_HISTORY = "CiaoHistory"
const val SHARE_PREFERENCE_HISTORY_KEY = "CiaoHistoryData"
const val SHARE_PREFERENCE_KEY_ACCESS = "access_token"
const val SHARE_PREFERENCE_KEY_ACCESS_REFRESH = "refresh_token"
const val SHARE_PREFERENCE_KEY_ACCESS_DATE = "expired_at"