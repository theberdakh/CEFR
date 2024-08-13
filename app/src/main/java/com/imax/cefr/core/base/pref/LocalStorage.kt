package com.imax.cefr.core.base.pref

import android.content.SharedPreferences
import com.imax.cefr.data.models.login.UserResponseData

class LocalStorage(preference: SharedPreferences) {

    private var userIdPreference by StringPreference(preference)
    private var namePreference by StringPreference(preference)
    private var emailPreference by StringPreference(preference)
    private var telNumberPreference by StringPreference(preference)
    private var rolePreference by StringPreference(preference)
    private var channelNamePreference by StringPreference(preference)
    private var userNamePreference by StringPreference(preference)
    private var streamKeyPreference by StringPreference(preference)

    private var isLoggedInPreference by BooleanPreference(preference, false)
    private var tokenPreference by StringPreference(preference)

    fun getUser(): UserResponseData {
        return UserResponseData(
            id = userIdPreference,
            name = namePreference,
            email = emailPreference,
            telNumber = telNumberPreference,
            role = rolePreference,
            channelName = channelNamePreference,
            userName = userNamePreference,
            streamKey = streamKeyPreference
        )
    }

    fun storeUser(user: UserResponseData) {
        userIdPreference = user.id
        namePreference = user.name
        emailPreference = user.email
        telNumberPreference = user.telNumber
        rolePreference = user.role
        channelNamePreference = user.channelName
        userNamePreference = user.userName
        streamKeyPreference = user.streamKey
    }

    fun getToken(): String {
        return tokenPreference
    }

    fun storeToken(token: String) {
        tokenPreference = token
    }

    fun storeLogin(isLoggedIn: Boolean): Boolean {
        isLoggedInPreference = isLoggedIn
        return isLoggedInPreference
    }

    fun isLoggedIn(): Boolean {
        return isLoggedInPreference
    }


}
