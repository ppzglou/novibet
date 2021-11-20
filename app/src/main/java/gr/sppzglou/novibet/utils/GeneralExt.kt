package gr.sppzglou.novibet.utils

import android.content.res.Resources

val Resources.screenWidth: Int
    get() = displayMetrics.widthPixels

val Resources.screenHeight: Int
    get() = displayMetrics.heightPixels