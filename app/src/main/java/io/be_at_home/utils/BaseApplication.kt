package io.be_at_home.utils

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import io.be_at_home.model.UserModel

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DEBUG = isDebuggable(this)

        userModel = UserModel()
    }

    private fun isDebuggable(context: Context): Boolean {
        var debuggable = false
        val pm = context.packageManager
        try {
            val appInfo = pm.getApplicationInfo(context.packageName, 0)
            debuggable = 0 != appInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e("BaseApplication :::", "${e.printStackTrace()}")
        }
        return debuggable
    }


    companion object {
        var instance = BaseApplication()
        @Volatile
        var DEBUG = false
        var storeVersion = ""
        var appVersion: String? = null
        var userModel: UserModel = UserModel()

    }
}
