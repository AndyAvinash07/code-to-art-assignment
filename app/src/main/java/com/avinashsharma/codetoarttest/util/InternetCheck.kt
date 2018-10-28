package com.avinashsharma.codetoarttest.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.support.annotation.RequiresApi

class InternetCheck  {

    companion object {
        fun isInternetAvailable(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            return if (activeNetwork==null) false else activeNetwork.isConnected!!

        }
    }
}