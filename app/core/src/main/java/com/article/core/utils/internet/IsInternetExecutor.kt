package com.article.core.utils.internet


import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * Extension method of getting internet connection status.
 */
fun AppCompatActivity.isInternetAvailable(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    var network: Network? = null
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) network =
        connectivityManager.activeNetwork ?: return false
    else Log.d("INTERNET_CONNECTION", "Error with API.")

    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }
}
