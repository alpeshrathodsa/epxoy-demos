package com.sa.demo.exoxycarouseldemo.modal

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.sa.demo.exoxycarouseldemo.R

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 16/6/20
 */
@EpoxyModelClass(layout = R.layout.model_indicator)
abstract class IndicatorModel : EpoxyModelWithHolder<IndicatorModel.Holder>() {

    class Holder : EpoxyHolder() {
        override fun bindView(itemView: View) {
        }
    }
}