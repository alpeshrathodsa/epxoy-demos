package com.sa.demo.exoxycarouseldemo.base.model.local.preference

import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.sa.demo.exoxycarouseldemo.base.utils.Constant

class PreferenceManager constructor(var sharedPreferences: SharedPreferences) : IPreferenceManager {

    companion object {
        const val KEY_FIRST_TIME = "pre.key.app.firstTime"
        const val KEY_ACCESS_TOKEN = "pre.key.accessToken"
        const val KEY_FIRST_NAME = "pre.key.firstName"
        const val KEY_LAST_NAME = "pre.key.lastName"
        const val KEY_EMAIL = "pre.key.email"
        const val KEY_USER = "pre.key.user"
        const val KEY_CART_COUNT = "pre.key.id"
        const val KEY_BUSINESS_NAME = "pre.key.business.name"
        const val KEY_GST_NUMBER = "pre.key.gst.number"
        const val KEY_CIN_NUMBER = "pre.key.cin.number"
        const val KEY_F_TOKEN = "pre.key.firebase.token"
    }

    override fun isFirstTime(): Boolean {
        return sharedPreferences.getBoolean(KEY_FIRST_TIME, true)
    }

    override fun setFirstTime(isFirstTime: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_FIRST_TIME, isFirstTime).apply()
    }

    override fun saveAccessToken(accessToken: String?) {
        sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, accessToken).apply()
    }

    override fun getAccessToken(): String? {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, "")
    }

    override fun clearAccessToken() {
        sharedPreferences.edit().remove(KEY_ACCESS_TOKEN).apply()
    }

    override fun saveFirstName(firstName: String) {
        sharedPreferences.edit().putString(KEY_FIRST_NAME, firstName).apply()
    }

    override fun getFirstName(): String? {
        return sharedPreferences.getString(KEY_FIRST_NAME, "")
    }

    override fun saveLastName(lastName: String) {
        sharedPreferences.edit().putString(KEY_LAST_NAME, lastName).apply()
    }

    override fun getLastName(): String? {
        return sharedPreferences.getString(KEY_LAST_NAME, "")
    }

    override fun getFullName(): String {
        return "${getFirstName()} ${getLastName()}"
    }

    override fun saveEmail(email: String) {
        sharedPreferences.edit().putString(KEY_EMAIL, email).apply()
    }

    override fun getEmail(): String? {
        return sharedPreferences.getString(KEY_EMAIL, "")
    }

    override fun setCartCount(count: Int) {
        sharedPreferences.edit().putInt(KEY_CART_COUNT, count).apply()
    }

    override fun getCartCount(): Int {
        return sharedPreferences.getInt(KEY_CART_COUNT, 0)
    }

    override fun getBusinessName(): String? {
        return sharedPreferences.getString(KEY_BUSINESS_NAME, "")
    }

    override fun saveBusinessName(businessName: String) {
        sharedPreferences.edit().putString(KEY_BUSINESS_NAME, businessName).apply()
    }

    override fun getGSTNumber(): String? {
        return sharedPreferences.getString(KEY_GST_NUMBER, "")
    }

    override fun saveGSTNumber(gstNumber: String) {
        sharedPreferences.edit().putString(KEY_GST_NUMBER, gstNumber).apply()
    }

    override fun getCINNumber(): String? {
        return sharedPreferences.getString(KEY_CIN_NUMBER, "")
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_F_TOKEN, token).apply()
    }

    override fun getToken(): String? {
        return sharedPreferences.getString(KEY_F_TOKEN, "")
    }

    override fun saveCINNumber(number: String) {
        sharedPreferences.edit().putString(KEY_CIN_NUMBER, number).apply()
    }

    fun setNetworkAvailable(isNetwork: Boolean) {
        sharedPreferences.edit().putBoolean(Constant.PREF_NETWORK_STATE, isNetwork).apply()
    }

    /**
     * Saves object into the Preferences.
     *
     * @param `object` Object of model class (of type [T]) to save
     * @param key Key with which Shared preferences to
     **/
    fun <T> put(obj: T, key: String) {
        //Convert object to JSON String.
        val jsonString = GsonBuilder().create().toJson(obj)
        //Save that String in SharedPreferences
        sharedPreferences.edit().putString(key, jsonString).apply()
    }

    /**
     * Used to retrieve object from the Preferences.
     *
     * @param key Shared Preference key with which object was saved.
     **/
    inline fun <reified T> get(key: String): T? {
        //We read JSON String which was saved.
        val value = sharedPreferences.getString(key, null)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type “T” is used to cast.
        return GsonBuilder().create().fromJson(value, T::class.java)
    }
}