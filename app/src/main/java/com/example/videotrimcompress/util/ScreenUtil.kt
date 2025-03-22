package com.example.videotrimcompress.util

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object ScreenUtil {
    fun getScreenWidth(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    fun getScreenHeight(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    fun getScreenDensity(context: Context): Float {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.density
    }

    @Composable
    fun getResponsiveDp(baseDp: Dp): Dp {
        val configuration = LocalConfiguration.current
        val density = LocalDensity.current
        
        return with(density) {
            val screenWidth = configuration.screenWidthDp.dp.toPx()
            val baseWidth = 360f // Base width for design
            val scale = screenWidth / baseWidth
            
            (baseDp.toPx() * scale).toDp()
        }
    }
} 