package com.sa.demo.exoxycarouseldemo.base.base

import android.content.*
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sa.demo.exoxycarouseldemo.base.R
import com.sa.demo.exoxycarouseldemo.base.model.local.preference.PreferenceManager
import com.sa.demo.exoxycarouseldemo.base.utils.*
import com.sa.demo.exoxycarouseldemo.base.utils.NetworkChangeReceiver.Companion.ACTION_LOCAL_CONNECTIVITY
import com.sa.demo.exoxycarouseldemo.base.utils.NetworkChangeReceiver.Companion.CONNECTIVITY_CHANGE
import com.tradeloop.base.utils.SnackBarUtils
import org.koin.android.ext.android.inject
import java.util.*


abstract class BaseActivity : AppCompatActivity(), ConnectionBridge {

    private val mPreferenceManger by inject<PreferenceManager>()

    val stack = Stack<Fragment>()
    var ft: FragmentTransaction? = null

    private lateinit var localBroadcastManager: androidx.localbroadcastmanager.content.LocalBroadcastManager
    private lateinit var networkBroadcastReceiver: NetworkBroadcastReceiver
    private lateinit var networkChangeReceiver: NetworkChangeReceiver
    protected abstract fun networkChangeState(boolean: Boolean)

    /**
     *To initialize the component you want to initialize before inflating layout
     */
    private fun preInflateInitialization() {
        /*1. Windows transition
        * 2. Permission utils initialization*/
    }

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getRootView(): View


    abstract fun postDataBinding(binding: ViewDataBinding)

    /**
     *To initialize the activity components
     */
    protected abstract fun initializeComponent()

    // Common Handling of top bar for all fragments like header name, icon on top bar in case of moving to other fragment and coming back again
    abstract fun <T> setUpFragmentConfig(currentState: IFragmentState, keys: T?)

    override fun onCreate(savedInstanceState: Bundle?) {
        preInflateInitialization()
        val binding = DataBindingUtil.setContentView(this, getLayoutId()) as ViewDataBinding
        super.onCreate(savedInstanceState)
        postDataBinding(binding)
        initializeComponent()
        initFields()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun isNetworkAvailable(): Boolean {
        return NetworkUtils.isNetworkAvailable(this)
    }

    private inner class NetworkBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val activeConnection = intent
                .getBooleanExtra(NetworkChangeReceiver.EXTRA_IS_ACTIVE_CONNECTION, false)
            if (activeConnection) {
                networkChangeState(true)
                mPreferenceManger.setNetworkAvailable(true)
                checkShowingConnectionError()
            } else {
                networkChangeState(false)
                mPreferenceManger.setNetworkAvailable(false)
                checkShowingConnectionError()
            }
        }
    }

    override fun checkNetworkAvailableWithError(): Boolean {
        return if (!isNetworkAvailable()) {
            networkChangeState(false)
            mPreferenceManger.setNetworkAvailable(false)
            false
        } else {
            networkChangeState(true)
            mPreferenceManger.setNetworkAvailable(true)
            true
        }
    }

    protected fun checkShowingConnectionError() {
        if (!isNetworkAvailable()) {
            showSnackBar(R.string.error_no_internet)
        }
    }

    private fun initFields() {
        networkBroadcastReceiver = NetworkBroadcastReceiver()
        networkChangeReceiver = NetworkChangeReceiver()
        localBroadcastManager =
            androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance(this)
    }

    fun hideKeyboard() {
        CommonUtils.hideKeyboard(this)
    }

    fun clearLoginData() {
        mPreferenceManger.sharedPreferences.edit().clear().apply()
        mPreferenceManger.setFirstTime(false)
    }

    fun after(delay: Long, process: () -> Unit) {
        Handler().postDelayed({
            process()
        }, delay)
    }

    //----------------------------- [Start] ProgressDialog Utils ----------------------------------
    fun showProgressDialog() {
        ProgressUtil.showOldProgressDialog(this)
    }

    fun hideProgressDialog() {
        ProgressUtil.closeOldProgressDialog()
    }

    //----------------------------- [End] ProgressDialog Utils -------------------------------------

    //----------------------------- [Start] Snack bar Utils -----------------------------------------
    fun showSnackBar(message: Int) {
        SnackBarUtils.show(getRootView(), message)
    }

    fun showSnackBar(message: String) {
        SnackBarUtils.show(getRootView(), message)
    }

    fun showSnackBar(message: Int, duration: Int) {
        SnackBarUtils.show(getRootView(), message, duration)
    }

    fun showSnackBar(message: String, duration: Int) {
        SnackBarUtils.show(getRootView(), message, duration)
    }

    //----------------------------- [End] Snack bar Utils -------------------------------------------

    //----------------------------- [Start] Toast Utils --------------------------------------------

    fun showToast(resourceId: Int) {
        Toast.makeText(this, resourceId, Toast.LENGTH_SHORT).show()
    }

    fun showToast(resource: String) {
        Toast.makeText(this, resource, Toast.LENGTH_SHORT).show()
    }

    fun showToast(resourceId: Int, toastLong: Int) {
        Toast.makeText(this, resourceId, toastLong).show()
    }

    fun showToast(resource: String, toastLong: Int) {
        Toast.makeText(this, resource, toastLong).show()
    }

    //----------------------------- [End] Toast Utils ----------------------------------------------

    //----------------------------- [Start] Alert Dialog Utils -------------------------------------

    fun showAlertDialog(
        title: Int,
        message: Int,
        posBtnClickListener: DialogInterface.OnClickListener
    ) {
        DialogUtils.alert(
            this,
            title,
            message,
            R.string.default_alert_dialog_button_title,
            posBtnClickListener
        )
    }

    fun showAlertDialog(
        title: String,
        message: String,
        posBtnClickListener: DialogInterface.OnClickListener
    ) {
        DialogUtils.alert(
            this,
            title,
            message,
            R.string.default_alert_dialog_button_title,
            posBtnClickListener
        )
    }

    fun showConfirmDialog(
        title: Int,
        message: Int,
        positiveButtonTitle: Int,
        posBtnClickListener: DialogInterface.OnClickListener,
        negativeButtonTitle: Int,
        negBtnClickListener: DialogInterface.OnClickListener
    ) {
        DialogUtils.confirm(
            this,
            title,
            message,
            positiveButtonTitle,
            posBtnClickListener,
            negativeButtonTitle,
            negBtnClickListener
        )
    }

    fun showCustomConfirmDialog(
        title: Int,
        message: Int,
        positiveButtonTitle: Int,
        posBtnClickListener: View.OnClickListener,
        negativeButtonTitle: Int,
        negBtnClickListener: View.OnClickListener
    ) {
        DialogUtils.customConfirm(
            this,
            title,
            message,
            positiveButtonTitle,
            posBtnClickListener,
            negativeButtonTitle,
            negBtnClickListener
        )

    }

    override fun onResume() {
        super.onResume()
        registerBroadcastReceivers()
        checkShowingConnectionError()
    }

    override fun onPause() {
        super.onPause()
        unregisterBroadcastReceivers()
    }

    private var receiverNet = NetworkChangeReceiver()
    private fun registerBroadcastReceivers() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                registerReceiver(
                    receiverNet,
                    IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                )
            }
        } catch (e: Exception) {
//            LogUtils.e(e.message ?: "Error registering registerBroadcastReceivers")
        }

        localBroadcastManager.registerReceiver(
            networkBroadcastReceiver,
            IntentFilter(ACTION_LOCAL_CONNECTIVITY)
        )
        localBroadcastManager.registerReceiver(
            networkChangeReceiver,
            IntentFilter(CONNECTIVITY_CHANGE)
        )
    }

    private fun unregisterBroadcastReceivers() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                unregisterReceiver(receiverNet)
            }
        } catch (e: Exception) {
//            LogUtils.e(e.message ?: "Error unregistering unregisterBroadcastReceivers")
        }

        localBroadcastManager.unregisterReceiver(networkBroadcastReceiver)
        localBroadcastManager.unregisterReceiver(networkChangeReceiver)
    }

    fun refreshCartCount(
        tvCartItemCount: AppCompatTextView
    ) {
        val count = getCount()
        when {
            count <= 0 -> {
                tvCartItemCount.visibility = View.GONE
            }
            count > 99 -> {
                tvCartItemCount.text = resources.getString(R.string.label_count_99_plus)
            }
            else -> {
                tvCartItemCount.text = count.toString()
            }
        }
    }

    fun getCount(): Int {
        return mPreferenceManger.getCartCount()
    }

    fun setCount(count: Int) {
        mPreferenceManger.setCartCount(count)
    }
}