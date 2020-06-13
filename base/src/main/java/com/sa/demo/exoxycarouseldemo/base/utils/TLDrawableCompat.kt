package com.tradeloop.base.utils

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build


/**
 * Created by Alpesh Rathod on 19/11/19.
 */
object TLDrawableCompat {
    fun setColorFilter(drawable: Drawable?, color: Int?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            color?.let { appliedColor ->
                drawable?.colorFilter = BlendModeColorFilter(appliedColor, BlendMode.SRC_ATOP)
            }
        } else {
            color?.let { appliedColor ->
                drawable?.setColorFilter(appliedColor, PorterDuff.Mode.SRC_IN)
            }

        }
    }
}