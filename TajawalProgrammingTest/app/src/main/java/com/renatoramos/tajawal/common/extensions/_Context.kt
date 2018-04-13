package com.renatoramos.tajawal.common.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast


/**
 * Method to create and Show a Toast Message in the view.
 */
fun Context.makeTextToast(toastText: CharSequence, duration: Int): Toast {
    return Toast.makeText(this, toastText, duration)
}

/**
 * Method to verify if is Internet Connected
 */
fun Context.isInternetConnected(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}
