package com.imax.cefr.core.base.pref

import android.content.SharedPreferences

class LocalStorage(preference: SharedPreferences) {

    var token by StringPreference(preference)

    var isLoggedIn by BooleanPreference(preference, false)

    var login by StringPreference(preference)

    var fullName by StringPreference(preference)

    var streamKey by StringPreference(preference)

    var twitchChannelUsername by StringPreference(preference)

    var type by StringPreference(preference)
}
