package com.sa.demo.exoxycarouseldemo.base.model.local.preference

interface IPreferenceManager {

    fun isFirstTime(): Boolean

    fun setFirstTime(isFirstTime: Boolean)

    fun saveAccessToken(accessToken: String?)

    fun getAccessToken(): String?

    fun clearAccessToken()

    fun saveFirstName(firstName: String)

    fun getFirstName(): String?

    fun saveLastName(lastName: String)

    fun getLastName(): String?

    fun getFullName(): String

    fun saveEmail(email: String)

    fun getEmail(): String?

    fun setCartCount(email: Int)

    fun getCartCount(): Int?

    fun saveBusinessName(businessName: String)

    fun getBusinessName(): String?

    fun saveGSTNumber(gstNumber: String)

    fun getGSTNumber(): String?

    fun saveCINNumber(number: String)

    fun getCINNumber(): String?

    fun saveToken(token: String)

    fun getToken(): String?
}