package com.sa.demo.exoxycarouseldemo.base.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager


/**
 * Purpose  - BroadcastReceiver for internet connection handle on network change.
 * @author  - amit.prajapati
 * Created  - 16/10/17
 * Modified - 26/12/17
 */
class NetworkChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val activeConnection = isActiveConnection(context)
    }

    private fun isActiveConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = cm.activeNetworkInfo

        if (activeNetworkInfo != null) {
            val type = activeNetworkInfo.type
            if (type == ConnectivityManager.TYPE_WIFI || type == ConnectivityManager.TYPE_MOBILE) {
                return activeNetworkInfo.isConnected
            }
        }

        return false
    }



    companion object {

        const val ACTION_LOCAL_CONNECTIVITY = "action_local_connectivity"
        const val EXTRA_IS_ACTIVE_CONNECTION = "extra_is_active_connection"
        const val CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE"
    }
}